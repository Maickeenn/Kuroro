# Start with a base image containing Java runtime (Amazon Corretto 21)
FROM amazoncorretto:21

# Install mysql-client
RUN yum install -y https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
RUN yum install -y --nogpgcheck mysql-community-client

# Add Maintainer Info
LABEL maintainer="maickeenn@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/Kuroro-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} Kuroro-0.0.1-SNAPSHOT.jar

# Copy the script
COPY wait-for-it.sh /wait-for-it.sh

# Make the script executable
RUN chmod +x /wait-for-it.sh

# Run the jar file
ENTRYPOINT ["/wait-for-it.sh", "db", "--", "java", "-jar", "/Kuroro-0.0.1-SNAPSHOT.jar"]
