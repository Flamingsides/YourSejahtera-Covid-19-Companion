# COVID-19 Symptom Checker (GUI)
###### Last Updated: 3rd March 2024
***[ENSURE ALL JAR DEPENDENCIES ARE INSTALLED BEFORE RUNNING](#referenced-libraries)***

## About The Project
### General Description
This Java Swing app primarily allows users to take a test which determines how many Covid-19 symptoms a person has. Based on that information, a user may be advised to take precautions and also linked to external webpages. To make this easier, the app allows users to enter their location and be informed of nearby clinics; this is accompanied by a friendly map to guide users. Furthermore, users may also view national Covid-19 statistics from the government in a line graph. The app tries to make the GUI simple and intuitive with clear buttons to switch from a page to another.

### Project Images

#### Login Page
![Login Page Image](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/22e25353-3649-4a44-8237-07eb007e278e)

#### Registration Page
![Registration Page Image](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/105cfcb0-328f-4184-aaf2-dfd7c352180b)

#### User's Dashboard (Shows Covid Trends)
![User's Dashboard Image](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/7c9e6c79-72d2-4c51-8573-1e237b10ac39)

#### Maps with Nearest Clinics
![Maps with Nearest Clinics Image](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/4d473803-b6da-493a-862b-b768bed7d728)

#### Covid-19 Symptoms Test
![Covid-19 Symptoms Test Form Image](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/7597afc6-5952-42b7-ba24-d237c2d06eb3)

#### Covid-19 Symptoms Test Results
![Covid-19 Symptoms Test Results](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/73b93f0b-0640-48e3-a083-287dd7d5dc24)

#### History Tab with Past Test Results for User
![History Tab with Past Test Results for User Image](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/assets/84507406/25f280aa-1ce5-4c38-abd0-c6089066812b)

### Contributers
##### Jei, Hao Wen (Howard)
- [Email: arsenalhw@gmail.com](mailto:arsenalhw@gmail.com)

##### Loh, Yue Hung (Cheerful)
- [LinkedIn: Yue Hung Loh](https://www.linkedin.com/in/yue-hung-loh-0bab63269/)

##### Tan, Li Qin
- [Email: lt2044@hw.ac.uk](lt2044@hw.ac.uk)

##### Zuberi, Suhaib Hameed
- [LinkedIn: Suhaib Hameed Zuberi](https://www.linkedin.com/in/suhaib-hameed-zuberi/)
- [GitHub: Flamingsides](https://github.com/Flamingsides)
- [Email: suhaib.zuberi2@gmail.com](mailto:suhaib.zuberi2@gmail.com)

### Features At A Glance
> Only features marked with **(Required)** were required by the course syllabus. Others were added due to personal motivation.
1. Covid-19 Symptoms Test **(Required)**
2. Gives users advice based on test results **(Required)**
3. Registering and Signing-in users *(multiple accounts supported)* **(Additional)**
4. Connected Database which stores user data **(Additional)**
5. Password Hashing for secure authentication **(Additional)**
6. Protection against SQL Injection Attacks **(Additional)**
7. API Accessing Features **(Additional)**
8. Locating nearest clinics **(Additional)**
9. Map View Presentation to reach located nearest clinics **(Additional)**
10. Reading Government data for national Covid-19 Statistics **(Additional)**
11. State-wise Filtering for Covid-19 Statistics **(Additional)**
12. Line Graph Presentation for national Covid-19 Statistics **(Additional)**
13. Added capability to redirect to browser links **(Additional)**
14. Links users to relevant health products **(Additional)**

### Technologies Used
- Java (Backend)
- Java Swing (Frontend)
- SQLite (Database)

### Referenced Libraries
See Also: [How To Add JAR files](#how-to-add-jar-files)

The following are external JAR libraries that we have used. They have been included under the [Dependencies](https://github.com/Flamingsides/YourSejahtera-Covid-19-Companion/tree/main/Dependencies) and need to be added in Eclipse ([See link above](#referenced-libraries)).

##### jcommon-1.0.23.jar
- _(Used to deal with JSON data from APIs)_

##### json-simple-1.1.1.jar
- _(Used to deal with JSON data from APIs)_

##### jfreechart-1.0.19.jar
- _(Used to plot charts to represent stored data)_

##### sqlite-jdbc-3.45.1.0.jar
- _(JDBC Driver to communicate with an SQLite database)_

##### slf4j-api-1.7.36.jar
- _(Required by JDBC SQL driver)_

##### slf4j-simple-1.6.1.jar
- _(Required by slf4j-api...)_

##### How To Add JAR files *(using Eclipse)*
1. Right click on your project
2. Select `Build Path`
3. Click on `Configure Build Path`
4. Ensure you are in the the `Libraries` tab
5. Select `Classpath` then click on `Add External JARs` from the right
6. Select the JAR file you'd like to add from the relevant folder
7. Click `Apply and Close`

## Detailed Analysis/Explanation of Files Written and Technologies Used
### Database (SQL) Structure
**Storing Users:**

```
CREATE TABLE users(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    username TEXT NOT NULL UNIQUE,
    hash INTEGER NOT NULL
);
```
> _Each user has a unique username and a hash of their password_

**Storing Covid-19 Test Results:**

```
CREATE TABLE history(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    time TIMESTAMP NOT NULL,
    symptoms_count INTEGER NOT NULL DEFAULT 0,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id)
);
```
> _Each log in `history` stores a timestamp of when a test result was stored and the number of symptoms recorded in the test. It also contains a Foreign Key referring to the owner of the task (user_id)._

**Creating Unique Indices**

```
CREATE UNIQUE INDEX id ON users(id);
CREATE UNIQUE INDEX username ON users(username);
```
> _The following unique indices were created knowing that the user's id would be referenced often. They help make queries faster._

### Classes
#### Main class
This class contains the main method which starts the app (an [App](#app-class) object).

#### App Class
The App class initialises `frame` of type `JFrame`. It also creates a default header and footer using `JPanel`s which appear on top of every page. Furthermore, App initialises the main body panel of type `JPanel` which uses a `CardLayout` to store the different pages. These pages can be opened using `App.showPage()` which takes in an argument from `App.pageNames`, a global `enum` which contains the name of each page, telling other parts of the program what pages are accessible. App also has a private variable `account` of type Account  which is used to handle account features for the current user (see [Account Class](#account-class)). This is also available for other parts of the program to call. Additionally, `App.initalisePages()` adds all the pages of type `Page` [See Page Class](#page-class)

#### Account Class
The Account handles Registration, Login, Logout, Data keeping functions.
It extends the Database Class to communicate with our SQLite Database (See [Database Class](#database-class))

#### Database Class
The Database Class is a custom class that offers functions to write to and read from our SQLite database. The database **MUST** be in the same folder as the project for this class to detect it.

#### Page Class
The Page class extends `JPanel` and adds methods that make it easy to communicate with `App` to communicate with the database or to switch to another page of type `Page` All classes that extend `Page` are treated as JPanels and are added to `App` to be accessible in the `CardLayout`.

#### `CoordinateDistance`, `CoordinatePoint`, `CoordinateTracker`
All these classes are implemented to get a user's coordinates, store them and to calculate distances considering the Earth is round.



