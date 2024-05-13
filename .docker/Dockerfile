FROM tomcat:9.0-alpine
LABEL maintainer="office@omilab.org"
ADD target/geocoder.war /usr/local/tomcat/webapps/geocoder.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
