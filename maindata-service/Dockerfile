# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# EXPOSE 8090
# ADD target/*.jar app.jar
# ENV JAVA_OPTS=""
# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


#########DEV#############


FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8090
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.oee.MaindataServiceApplication"]


########PROD#########

# FROM maven:3.6.3-jdk-8 as backend-build
# WORKDIR /fullstack/backend
# COPY pom.xml .
# RUN mvn dependency:go-offline -B
# COPY src src
# RUN mvn install -DskipTests
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# EXPOSE 8090
# ARG DEPENDENCY=/fullstack/backend/target/dependency
# COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=backend-build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.oee.MaindataServiceApplication"]