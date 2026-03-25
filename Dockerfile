FROM maven:3.9.12
LABEL authors="noahstewart"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn package

CMD ["java", "-jar", "target/opt2.jar"]