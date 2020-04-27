# WordCounter

WordCounter Application(see application description at [ApplicationDescription.md](./ApplicationDescription.md)) code and test

Introduction

Thank you for downloading the sample WordCounter Service project

System Requirements & Installation

Java version: JDK 1.8
Spring Boot v2.2.6.RELEASE


Installation
- Clone the repo to file system a location.$ ``git clone https://github.com/esaver/WordCounter.git``
- Create new project from existing source 
- Run $ `mvn clean install` to compile and install project
- Run $ `mvn spring-boot:run` to start the application

TEST DATA

Word Counter Application test data and its responses
1. Add word
For `http://localhost:8080/addWord/{word}` endpoint
 
  **Word**   ---->  **Response** 
- hello  ---->  Http Status 200 OK
- hola   ---->  Http Status 200 OK
- hallo  ---->  Http Status 200 OK
- flower ---->  Http Status 200 OK
- flor   ---->  Http Status 200 OK
- blume  ---->  Http Status 200 OK

2. Get added word count
For `http://localhost:8080/wordCount/{word}` endpoint

  **Word**   ---->  **Response** 
- hello  ---->  1 Http Status 200 OK
- hola   ---->  2 Http Status 200 OK
- hallo  ---->  3 Http Status 200 OK
- flower ---->  1 Http Status 200 OK
- flor   ---->  2 Http Status 200 OK
- blume  ---->  3 Http Status 200 OK


Test GET Requests

1. Valid word to add
http://localhost:8080/addWord/hello

2. Valid same (spanish word for hello ) word in different language to add 
http://localhost:8080/addWord/hola

3. Get word count for the word added above
http://localhost:8080/wordCount/hello

4. Get word count for the word added above in different language
http://localhost:8080/wordCount/hola
