package com.oee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.CorsRegistry;

import java.util.Arrays;

//@Configuration
public class WebConfig
//        implements Filter,
//        WebMvcConfigurer
    {

//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**").allowedMethods("*");
//        }

//        @Bean
//        CorsWebFilter corsWebFilter() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Arrays.asList("*"));
//            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
//            configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin",
//                    "Access-Control-Allow-Headers", "X-Auth-Token", "Authorization", "Origin"));
//            configuration.setExposedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Origin",
//                    "Access-Control-Allow-Headers", "Origin", "X-Auth-Token", "Authorization"));
//            configuration.setAllowCredentials(false);
//            configuration.setMaxAge(123L);
//
//            //the below three lines will add the relevant CORS response headers
////            configuration.addAllowedOrigin("*");
////            configuration.addAllowedHeader("*");
////        configuration.addExposedHeader("*");
////            configuration.addAllowedMethod("*");
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return new CorsWebFilter(source);
//        }

//        @Bean
//        public DiscoveryClientRouteDefinitionLocator
//        discoveryClientRouteLocator(DiscoveryClient discoveryClient) {
//
//            return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
//        }
//
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
//      HttpServletResponse response = (HttpServletResponse) res;
//      HttpServletRequest request = (HttpServletRequest) req;
//      System.out.println("WebConfig; "+request.getRequestURI());
//      response.setHeader("Access-Control-Allow-Origin", "*");
//      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
//      response.setHeader("Access-Control-Max-Age", "3600");
//      response.setHeader("Access-Control-Allow-Credentials", "true");
//      response.setHeader("Access-Control-Expose-Headers", "Authorization");
//      response.addHeader("Access-Control-Expose-Headers", "responseType");
//      response.addHeader("Access-Control-Expose-Headers", "observe");
//      System.out.println("Request Method: "+request.getMethod());
//      if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
//          try {
//              chain.doFilter(req, res);
//          } catch(Exception e) {
//              e.printStackTrace();
//          }
//      } else {
//          System.out.println("Pre-flight");
//          response.setHeader("Access-Control-Allow-Origin", "*");
//          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
//          response.setHeader("Access-Control-Max-Age", "3600");
//          response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
//          "USERID"+"ROLE"+
//                  "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
//          response.setStatus(HttpServletResponse.SC_OK);
//      }
//
//    }

}