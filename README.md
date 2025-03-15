# MedScheduler - Documentation
Web application with Java support, SpringMVC and connection to the PostgreSQL Server database. <br>
Project made by Alexandru Oancea <br>
Date: January 2025 <br>

## General Description
This web application is developed to facilitate the management of medical consultations in a clinic. The system allows adding, editing, and deleting patients and consultations, integrating a PostgreSQL database for handling information. The application is built using Spring Boot, JDBC, and Thymeleaf for the frontend, while database interactions are managed through well-defined backend services.

## Main Functionalities
Since we are talking about the management of consultations in a medical clinic, we have considered two important entities: Patient and Consultation, entities that we manipulated in a database that we saved locally using the PostgreSQL application. There is a one-to-many relationship between the two entities, namely a patient can schedule multiple consultations (we considered a simple case that allows a single appointment in a day for a patient). The database is connected to the application and is constantly updated based on the user's activity on the site. As for the user, he can add, edit and remove a patient from the patient list. Adding data is done by opening a form where all the details about the patient are requested (the same patient cannot be entered more than once) according to some criteria (such as: the email must contain @, the CNP must contain 13 characters). The editing operation is also carried out by accessing a form where we can update the data that can be modified (the CNP cannot be modified). Regarding patients, we have the option to delete a patient from the list, the system asking a "question" if we are sure of this action. Regarding the consultations, we have several standard options available, such as viewing all consultations with the option to cancel (delete), adding a new consultation for a patient from the existing list using their CNP (if the patient is not on the list, the appointment cannot be made). <br>

In addition to the basic functionalities listed above, we have also added some additional ones. For the section intended for patients, we have added a search engine for them by CNP (the CNP is entered in a form, and if the patient exists, his details will be displayed with the option to modify or delete, if the patient does not exist, a message will be displayed). For the consultations section, we can sort the consultations by two criteria: by their date and by the specialization of the consultation, and also another important functionality is given by searching for a consultation by the patient's CNP and by the date of the consultation (we considered that the result should be unique).

Patient Management:
  + Add new patients.
  + Edit existing patient information.
  + Delete patients with confirmation.
  + Search for patients by CNP.

Consultation Management:
  + Add a consultation for an existing patient.
  + View existing consultations.
  + Filter consultations by date and specialization.
  + Search consultations by CNP and date.
  + Delete existing consultations.

Future Enhancements:
  + User authentication and role-based access.
  + Export reports and automated notifications for upcoming consultations.
## Program Structure
The project is organized into the following modules:
  + Controller - Handles HTTP requests and user interactions.
  + Service - Contains business logic and database queries.
  + Model - Represents the database entity structure.
  + Repository - Manages PostgreSQL database interaction using JDBC.
  + View - HTML + Thymeleaf pages for the frontend.
## Main Classes and Methods
1. ConsultationController (Managing Consultations)
  + @GetMapping("/") - Displays the main page.
  + @GetMapping("/consultations") - Lists consultations with sorting options.
  + @PostMapping("/consultations/save") - Saves a new consultation after verifying patient existence.
  + @PostMapping("/consultations/delete/{id}") - Deletes a consultation by ID.
  + @GetMapping("/consultations/search") - Searches consultations by CNP and date.

2. PatientController (Managing Patients)
  + @GetMapping("/patients") - Lists patients sorted by a specified criterion.
  + @PostMapping("/patients/save") - Adds a new patient with validations for CNP and email.
  + @GetMapping("/patients/search") - Searches for a patient by CNP.
  + @PostMapping("/patients/delete/{id}") - Deletes a patient by ID.
  + @GetMapping("/patients/edit/{id}") - Displays the patient edit form.

3. ConsultationService (Consultation Services)
  + getAllConsultationsSortedBy(String sortBy) - Returns all consultations sorted by a criterion.
  + findPatientIdByCnp(String cnp) - Finds a patient ID by CNP.
  + hasConsultationOnSameDay(int patientId, String date) - Checks if a patient already has a consultation on a given day.
  + saveConsultation(...) - Saves a consultation in the database.
  + deleteConsultationById(int id) - Deletes a consultation by ID.

4. PatientService (Patient Services)
  + getAllPatientsSorted(String sortBy) - Returns the list of patients sorted by a criterion.
  + existsByCnp(String cnp) - Checks if a patient exists by CNP.
  + addPatient(Patient patient) - Adds a new patient to the database.
  + deletePatientById(Long id) - Deletes a patient by ID.
  + updatePatient(...) - Updates patient information.
## Running the Program
1. Prerequisites
  + Java 17+
  + Maven
  + PostgreSQL (ensure the database is properly configured)
  + IDE (IntelliJ IDEA, Eclipse, or VS Code)

2. Steps to Run
  + Clone the repository:
  ```
  git clone https://github.com/user/project_awj.git
  cd project_awj
  ```
  + Configure the PostgreSQL database according to application.properties.
  + Build the project with Maven:
  ```
  mvn clean install
  ```
  +  Run the Spring Boot application:
  ```
  mvn spring-boot:run
  ```
Access the application in a browser at http://localhost:8080
## Conclusion
This project provides a solid foundation for managing medical consultations efficiently. The integration of Spring Boot, PostgreSQL, and Thymeleaf ensures a scalable and maintainable architecture. The functionalities developed so far facilitate patient and consultation management, reducing administrative errors and improving workflow.

Future Improvements:
  + User Authentication & Roles: Implement login systems with different access levels (admin, doctor, receptionist, etc.).
  + Medical History Management: Add a patient history section where doctors can track previous consultations and treatments.
  + Notification System: Enable email or SMS reminders for upcoming consultations.
  + Reporting & Analytics: Generate PDF/Excel reports with patient and consultation statistics.
  + Appointment Scheduling Enhancements: Introduce availability checks and automatic time-slot suggestions for new consultations.
  + With these future upgrades, the system can evolve into a fully-fledged medical appointment management platform.

__Thank you!__

Project made by Alexandru Oancea <br>
Email: alexandruoancea49@gmail.com
