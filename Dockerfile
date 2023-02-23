FROM openjdk:latest
COPY ./target/CW_SE_Releases-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "CW_SE_Releases-1.0-SNAPSHOT-jar-with-dependencies.jar"]