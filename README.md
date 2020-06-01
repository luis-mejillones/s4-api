# s4-api
Demo for Super Simple Scheduling System

It's a very simple and small API to demonstrate how to create REST API application which can be used in any application.

This API permit create and persist students data, class data and enroll and/or disenroll to/from any class
 
## Technology
This API has been developed using the next technologies:
- Java as a main programing language.
- Play Framework which permit administration the entire project.
- MongoDB as database to persist information.
- Docker to permit create a dockerized instance of MongoDB.
- SBT as a compiler tool also administrate packages.

## Compilation
- Checkout the project.
- Enter to `s4-api` folder
- Enter to folder `docker-images` to start the docker image.
- Run the command `$ docker-compose up -d` it will run the docker as daemon mode.
- Return to folder `s4-api`
- Run the command `$ sbt clean compile`
- To run the application run the command `$ sbt runProd`

After that the server application will run under port 9000, to check if its ok, just goto a navigator and open the url `http:localhost:9000` and it should display a message **"Welcome to Play!"**

## API Examples
Here are some examples to create students, classes and enroll to some.

**Student stuff**

Create a student

`POST 'http://localhost:9000/student'`

Payload

`{
   "id": "100",
   "lastName": "John",
   "firstName": "Doe"
 }` 

Retrieve student

`GET http://localhost:9000/student/100`

Response

`{
    "id": "100",
    "lastName": "John",
    "firstName": "Doe"
}`

When some error occur, this kind of message is received

`GET http://localhost:9000/student/101`

`{
    "status": 404,
    "body": "Student not found"
}`

Enroll a student to some class

`POST http://localhost:9000/class/MAT-100/student/100/enroll`

Retrieve courses for a student

`GET http://localhost:9000/student/100/classes`

Response:

`[
    {
        "id": "3EACAD9E86AE4AEE82EB1E8F466FA3E9",
        "classId": "MAT-100",
        "studentId": "100"
    }
]`
