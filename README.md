# Employee Management System

A role-based employee management system with HR Admin and Employee portals. Originally a console application, being upgraded with a GUI.

## Original Project (Spring 2026)
- **Course:** CSC3350 Software Development (Group Project)
- **Original format:** Console-based Java application
- **Features:** Role-based access (HR Admin / Employee), MySQL database, CRUD operations, payroll reports


## Documentation

| Document                 | Google Docs                                                                                                                                                | PDF (Repo)                                |
| ------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- |
| Software Design Document | [View on Google Docs](https://drive.google.com/file/d/1059am8DYuQal34lb6Q8NK7_tFOh4BG4Q/view?usp=sharing)                                                  | [Download](/docs/SDD-DevOpsDynasty.pdf)  |
| User Story Task Document | [View on Google Docs](https://docs.google.com/document/d/1f8pj1QDKIFnflCI1rWLeREG18Cl8ajLx/edit?usp=sharing&ouid=117099564227884055012&rtpof=true&sd=true) | [Download](/docs/user-stories-tasks.pdf) |


## Improvents by Me
- [x] Software Design Document added to repository
- [x] Improved error handling and validation
- [x] Professional README with setup instructions
- [ ] GUI built with Java Swing / JavaFX (in progress)
- [ ] Additional reporting features (planned)


## My Roles

Due to uneven team participation, I served as the **de facto lead** - a role I stepped into to ensure project completion.

**My specific contributions:**
- Wrote the complete Software Design Document and programming tasks
- Defined and documented all test cases
- Created and managed the shared online database (Aiven)
- Debugged and completed partial work from team members (DataAccessLayer and 2-3 other files)
- Added missing functions, imports, and validation logic
- Drove all communication and coordination
- Completed the following files I was responsible for: AuthenticationController.java, Main.java

**What I learned about leadership:** Taking ownership when others don't, maintaining quality standards, and delivering a working product despite unreliable collaboration.

## Tech Stack
- **Language:** Java
- **Database:** MySQL (Aiven or local)
- **GUI (planned):** Java Swing / JavaFX
- **JDBC:** MySQL Connector

## How to Run

### Prerequisites
- Java 11 or higher
- MySQL database (Aiven or local instance)
- MySQL JDBC driver

### Setup

1. Clone the repository
```bash
git clone https://github.com/night-angel-dev/Employee-Management-System.git