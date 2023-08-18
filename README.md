ToDo application is developed in Java 17 using Spring Boot.

The application provides the ability to use the task management functionality: create, delete, very task, change their status.
Only an authorized user can access the task. Implementation authentication and authorization procedure using Spring Boot. Passwords are stored in the database in a hashed form,
and after successful authentication, the user uses a JWT token with a duration of 60 min.

## Installation  
Run the following command to start the application using Docker Compose:    
```  
docker-compose up
```
Welcome to the application:  
http://localhost:8070
