#FROM tomcat:9.0-alpine
#LABEL maintainer="office@omilab.org"
#ADD target/portal_service.jar /usr/local/tomcat/webapps/
#ENTRYPOINT ["java","-jar","portal_service.jar"]
#EXPOSE 8080
#CMD ["catalina.sh", "run"]

#.war


#FROM openjdk:17
#LABEL maintainer="office@omilab.org"
#ADD target/portal_service.war portal_service.war
#ENTRYPOINT ["java","-jar","portal_service.war"]
#EXPOSE 8080'

# Use oracle linux 8 as base image as it is what "FROM openjdk:17" defaults to
FROM oraclelinux:8

LABEL maintainer="office@omilab.org"

# Install OpenJDK 17 for our Java application
RUN yum -y update && \
    yum -y install java-17-openjdk java-17-openjdk-devel mysql mysql-server && \
    yum clean all && \
    rm -rf /var/cache/yum

    # Enable the MySQL service
RUN systemctl enable mysqld

# Set the default command to run systemd
CMD ["/usr/sbin/init"]

ADD target/portal_service.war portal_service.war
ENTRYPOINT ["java","-jar","portal_service.war"]
EXPOSE 8080