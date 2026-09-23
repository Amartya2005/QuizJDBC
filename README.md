# JDBC Quiz Application

This project is a simple console-based JDBC Quiz System built using Java and MySQL.
It demonstrates how to establish a JDBC connection, use `PreparedStatement` and `CallableStatement`, and interact with a relational database to conduct a quiz, store responses, and calculate results.

The project is designed to be easy to understand and suitable for academic labs, practical exams, and viva discussions.

## Features

- Student login using roll number and password
- Secure database queries using `PreparedStatement`
- Quiz questions fetched dynamically from the database
- Multiple-choice questions with shuffled options
- Student answers stored in the database
- Final score calculated using a stored procedure
- `CallableStatement` used to retrieve the result
- Clean separation of responsibilities across classes

## Technologies Used

- Java (JDK 8 or above)
- MySQL
- JDBC (MySQL Connector/J)
- IntelliJ IDEA (IDE)

## Database Schema

### `student`

| Column | Type |
| --- | --- |
| `roll_no` | VARCHAR |
| `password` | VARCHAR |
| `name` | VARCHAR |

### `question`

| Column | Type |
| --- | --- |
| `q_id` | INT (PK, Auto Increment) |
| `question_text` | VARCHAR |
| `option_a` | VARCHAR |
| `option_b` | VARCHAR |
| `option_c` | VARCHAR |
| `option_d` | VARCHAR |
| `correct_option` | CHAR |

### `result`

| Column | Type |
| --- | --- |
| `result_id` | INT (PK, Auto Increment) |
| `roll_no` | VARCHAR (FK) |
| `q_id` | INT (FK) |
| `selected_option` | CHAR |
| `is_correct` | BOOLEAN |

## Stored Procedure

The project uses a stored procedure to calculate the final score:

```sql
calculate_score(IN roll_no, OUT score)
```

It counts the number of correct answers for a given student. This procedure is executed from Java using a `CallableStatement`.

## Project Structure

```text
Quiz/
│
├── DBConnection.java      // JDBC connection logic
├── Main.java              // Application entry point
├── Login.java             // Student authentication
├── Quiz.java              // Quiz logic and answer storage
├── ResultService.java     // Result calculation using CallableStatement
```

## Application Flow

1. Application starts and establishes a database connection.
2. Student logs in using roll number and password.
3. Quiz questions are fetched from the database.
4. Options are shuffled and displayed.
5. Student answers are recorded in the `result` table.
6. The stored procedure is called to calculate the score.
7. The final score is displayed.

## How to Run

1. Create the required database and tables in MySQL.
2. Insert at least one student and some questions.
3. Update the database URL, username, and password in `DBConnection.java`.
4. Add MySQL Connector/J to the project classpath.
5. Run `Main.java`.

## Why PreparedStatement and CallableStatement Are Used

`PreparedStatement` is used to prevent SQL injection and handle parameterized queries efficiently.

`CallableStatement` is used to execute the stored procedure that calculates the final score.

## Learning Outcomes

- Understanding JDBC architecture
- Secure database interaction using `PreparedStatement`
- Executing stored procedures from Java
- Handling `ResultSet` and database-driven applications
- Designing a simple end-to-end database application

## Notes

- This is a console-based application for simplicity.
- Error handling is kept minimal for clarity.
- The project focuses on JDBC concepts rather than UI.

## Conclusion

This project demonstrates a complete JDBC workflow, from connection establishment to secure querying and stored procedure execution. It is suitable as a learning project, lab submission, or reference for basic Java-MySQL integration.
