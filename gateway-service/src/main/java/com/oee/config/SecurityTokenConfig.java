package com.oee.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.oee.filter.JwtTokenAuthenticationFilter;


@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtConfig jwtConfig;
 
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
    	   http
		.csrf().disable()
		.cors().configurationSource(corsConfigurationSource()).and()
		    // make sure we use stateless session; session won't be used to store user's state.
	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
		.and()
		    // handle an authorized attempts 
		    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
		.and()
		   // Add a filter to validate the tokens with every request
		   .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		// authorization requests config
		.authorizeRequests()
		   // allow all who are accessing "auth" service
		   .antMatchers(HttpMethod.POST, jwtConfig.getUri(), "/auth-service/**", "/actuator/**","/routes").permitAll()
		   .antMatchers(HttpMethod.GET, "/routes").permitAll()
		   // must be an admin if trying to access admin area (authentication is also required here)
//		   .antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
		   // Any other request must be authenticated
		   .anyRequest().authenticated(); 
	}
	
	@Bean
  	public JwtConfig jwtConfig() {
    	   return new JwtConfig();
  	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin",
				"Access-Control-Allow-Headers", "X-Auth-Token", "Authorization", "Origin"));
		configuration.setExposedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin",
				"Access-Control-Allow-Headers", "Origin", "X-Auth-Token", "Authorization"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(123L);
		
		//the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
//        configuration.addExposedHeader("*");
        configuration.addAllowedMethod("*");
        
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}