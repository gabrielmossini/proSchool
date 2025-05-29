# proSchool ![status](https://img.shields.io/badge/status-no%20longer%20maintained-lightgrey)  

## 📌 Nota Técnica sobre este Projeto | Technical Note about this Project  

### 🇧🇷 Português  

Este projeto foi desenvolvido durante minha graduação em Ciência da Computação (2021-2024), como parte de uma disciplina prática voltada para o desenvolvimento de sistemas utilizando Java e Spring Boot.

**Motivação para manter o código inalterado:**  
Decidi manter este projeto em sua versão original, sem atualizações ou refatorações, para preservar a autenticidade e evidenciar o estágio técnico e conceitual que eu possuía à época. Considero importante demonstrar minha evolução como desenvolvedor ao longo do tempo, desde os primeiros códigos até os projetos mais sofisticados e alinhados com as melhores práticas atuais.

**Tecnologias utilizadas na época:**  
- Linguagem de programação: **Java**  
- Framework: Spring Boot  **Spring Boot**
- Banco de dados: **MySQL**
- Ambiente de desenvolvimento: **Linux (Ubuntu)**
- Ferramentas auxiliares: **Git para versionamento, Maven para gerenciamento de dependências**

**Escopo do projeto:**  
- Desenvolvimento de um sistema de gestão escolar, com funcionalidades de cadastro e gerenciamento de entidades como alunos, professores e turmas.
- Aplicação de princípios de Engenharia de Software, incluindo modelagem UML e definição de requisitos.
- Estruturação do backend utilizando o ecossistema Spring Boot e organização de dependências com Maven.

**Pontos que poderiam ser aprimorados atualmente:**
- Aplicação consistente de boas práticas de engenharia de software, como modularização, testes unitários e integração contínua e deployment automatizado (CI/CD).  
- Uso de frameworks de testes como JUnit e Mockito.  
- Adoção de ferramentas de análise estática de código.
- Adoção de padrões de arquitetura como Clean Architecture ou Hexagonal Architecture.
- Uso de contêineres (Docker) para facilitar a implantação.  
- Atualização para versões mais recentes do Spring Boot.
- Integração com banco de dados real, utilizando Spring Data JPA.
- Implementação de uma interface frontend moderna com frameworks como React ou Angular.

**Reflexão pessoal:**  
Este projeto representa um marco importante na minha trajetória, quando comecei a aplicar de forma prática os conceitos de desenvolvimento web e backend com Java. Embora hoje eu possua uma visão mais madura sobre design de software, arquitetura de sistemas e boas práticas, opto por manter este trabalho como registro fiel dessa etapa formativa.  

**Observação:**  
Para projetos mais recentes e que refletem meu nível técnico atual, consulte outros repositórios no meu portfólio.


---  

### 🇬🇧 English  

This project was developed during my undergraduate studies in Computer Science (2021-2024), as part of a practical course focused on developing systems using Java and Spring Boot.  

**Reason for keeping the code unchanged:**  
I decided to keep this project in its original version, without updates or refactoring, to preserve its authenticity and to showcase the technical and conceptual level I had at the time. I believe it is important to demonstrate my evolution as a developer over time, from my earliest code to more sophisticated projects aligned with current best practices.  

**Technologies used at the time:**  
- Programming language: **Java**
- Framework: **Spring Boot**
- Database: **MySQL**
- Development environment: **Linux (Ubuntu)**  
- Auxiliary tools: **Git for version control, Maven for dependency management**

**Project scope:**  
- Development of a school management system, including features for registering and managing entities such as students, teachers, and classes.
- Application of Software Engineering principles, including UML modeling and requirements specification.  
- Backend structuring using the Spring Boot ecosystem and dependency organization with Maven.

**Aspects that could be improved today:**  
- Consistent application of software engineering best practices, such as modularization, unit testing, continuous integration and automatic deployment (CI/CD).  
- Use of testing frameworks like JUnit and Mockito.  
- Adoption of static code analysis tools.
- Adoption of architectural patterns like Clean Architecture or Hexagonal Architecture.
- Use of containers (Docker) to facilitate deployment.
- Update to the latest versions of Spring Boot.
- Integration with a real database using Spring Data JPA.
- Development of a modern frontend interface with frameworks such as React or Angular.

**Personal reflection:**  
This project represents an important milestone in my journey, when I began to apply web and backend development concepts with Java in a practical way. Although today I have a more mature understanding of software design, system architecture, and best practices, I choose to keep this work as a faithful record of that formative stage.  

**Note:**  
For more recent projects that reflect my current technical level, please check other repositories in my portfolio.

---  

## 🚀 Como Executar | How to Run

Este projeto foi desenvolvido e testado em **Linux (Ubuntu)**. | This project was developed and tested on **Linux (Ubuntu)**


**Pré-requisitos: | Prerequisites**
- Java 11 ou superior | Java 11 or higher
- Maven  

**Passos para execução: | Steps to run**  
```bash
# Clone o repositório
# Clone the repository
git clone https://github.com/gabrielmossini/proSchool.git
cd proSchool

# Compile o projeto
# Build the project
mvn clean install

# Execute a aplicação
# Run the application
mvn spring-boot:run
