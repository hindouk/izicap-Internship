# izicap-Internship

## Idea of homework project 

Plain Text

  As an AI language model, I can to communicate through a REST API endpoint. And I can expose an endpoint for me to receive requests and respond with text-based outputs.
 
 ### The planning of project:
 
 Plain Test
 
  + Create a new Spring Boot “Izicap_internship” project and add the necessary dependencies of: **springboot** , **swagger** , **aven plugin** , **mysql** and **csv**
  + Use the architecture MVC in the two endponits:
     • The first endpoint service: communicate through a REST API endpoint
         - Create a class to handle the API requests and responses. So, I use RestTemplate to make the API calls, and passing the question that I want to ask to the API as a body of the request.
         - Parse the API response to extract the answer from the API  
         - Use JPA Repository interface to store questions and answers in a MySQL database. This is a common approach in Java-based web applications that use JPA (Java Persistence API) to manage database interactions “chatgpt_db”.
           
    • The last endpoint service: expose an endpoint
         - Expose the endpoint that your microservice will use to receive questions from your clients and return answers from the API by exporting a csvFile 
           
    • Create a controller class to handled incoming HTTP requests and returns HTTP responses With the Request methods Post and Get 
         
    • Create a Entity class has three fields: "id" of type UUID and "content" and "answer" of type String, which store the question and its answer respectively
    
     __Note__ : I use **Postman** for testing the requests methods Post and Get and the my source code performance
     
  ### Prerequisite :
  
        + Docker
        + Git
        
  ### Optional :
  
        + Java 17
        + Maven 3.x
        
   ### The new notation: Swagger:
   
   Swagger notation (also known as OpenAPI specification) is a standardized format for describing RESTful APIs, including the available endpoints, input/output data format, authentication methods, and other details. It uses YAML or JSON syntax to define the structure of the API, making it easier to generate client code, document the API, and test its functionality.
   
   > I'am a bockquote
   '''
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
    '''    
    
    
    ### Using docker container
    
      + Create a simple spring boot application.
      + Create a docker file under parent directory.
      + Paste the following code in the docker file.
      
      '''
        FROM openjdk:17-jdk
        FROM mysql:latest
        ENV MYSQL_DATABASE=chatgpt_db
        ENV MYSQL_ROOT_PASSWORD = ""
        WORKDIR /my-application
        COPY target/izicap-Internship-0.0.1-SNAPSHOT.jar /my-application/izicap-Internship-0.0.1-SNAPSHOT.jar
        EXPOSE 9090
        CMD service mysql start && java -jar izicap-Internship-0.0.1-SNAPSHOT.jar
      '''
      
      
     ### Steps to access of project:
         
      #### Clone source code from git
      
         '''
         git clone https://github.com/hindouk/izicap-Internship
         '''
         
      #### Build Docker image
      
         '''
         docker build -t="izicap-Internship-0.0.1-SNAPSHOT-java"
         '''
         
         Maven build will be executes during creation of the docker image.

      #### Run Docker Container
         
         '''
         docker run -p 9090:9090 -it --rm izicap-Internship-0.0.1-SNAPSHOT-java
         '''
         
      #### Test application
      
         '''
         curl localhost:9090/question
         '''
         
         I tested in Postman Tester : I ask it a question with type text and through REST API endpoint I receive the answer
         
         '''
         curl localhost:9090/export
         '''
         
          To download the csv file With name **questions**
         
      #### Stop Docker Container:
      
          '''
          docker stop `docker container ls | grep "izicap-Internship-0.0.1-SNAPSHOT-java:*" | awk '{ print $1 }'`
          '''

          



         
        
        
    
    

 
           
         
