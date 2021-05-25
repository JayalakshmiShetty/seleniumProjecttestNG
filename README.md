### seleniumProjectTestNG


### Way to execute the Assignment Maven project locally.

1. Download the project  and save it.
2. Open in Eclipse workspace and In home page  click “File”  click “Import”
3. Click on Maven  Existing Maven Project.
4. Then select the Root folder as saved above “TestAutomationAssignment folder” 
5. Click on check box “/pom.xml”then click Finish
6. TestAutomationAssignment project will be added in the “project Explorer”
7. Expand the src/test/java package. And right click test file  Click Run As  TestNGTest. 
8. TestResults reported in Console.
9. RightClick on “emailable-report.html” file. Goto  properties  copy the file location. 
10. Copy the above location path and paste in the chrome browser.  HTML Report will be displayed in the browser.

To execute from the Terminal , navigate to the project directory and run the below command :
mvn clean test -DsuiteXmlFile="./src/test/java/com/resources/testng.xml"


****************************************************************************

### Way to execute the Assignment in Git Hub Actions:
1. Creating a new action by navigating to the Actions tab on our repository page.
2. Click on setup this workflow button under the Node.js workflow.
3. click Start commit then either commit it directory to the master branch or add the change to a new branch. For this walkthrough, I'll be committing straight to master.
4. To see our new action run, we can again click on the Actions tab which will navigate us back to our new Actions dashboard.


###  Technologies used:
1.	Appium
2.	Maven
3.	TestNG
4.	Selenium
5.	Intellij
6.	Android Studio
