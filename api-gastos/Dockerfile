FROM openjdk:17-alpine
ADD /target/gastos.jar gastos.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar gastos.jar