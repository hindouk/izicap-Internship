# Create the Base image
FROM openjdk:17-jdk

# Use an official MySQL image as the base image
FROM mysql:latest

# Set environment variables
ENV MYSQL_DATABASE=chatgpt_db
ENV MYSQL_ROOT_PASSWORD = ""


#set the directory
WORKDIR /my-application

# Copy the jar file to the image
COPY target/izicap-Internship-0.0.1-SNAPSHOT.jar /my-application/izicap-Internship-0.0.1-SNAPSHOT.jar

#Set the port
EXPOSE 9090

# Start the MySQL service
CMD service mysql start && java -jar izicap-Internship-0.0.1-SNAPSHOT.jar




#Build the Docker Image
#docker build -t izicap-Internship-0.0.1-SNAPSHOT

#Run the Docker Container
#docker run -p 9090:9090 izicap-Internship-0.0.1-SNAPSHOT.jar