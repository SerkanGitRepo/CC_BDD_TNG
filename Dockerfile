FROM maven:3.6-jdk-8

COPY src /home/CC_BDD_TNG/src

# COPY reports /home/CC_BDD_TNG/reports

COPY pom.xml /home/CC_BDD_TNG

COPY docker-compose.yml /home/CC_BDD_TNG

RUN mvn -f /home/CC_BDD_TNG/pom.xml clean test -DskipTests=true