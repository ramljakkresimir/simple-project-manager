# Project Management Application

Welcome to OG-CS Winter Internship!

In the following text you will read project requirements, coding and collaboration guidelines.

:warning: Read your task and guidelines carefully!

## Diagram

![Component Diagram](/docs/component-diagram.drawio.png)

## User story

As a project manager, I want to develop a project management application that
allows us to input, manage, track multiple projects, assign users to those projects, and create features
with deadlines for each project. Users can be assigned to more than one project, and each
project can have several features tied to specific dates.

## Requirements

### Technical

- You are tasked with designing (database) and creating a backend service in Spring Boot
- Make sure your code adheres to the best programming practices
- You will use Postgres Database and run it inside Docker using Docker Compose file
- To configure the application, it is recommended to use the 'application.yml' file instead of 'application.properties'. This YAML-based configuration file allows for a more readable and organized approach to managing application properties.
- As a build tool, use Gradle instead of Maven.
- ***(OPTIONAL)*** For confidential information inside application.yml (database credentials, ...) use environment variables

### Organizational

- Divide the project into smaller tasks (e.g., User CRUD, Project CRUD, Feature CRUD, ...) so each team member can focus on specific modules while contributing equally
- Work together on key tasks like database design and project setup
- Keep in touch with your teammates to share progress and ideas (teams, discord, ...)
