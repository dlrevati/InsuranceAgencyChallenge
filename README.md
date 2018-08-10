# InsuranceAgencyChallenge

Insurance Agency - Calculate Commission Challenge

Web App:

1. UI - Built with Angular Framework
All configurations maintained by the Rest API.
UI is dynamic to the Agents Hierarchy with the individual agent commission % and the Commission Plans.

2. RestAPI - 
Configuration:
App is maintained as fully configurable and configurations are maintained in ${Project}/WebContent/WEB-INF/config/app_config.properties
Any changes to the configurations will be done only to the app_config.properties file.

Distributable:
The WAR file: InsuranceAgency-0.0.1-SNAPSHOT.war can be directly put on the server. 
Using, Apache-Tomcat 8.5, place the WAR file in the webapps folder: {path to apache-tomcat}\apache-tomcat-8.5.32\webapps
and then start up server

Build: 
Project is Maven Build.
Doesn't have any third party dependency for runtime execution.

Rest Api Calls:
GET: 
http://localhost:8080/InsuranceAgency/api/getAppContext

POST:
http://localhost:8080/InsuranceAgency/api/calculate 
Body--> 

{
"agentCount":4,
"commissionPlan":"b",
"policyAmount":100000
}
_______________________________________________________________________________________________________________________________________
Console App:

Requirements:
JDK 1.8 for build
JRE 1.8 for runtime

Configuration:
App is maintained as fully configurable and configurations are maintained in ${Project}/config/app_config.properties
Any changes to the configurations will be done only to the app_config.properties file.

Distributable:
The Final deliverable is available in ${Project}/dist folder.Execute run.bat to start the Solution.
To run a batch file, move to the directory containing the file and type the name of the batch file. 
For example, if the batch file is named "run.bat", type "run" to execute the batch file.
(Attached Screenshot for running batch file)

Build:
Project is Maven build.
Doesn't have any third party dependency for runtime execution.
