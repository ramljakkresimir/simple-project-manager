# Project Management Application

Welcome to OG-CS Winter Internship!

In the following text you will read project requirements, coding and collaboration guidelines.

:warning: Read your task and guidelines carefully!

[See the board from introduction](https://excalidraw.com/#json=u01S_xBEVSAQZHQfmQFOo,sPtGX6P6pFgHMxQLhrAoXA)

## Diagram

![Component Diagram](/docs/component-diagram.drawio.png)

## User story

As a project manager, I want to develop a project management application that
allows us to input, manage, track multiple projects, assign users to those projects, and create features
with deadlines for each project. Users can be assigned to more than one project, and each
project can have several features tied to specific dates.

## Requirements

### Update 

As a Developer, I want to Dockerize the Spring Boot application, so it can run consistently across different environments.


As a Project Manager, I want to mark the actual delivery date of a feature and retrieve a list of features delivered within a specified time period (e.g. 07-2023 - 12-2023), so I can track project progress for specific projects or all projects.


As a Chief Financial Officer, I want to see which projects exceeded their equipment budget (project budget - sum of all equipment costs), so I can better manage future budgets. This information should be retrievable using query parameters in a RESTful API.


As a User, I want to claim a feature to indicate that I am responsible for its implementation, and I understand that only one user can claim a feature, but I can claim multiple features.


As a User, I want to see a list of unclaimed features and filter them by project, so I can identify tasks that need attention on specific projects.


As a User, I want to assign an optional person-day estimate to features without affecting their deadlines, so I can plan resources for both current and future features.


As a Project Manager, I want to receive a nicely formatted daily notification (at 8 AM) in the application logs listing project and feature deadlines within the next 7 days, so I can stay informed about imminent tasks.


As a Project Manager, I want to see the number of features assigned to each user in a specified project, so I can balance workloads effectively.


As a User, I want to see a breakdown of features in a project by their status (e.g., Not Started, In Progress, Completed), so I can track overall progress.

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
