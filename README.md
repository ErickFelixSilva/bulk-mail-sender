# Bulk Email Sender

This project is a demonstration full-stack application built with React and Tailwindcss for the frontend and Java with Spring Boot for the backend. My objective here is just to show basic frontend and backend programming skills, familiarity with good practices and with
all the technologies involved, which are listed below. The application allows users to manage nonprofits, create email templates, send bulk emails to selected nonprofits and check the lastly sent emails. 

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
- [Testing](#testing)
- [API Endpoints](#api-endpoints)
- [License](#license)

## Features

- Manage nonprofits (create, edit and delete)
- Create and edit the email template
- Send bulk emails to selected nonprofits
- Log and view sent emails
- Mark nonprofits as recently sent

## Technologies

### Frontend

- React
- TypeScript
- Tailwind CSS
- Axios
- Fontawesome

### Backend

- Java
- Spring Boot
- Spring Data JPA
- Spring Logging
- Lombok
- H2 Database
- Jakarta Validation

## Getting Started

### Prerequisites

- Node.js (v20.14.0)
- npm or yarn
- Java 22

### Installation

The applications setup is very simple, they are just basic Spring Boot and React applications. The most important is
to use compatible node and java versions which are Java 22 for the backend and Node.js 20.14.0 for the frontend.
The backend uses Maven as a build tool, but it is possible to use the maven wrapper to build and run the application.

The following steps will guide you through the installation process:

1. **Clone the repository**

   ```sh
   git clone https://github.com/yourusername/fullstack-email-app.git
   cd fullstack-email-app
   ```
2. **Backend Setup**

 - Navigate to the backend directory:
   
   ```sh
   cd backend
   ```

 - Build the project with local maven:
   
   ```sh
   mvn clean install
   ```
 - For maven commands, if there is no local maven installed, it is possible to use the maven wrapper this way:

      ```sh
      mvnw.cmd clean install
      ```
  
 - Start the backend server with local maven:
   
   ```sh
   mvn spring-boot:run
   ```
 - Start the backend server with maven wrapper:
   
   ```sh
   mvnw.cmd spring-boot:run
   ```

3. **Frontend Setup**

 - Navigate to the frontend directory:
   
   ```sh
   cd ../frontend
   ```

 - Install dependencies:
   
   ```sh
   npm install
   ```

 - Start the frontend development server:
   
   ```sh
   npm start
   ```

### Running the Application

- The backend server will be running at `http://localhost:8080`
- The frontend development server will be running at `http://localhost:3000`

## Usage

1. **Open the application in your browser**:
   - Navigate to `http://localhost:3000`
   - Use the navigation bar on the top to access the other functionalities.
   
2. **Create Email Templates**:
    - Create and save email template, if necessary.

3. **Manage Nonprofits**:
   - Add, edit, or delete nonprofits through the UI.

4. **Send Bulk Emails**:
   - Select nonprofits and send bulk emails using the saved template.

5. **View Sent Emails**:
   - Check the logs to see the details of sent emails.

## Testing

- To run the backend tests, use the following command:

  ```sh
  cd backend
  mvn test
  ```
- Or again, to use the maven wrapper:
    
  ```sh
  cd backend
  mvnw.cmd test
  ```
  
## API Endpoints

### Nonprofit Endpoints

- GET /api/nonprofits: Get all nonprofits
- GET /api/nonprofits/{id}: Get a nonprofit by ID
- POST /api/nonprofits: Create a new nonprofit
- PUT /api/nonprofits/{id}: Update a nonprofit 
- DELETE /api/nonprofits/{id}: Delete a nonprofit

### Email Template Endpoints

- GET /api/email-template: Get the email template
- POST /api/email-template: Save the email template

### Email Sending Endpoints

- POST /api/mails: Send emails to selected nonprofits using the current saved template 
- GET /api/mails/logs: Get logs of sent emails