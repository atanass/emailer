#FROM ubuntu:latest
FROM jboss/wildfly:latest
ADD target/mail-1.0.war /opt/jboss/wildfly/standalone/deployments/

MAINTAINER Atanas Dichev <dichev.atanas@gmail.com>
