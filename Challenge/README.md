README

SETUP

Tools used for project. Please download using the link if you dont have it already

BE Language/Frameworks :  Java 8 + JEE -> https://java.com/es/download/
FE Language/Frameworks : Javascript ES6 + JQuery + Bootstrap
DB : MySQL Server 5.7.18 -> https://dev.mysql.com/downloads/  
Application Server : Tomcat 8 -> http://www-eu.apache.org/dist/tomcat/tomcat-8/v8.5.14/bin/apache-tomcat-8.5.14.exe


Setting Up MySQL, schemas and databases

While installing mysql dont check the boxes that require launching MySQL server as a service and at startup time since it is being used specifically for this project.
Instead, after installing, in the windows console execute the following commands with administrator priviledges:
	
	-C://Program Files/MySQL/MySQL Server 5.7/bin/mysqld --initialize-insecure 
	
	In an independent command console window launch mysql service by running the following command:
	
	-C://Program Files/MySQL/MySQL Server 5.7/bin/mysqld 
	
	Execute the following in a different window while mysqld service is running :
	
	-C://Program Files/MySQL/MySQL Server 5.7/bin/mysql -u "root"

	Then under mysql console execute the following queries to create user, schema and databases:
		
	- create user 'db_user'@'localhost' identified by 'Pass1234';

	- GRANT ALL ON *.* TO 'db_user'@'localhost' WITH GRANT OPTION;	
	
	- create schema if not exists challenge;
	
	- create table if not exists challenge.shoppers_applications(id int not null auto_increment,first_name varchar(60) not null ,  last_name varchar(60) not null ,  email varchar(200) not null ,   phone_number varchar(10) not null,  referral_code varchar(20) ,  PRIMARY KEY ( id ));

	- create table if not exists challenge.application_status( id int not null auto_increment, application_date datetime not null, quiz_status tinyint not null default 0, onboarding_status tinyint not null default 0, hiring_status tinyint not null default 0, constraint hiring_status check ( state in (0, 1, 2) ), constraint onboarding_status check ( state in (0, 1, 2) ), constraint hiring_status check ( state in (0, 1, 2) ), primary key ( id) );

Installing Tomcat 

	- While installing Tomcat 8 make sure to check the boxes 'Manager' and 'Host Manager' in the 'Choose Components' section
		
	- Give and take note of a proper user name and password for the tomcat administrator.

	- Finish and run tomcat

Deploying the app in tomcat	and start to use it

	- After running tomcat go to http://localhost:8080/manager/, type the user name and password used in the previous step.

	- Upload the WAR file provided in the project folder under Challenge/dist in the "WAR file to deploy" section and deploy
	
	- Go to http://localhost:8080/InstacartChallenge/ to go to webapp home page

PROJECT DEVELOPMENT

First Part 

	Front end
	
	*The front end is composed of the home page which prompts the user to apply for a Shopper job using a form that appears as soon as the Apply button is pressed. This form is constituted
	by 5 different fields : First Name, Last Name, Email, Phone number and Referral Code. After the user fills the form (made with bootstrap) and presses the Submit button, validation
	of form fields takes place. First Name, Last Name, Email and Phone number are mandatory. Email must also comply with the corresponding format (<username>@<SLD>.<TLD>) as well as cell phone_number 
	number (10 digits). 

	If validation is succesful the user will be sent to the background check page and the server will create a session in which the values entered by the user are persisted until a timeout 
	is reached (5 minutes), which is shown in the background page in the form of a countdown. In the following page the user will see notification and authorization messages regarding the 
	background check. He can also edit and update the information previously entered by clicking on the corresponding field values in the table. One feature that is important to mention and that
	allows to maintain user session across pages is that if the user tries to return to the home page via address bar url change the server will redirect him back to the background check page. 
	Hence, the only way to gracefully return to the home page and destroy user session is via the back button or submitting the information to the server to be persisted in the DB. 	

	Back End
	
	There are different services that take care of the requirements : one service to create the user session and persist the data(triggered by home page), one to update the session data (background check page)
	one to save the data in the DB (submit in the bakcground page) and one to destroy the user session (back button in the background page) 	
	
	DB	
	
	The DB table(challenge.shoppers_applications) is straightforward and consistent with the information requested to the shopper candidate.


Second Part

	DB 

	The table (challenge.application_status) has the following structure :
	
	* application_date : datetime type. Represents the date and time of the application  
	* quiz_status : tinyint type . Represents the status of the quiz . 0 for not started, 1 for started, 2 for completed.
	* onboarding_status : tinyint type. Represents the status of the onboarding. 0 for not requested, 1 for requested, 2 for completed.
	* hiring_status : tinyint type. Represents the status of the hiring. 0 for pending, 1 for hired, 2 for rejected

	Given this structure all the information can be retrieved in one query using the following sql: 
	
	SELECT Count(id)                                 AS applied, 
		   Year(application_date)                    AS app_year, 
		   Weekofyear(application_date)              AS app_week, 
		   Count(IF(quiz_status > 0, 1, NULL))       AS quiz_started, 
		   Count(IF(quiz_status = 2, 1, NULL))       AS quiz_completed, 
		   Count(IF(onboarding_status > 0, 1, NULL)) AS onboarding_requested, 
		   Count(IF(onboarding_status = 2, 1, NULL)) AS onboarding_completed, 
		   Count(IF(hiring_status = 1, 1, NULL))     AS hired, 
		   Count(IF(hiring_status = 2, 1, NULL))     AS rejected 
	FROM   challenge.application_status 
	WHERE  application_date BETWEEN ? AND ? 
	GROUP  BY Year(application_date), 
			  Weekofyear(application_date) 
			  
	Back end
		
	The funnels.json end point which serves as proxy for the Funnel service is the one responsible for retrieving this information using the aforementioned SQL query and to format it to
	the desired structure.

	You can test this feature by invoking the following URL : http://localhost:8080/InstacartChallenge/funnels?start_date=<start_date>&end_date=<end_date>
	
	In order to populate the db with mockup dat you can use http://www.generatedata.com/
	
	

		
	
	
	
		