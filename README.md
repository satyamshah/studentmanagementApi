Student Management API Documentation
Overview
The Student Management API allows clients to perform CRUD operations on student data. The API provides endpoints to retrieve, create, update, and delete student records.

Base URL
perl
Copy code
http://<your-server-domain>/api/v1
Response Format
All responses are in JSON format.
Success responses include relevant data or success messages.
Error responses include an appropriate HTTP status code and an error message.
Endpoints
1. Get All Students
Request
Endpoint: /getStudent
Method: GET
Description: Fetches a list of all students.
Response
Success (200 OK):
json
Copy code
[
    {
        "id": 1,
        "name": "John Doe",
        "age": 20,
        "classname": "12th Grade",
        "phonenumber": "1234567890"
    },
    {
        "id": 2,
        "name": "Jane Doe",
        "age": 18,
        "classname": "11th Grade",
        "phonenumber": "0987654321"
    }
]
Error (500 Internal Server Error):
json
Copy code
{
    "error": "Error occurred while fetching students"
}
2. Add a New Student
Request
Endpoint: /addstudents
Method: POST
Description: Adds a new student to the database.
Request Body:
json
Copy code
{
    "name": "John Doe",
    "age": 20,
    "classname": "12th Grade",
    "phonenumber": "1234567890"
}
Response
Success (201 Created):
json
Copy code
{
    "id": 1,
    "name": "John Doe",
    "age": 20,
    "classname": "12th Grade",
    "phonenumber": "1234567890"
}
Error (500 Internal Server Error):
json
Copy code
{
    "error": "Error occurred while adding student"
}
3. Edit a Student
Request
Endpoint: /editstudents
Method: PUT
Description: Updates an existing student's information.
Request Body:
json
Copy code
{
    "id": 1,
    "name": "John Smith",
    "age": 21,
    "classname": "College Freshman",
    "phonenumber": "1234567890"
}
Response
Success (200 OK):
json
Copy code
{
    "id": 1,
    "name": "John Smith",
    "age": 21,
    "classname": "College Freshman",
    "phonenumber": "1234567890"
}
Error (404 Not Found):
json
Copy code
{
    "error": "Student with ID 1 not found"
}
Error (500 Internal Server Error):
json
Copy code
{
    "error": "Error occurred while editing student"
}
4. Delete a Student
Request
Endpoint: /students/{id}
Method: DELETE
Description: Deletes a student record based on the provided ID.
Path Parameter:
id (Long) - The ID of the student to delete.
Response
Success (200 OK):
json
Copy code
{
    "message": "Student deleted successfully"
}
Error (404 Not Found):
json
Copy code
{
    "error": "Student not found"
}
Error (500 Internal Server Error):
json
Copy code
{
    "error": "Error occurred while deleting student"
}
