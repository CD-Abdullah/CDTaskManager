# Task Manager CLI

The Task Manager CLI is a command-line interface application designed to manage tasks efficiently. Built using Java and PostgreSQL, this application allows users to sign in with predefined credentials and perform various operations related to task management such as adding, viewing, updating, and deleting tasks.

## Features

- **User Authentication**: Sign in to access personalized task management.
- **Task Management**: Add, view, update, and delete tasks.
- **Personalization**: Each user can only interact with their tasks.

## ERD

![ERD TaskManager](https://github.com/CD-Abdullah/CDTaskManager/assets/174435638/a0cd115f-42b1-4491-aed8-d9ab094032e8)


## Prerequisites

- Java JDK 11 or higher
- PostgreSQL
- IntelliJ IDEA (Recommended IDE)

## Database Setup

First, you need to set up the PostgreSQL database. Below are the SQL commands to create the necessary database and tables:

```sql
-- Create the database
CREATE DATABASE TaskManager;

-- Connect to the database
\c TaskManager

-- Create the Users table
CREATE TABLE public.Users (
    UID serial PRIMARY KEY,
    UName text NOT NULL,
    UPass text NOT NULL
);

-- Create the Tasks table
CREATE TABLE public.Tasks (
    TID serial PRIMARY KEY,
    UID integer NOT NULL REFERENCES public.Users(UID),
    TTitle text NOT NULL,
    TDesc text NOT NULL
);
```
## Configuration

Before running the application, configure the database connection settings in the dbconnect.java file. Adjust the port number, user, and password according to your PostgreSQL installation.

```java
public class dbconnect {

    private static String HOST = "localhost";
    private static int PORT = 5432; //Adjust Portnumber
    private static String DATABASE = "TaskManager";
    private static String USER = "postgres"; //Adjust User
    private static String PASS = "123"; //Adjust Password

// rest of the code
}
```
## Running the Application

1. **Clone the Repository**: Clone this repository to your local machine.

2. **Database Setup**: Setup Database using PostgreSQL as outlined above.
   
4. **Open the Project**: Open the project in IntelliJ IDEA.

5. **JDBC Driver**: Download the [pgJDBC driver](https://jdbc.postgresql.org/download/). Import the downloaded .jar file in your project's external libraries.

6. **Configure Database Connection**: Adjust the database connection settings in the dbconnect.java file (per your machine) as outlined above.
   
7. **Run the Application**: Navigate to `src/Main.java`, right-click on the file, and select `Run 'Main.main()'` to start the application.

## Usage

Follow the on-screen prompts to interact with the application:

- **Login**: Enter your username and password to log in.
- **View Tasks**: Displays all tasks associated with your user ID.
- **Add Tasks**: Allows you to add a new task with a title and description.
- **Update Tasks**: Update the title or description of an existing task.
- **Delete Tasks**: Remove an existing task by specifying its ID.

## Support

For queries, please contact me at [abdullah.basit@codedistrict.com](mailto:abdullah.basit@codedistrict.com).

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt) file for details.
