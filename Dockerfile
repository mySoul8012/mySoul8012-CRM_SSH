FROM tomcat:9-jre11
EXPOSE 8080
RUN apt update
RUN apt install git -y
RUN apt install maven -y
RUN git clone https://github.com/mySoul8012/mySoul8012-CRM_SSH.git
WORKDIR /usr/local/tomcat/mySoul8012-CRM_SSH
RUN mvn package
RUN mvn test
WORKDIR /usr/local/tomcat/webapps
RUN  cp /usr/local/tomcat/mySoul8012-CRM_SSH/target/mingdiaoewww.war  ./
WORKDIR /usr/local/tomcat/
CMD ["catalina.sh", "run"]
