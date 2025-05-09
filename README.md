# ☎️ Java Web Application – Telephone Management System  
![Login Page](/login.png)

Welcome to the official repository of the **Telephone Management System**, a dynamic Java-based web application designed to manage user telephone records, powered by **Java EE**, **JSP**, **Servlets**, and **MySQL**. This system supports CRUD operations, user authentication, and a clean admin interface — all running on **Apache Tomcat**.

---

## 🛠️ Tech Stack

- **Frontend:** HTML5, CSS3, JSP (JavaServer Pages)  
- **Backend:** Java (JDK 17), Servlets  
- **Database:** MySQL 8.x  
- **Server:** Apache Tomcat 9.x  
- **IDE:** Eclipse IDE for Enterprise Java  
- **Build Tool:** Apache Maven  
- **Architecture:** 3-Tier MVC (Model-View-Controller)  

---

## 🚀 Features

- 🔐 User login with session management  
- 📇 Add, update, and delete telephone records  
- 🧍 View and manage users and their phone details  
- ✅ Full CRUD operations via JDBC  
- 🔍 Search functionality for telephone records  
- 🖥️ Admin panel for managing the system  
- 📄 JSP-based frontend with dynamic server-side rendering  

---

## 🧱 Architecture

- **Presentation Layer:** HTML, CSS, JSP  
- **Controller Layer:** Java Servlets  
- **Model Layer:** Java classes & JDBC DAO  
- **Database Layer:** MySQL – structured via schema.sql  

The system follows the **MVC pattern** and separates concerns between business logic, data access, and presentation.

---

## 📚 Libraries & Dependencies

- **JDBC API** – for database interaction  
- **MySQL JDBC Driver** – for connectivity  
- **Jakarta Servlet API** – for HTTP request handling 

---

## 📦 Installation

### Prerequisites

- Java JDK 17+  
- Apache Tomcat 9.x  
- MySQL 8.x  
- Eclipse IDE for Enterprise Java Developers  
- Maven installed and configured  

---

### 🧩 Steps to Run the Project

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/YourUsername/CallManage.git
   cd CallManage
   ```
2. **Import into Eclipse:**

- Open Eclipse IDE.
- Go to `File > Import > Maven > Existing Maven Projects`.
- Select the project directory and click Finish.

3. **Configure the Database:**

   - Open **MySQL Workbench**.
   - Go to **File > Open Model** and select the `phonebills_database.mwb` file for your project.
   - Once the model is loaded, you can **reverse engineer** the schema into a MySQL database.
   
   To reverse-engineer the database:
   
   - Go to **Database > Reverse Engineer...**.
   - Select your **MySQL connection** and click **Continue**.
   - Choose the **Schema** you want to create (or create a new schema, e.g., `telephone_management`).
   - Follow the prompts to generate the database schema and tables based on your MWB model.
   
   After reverse engineering, you should have the required tables and relationships created in your MySQL database.

   - Next, configure your database connection by editing the properties:

     ```properties
     db.url=jdbc:mysql://localhost:3306/phonebills_database
     db.username=your_mysql_username
     db.password=your_mysql_password
     ```


## 👨‍💻 Developer
- Name: VoidG4
- GitHub: https://github.com/VoidG4

## 📝 License
This project is for educational and demonstration purposes. All rights reserved.