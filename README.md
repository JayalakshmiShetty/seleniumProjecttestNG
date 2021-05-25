### SeleniumProjectTestNG


### Way to execute the Assignment Maven project locally.

1. Download the project  and save it.
2. Open in Eclipse workspace and In home page  click “File”  click “Import”
3. Click on Maven  Existing Maven Project.
4. Then select the Root folder as saved above “SeleniumProjectTestNG folder” 
5. Click on check box “/pom.xml”then click Finish
6. SeleniumProjectTestNG project will be added in the “project Explorer”
7. Expand the src/test/java package. And right click test file  "ComputerTest.java" Click Run As  TestNGTest. 
8. TestResults reported in Console.
9. RightClick on “emailable-report.html” file in test-output folder in intellij left menu. Goto  properties  copy the file location. 
10. Copy the above location path and paste in the chrome browser.  HTML Report will be displayed in the browser.
11. Extent report can be viewed by right right clicking  "TestReport_Extent.html" file in test-output folder in intellij left menu and click Öpen in browser". 

To execute from the Terminal , navigate to the project directory and run the below command :
>- mvn clean test -DsuiteXmlFile="./src/test/java/com/resources/testng.xml


****************************************************************************

### Frameworks used:
Made use of Hybrid framework( Data Driven+ Pageobject model) with TestNG

###  Technologies used:
1.	Intellijm
2.	Maven
3.	TestNG
4.	Selenium

