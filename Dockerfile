FROM eclipse-temurin:17
WORKDIR /app
COPY target/ProductService-0.0.1-SNAPSHOT.jar /app/ProductService.jar
ENTRYPOINT ["java","-jar","/app/ProductService.jar"]
EXPOSE 8081
