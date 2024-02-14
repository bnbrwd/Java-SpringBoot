FROM eclipse-temurin:17

LABEL key="bnbrwd@gmail.com"

WORKDIR /app

COPY target/DockerSpringBootDemo-0.0.1-SNAPSHOT.jar /app/springboot-docker-demo.jar

ENTRYPOINT [ "java", "-jar", "springboot-docker-demo.jar" ]

