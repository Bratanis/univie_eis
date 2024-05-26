FROM tomcat:9.0-alpine
LABEL maintainer="office@omilab.org"
ADD target/portal_service.war portal_service.war
EXPOSE 8080
CMD ["catalina.sh", "run"]