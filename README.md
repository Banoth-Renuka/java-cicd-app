# java-cicd-app
Jenkins ci/cd for java app

# 🚀 Java CI/CD Pipeline with Jenkins, Maven, and Docker (DevOps Demo)

This project is a demonstration of a full **Continuous Integration/Continuous Delivery (CI/CD)** pipeline for a simple Java application built with **Maven**. The pipeline uses Jenkins to automate the build, security scanning, artifact storage in Nexus, and deployment using Docker.

## 🌟 Project Overview

This repository contains all the necessary files to define an end-to-end automation workflow, showcasing fundamental DevOps skills:

| Component | Technology | Purpose |
| :--- | :--- | :--- |
| **Source Code** | Java 11 / Maven | The basic application logic. |
| **Build & CI Engine** | **Jenkins** | Orchestrates the entire pipeline via the `Jenkinsfile`. |
| **Artifact Storage** | **Nexus Repository** | Stores the finalized `.jar` artifact for security and traceability. |
| **Code Quality/SAST**| **SonarQube** | Used in the pipeline to perform static code analysis and enforce quality gates. |
| **Deployment** | **Docker** | Packages the application into a container for consistent, portable deployment. |

## 🛠️ Prerequisites

To run this pipeline successfully, you must have the following infrastructure components set up and accessible by your Jenkins server:

1.  **Jenkins Server:** Running instance with **Maven** and **JDK 11+** installed/configured via Global Tool Configuration.
2.  **Docker Registry:** Access to a registry like **Docker Hub** or **AWS ECR** to push the final image.
3.  **Nexus Repository:** Running instance with dedicated `maven-releases` and `maven-snapshots` repositories.
4.  **SonarQube Server:** Running instance for static code analysis.
5.  **Remote Target Host:** A separate machine (e.g., an EC2 instance) with **Docker** and **SSH access** for the final deployment stage.

## 📁 Project Structure
java-cicd-app/
├── src/
│ └── main/
│ ├── java/com/example/App.java
│ └── resources/
├── pom.xml # Maven build file
├── Dockerfile # Multi-stage Docker build
├── Jenkinsfile # CI/CD pipeline script (added directly in Jenkins)
└── deployment/
└── docker-compose.yml

## ⚙️ Jenkins Pipeline Stages (`Jenkinsfile`)

The pipeline is defined in the `Jenkinsfile` and executes the following stages sequentially:

1.  **Checkout:** Pulls the latest code from the GitHub repository.
2.  **Build & Test:** Executes `mvn clean install` to compile the Java code and run any unit tests.
3.  **SonarQube Analysis & Quality Gate:** Runs `mvn sonar:sonar`, and then uses `waitForQualityGate` to **fail the pipeline** if the code quality does not meet predefined standards.
4.  **Publish to Nexus (Artifact Storage):** Executes `mvn deploy` to push the built `.jar` file to the specified Nexus repository, versioning the artifact.
5.  **Docker Build & Push:** Builds the Docker image based on the `Dockerfile`, tags it with the Git commit hash, logs into the Docker registry, and pushes the image.
6.  **Deployment (Remote SSH):** Uses the `sshagent` block to connect to a remote server, pull the newly pushed Docker image, stop the old container, and start the new container, completing the Continuous Deployment cycle.
   
I understand. Here is the complete, single-block content for your professional **`README.md`** file, including the necessary headings and structure for your Java CI/CD project on GitHub.

```markdown
# 🚀 Java CI/CD Pipeline with Jenkins, Maven, and Docker (DevOps Demo)

This project demonstrates a full **Continuous Integration/Continuous Delivery (CI/CD)** pipeline for a simple Java application built with **Maven**. The workflow uses a **Jenkinsfile** to automate the build, code quality checks (SonarQube), artifact storage (Nexus), and deployment (Docker/SSH).

## 🌟 Project Highlights

| Component | Technology | Purpose in Pipeline |
| :--- | :--- | :--- |
| **Source Code** | Java 11 / Maven | The core application and build definitions. |
| **Orchestration** | **Jenkins** | Drives the entire pipeline using the `Jenkinsfile`. |
| **Code Quality** | **SonarQube** | Enforces code standards and security via a **Quality Gate**. |
| **Artifact Storage** | **Nexus Repository** | Securely stores the final `.jar` file after testing. |
| **Deployment** | **Docker / Remote SSH** | Packages the app into a container and deploys it to a remote host. |

## ⚙️ Jenkins Pipeline Stages (`Jenkinsfile`)

The pipeline runs sequentially, enforcing quality checks before proceeding to delivery:

1.  **Checkout:** Clones the code from GitHub.
2.  **Build & Test:** Executes `mvn clean install`.
3.  **SonarQube Analysis & Quality Gate:** Scans the code and halts the build if standards are not met.
4.  **Publish to Nexus:** Executes `mvn deploy` to store the `.jar` artifact.
5.  **Docker Build & Push:** Builds the Docker image, tags it with the Git commit hash, and pushes it to a container registry.
6.  **Deployment (Remote SSH):** Connects to the target server, pulls the new image, stops the old container, and starts the new application container.

## 🗂️ File Structure

```

java-cicd-app/
├── src/                            \# Java source code
├── Jenkinsfile                     \# Pipeline script
├── pom.xml                         \# Maven build file (includes Nexus config)
├── Dockerfile                      \# Docker image definition
├── .gitignore                      \# Ignores /target, IDE files
└── LICENSE                         \# MIT License

```

## ⚠️ Setup & Configuration Notes

To successfully run this pipeline, you must configure the following in your Jenkins instance:

* **Tools:** The names **`M3`** (Maven) and **`JDK11`** (Java) must be configured under **Manage Jenkins** > **Global Tool Configuration**.
* **Credentials:** You must define credentials for GitHub, Nexus, Docker Registry, and the remote SSH server in Jenkins's **Credentials Manager**.
* **`pom.xml`:** Ensure the **`<url>`** within the `<distributionManagement>` section points to your specific **Nexus repository IP/URL**.
```



