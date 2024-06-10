#FROM tomcat:9.0-alpine
#LABEL maintainer="office@omilab.org"
#ADD target/portal_service.jar /usr/local/tomcat/webapps/
#ENTRYPOINT ["java","-jar","portal_service.jar"]
#EXPOSE 8080
#CMD ["catalina.sh", "run"]

#.war
FROM openjdk:17
LABEL maintainer="office@omilab.org"
ADD target/portal_service.war portal_service.war
ENTRYPOINT ["java","-jar","portal_service.war"]
EXPOSE 8080