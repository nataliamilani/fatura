FROM openjdk:11-jre-slim

WORKDIR /app

EXPOSE 8084

ENV DATABASE_CONNECTION_URL="jdbc:mysql://db:3306/faturadb"
ENV EUREKA_CONNECTION_URL="http://eureka:8761"
ENV CONTA_CORRENTE_URL="http://contacorrente:8083/conta-corrente"

COPY target/fatura.jar /app/fatura.jar

ENTRYPOINT ["java", "-jar", "fatura.jar"]