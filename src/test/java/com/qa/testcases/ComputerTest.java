package com.qa.testcases;

import com.qa.base.TestBase;
import com.qa.pages.AddComputerPage;
import com.qa.pages.ComputerDBHomePage;
import com.qa.pages.EditComputerPage;
import com.qa.util.TestUtil;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ComputerTest extends TestBase{
    AddComputerPage addComputerPage;
    ComputerDBHomePage computerDBHomePage;
    EditComputerPage editComputerPage;
    WebDriverWait wait;
    public static String newCompName;

    //Constructor for the LoginPageTest
 	public ComputerTest() {
 		super();
 	}
 	
    
	@BeforeMethod()
	public void setUp() throws InterruptedException {
		initialization();
		computerDBHomePage=new ComputerDBHomePage();
        addComputerPage =new AddComputerPage();
        editComputerPage =new EditComputerPage();
		}
	

    @Test(priority=1)
    public void verifyThatUserCanAddComputerWithAllDetailsTest() throws Exception
    {
        newCompName=TestUtil.randomString(4);
        String[] data=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        computerDBHomePage.clickAddNewCompButton();
        addComputerPage.enterComputerDetails(newCompName,data[0], data[1], data[2]);
        addComputerPage.clickOnCreateComputerButton();
        Assert.assertTrue(computerDBHomePage.verifyThatCompNameIsAdded(newCompName));
        editComputerPage.filterAndDeleteComputerAfterTests(newCompName);
    }

    @Test(priority=2)
    public void verifyThatUserCanAddComputerWithComputerNameTest() throws InterruptedException
    {
        newCompName=TestUtil.randomString(4);
        computerDBHomePage.clickAddNewCompButton();
        addComputerPage.enterComputerDetails(newCompName,null, null, null);
        addComputerPage.clickOnCreateComputerButton();
        Assert.assertTrue(computerDBHomePage.verifyThatCompNameIsAdded(newCompName));
        editComputerPage.filterAndDeleteComputerAfterTests(newCompName);
    }

    @Test(priority=3)
    public void verifyAddComputerWithoutNameTest() throws Exception
    {
        newCompName=TestUtil.randomString(4);
        computerDBHomePage.clickAddNewCompButton();
        String[] data=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        addComputerPage.enterComputerDetails(null,data[0], data[1], data[2]);
        addComputerPage.clickOnCreateComputerButton();
        addComputerPage.verifyErrorMessage("Computer name", "Required");
    }
    @Test(priority=4)
    public void verifyClickOnCancelButtonInAddComputerTest() throws Exception {
        String compName= TestUtil.randomString(5);
        computerDBHomePage.clickAddNewCompButton();
        String[] data=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        addComputerPage.enterComputerDetails(compName,data[0], data[1], data[2]);
        addComputerPage.clickOnCancelButton();
        Assert.assertTrue(addComputerPage.verifyThatHomepageisShownOnClickingCancelButton(),"Unable to click on Cancel Button");
    }

    @Test(priority=5)
    public void verifyThatUserCanEditComputerTest() throws Exception {
        String[] beforeEdit=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        String compNameBeforeUpdate= TestUtil.randomString(5);
        computerDBHomePage.clickAddNewCompButton();
 	    addComputerPage.addComputer(compNameBeforeUpdate, beforeEdit[0], beforeEdit[1], beforeEdit[2]);
        computerDBHomePage.filterComputerByName(compNameBeforeUpdate);
        computerDBHomePage.ClickOnFilteredComputer(compNameBeforeUpdate);
        String compNameAfterUpdate= TestUtil.randomString(5);
        String[] afterEdit=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        editComputerPage.enterEditComputerDetails(compNameAfterUpdate,afterEdit[0], afterEdit[1], afterEdit[2]);
        editComputerPage.clickOnSaveButton();
        Assert.assertTrue(editComputerPage.verifyThatCompNameIsModified(compNameAfterUpdate),"Unable to click on Save Button");
        editComputerPage.filterAndDeleteComputerAfterTests(compNameAfterUpdate);
 	}

    @Test(priority=6)
    public void verifyEditComputerWithoutNameTest() throws Exception
    {
        String[] data=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        String compNameBeforeUpdate= TestUtil.randomString(5);
        computerDBHomePage.clickAddNewCompButton();
        addComputerPage.addComputer(compNameBeforeUpdate,data[0], data[1], data[2]);
        computerDBHomePage.filterComputerByName(compNameBeforeUpdate);
        computerDBHomePage.ClickOnFilteredComputer(compNameBeforeUpdate);
        editComputerPage.enterEditComputerDetails(null,data[0], data[1], data[2]);
        editComputerPage.clickOnSaveButton();
        Assert.assertTrue(addComputerPage.verifyErrorMessage("Computer name", "Required"));
    }

    @Test(priority=7)
    public void verifyClickOnCancelButtonInEditComputerTest() throws Exception {
        String[] beforeUpdate=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        String compNameBeforeUpdate= TestUtil.randomString(5);
        computerDBHomePage.clickAddNewCompButton();
        addComputerPage.addComputer(compNameBeforeUpdate,beforeUpdate[0], beforeUpdate[1], beforeUpdate[2]);
        computerDBHomePage.filterComputerByName(compNameBeforeUpdate);
        computerDBHomePage.ClickOnFilteredComputer(compNameBeforeUpdate);
        String compNameAfterUpdate= TestUtil.randomString(5);
        String[] afterUpdate=TestUtil.readExcelValues("Computer", "edit_computer_db_values");
        editComputerPage.enterEditComputerDetails(compNameAfterUpdate,afterUpdate[0], afterUpdate[1], afterUpdate[2]);
        editComputerPage.clickOnCancelButton();
        Assert.assertTrue(addComputerPage.verifyThatHomepageisShownOnClickingCancelButton(),"Unable to click on Cancel Button");
        editComputerPage.filterAndDeleteComputerAfterTests(compNameBeforeUpdate);
    }

    @Test(priority = 8, groups="Regression")
    public void verifyDeleteComputerTest() throws Exception {
        String[] beforeUpdate=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        String compNameToDelete= TestUtil.randomString(5);
        computerDBHomePage.clickAddNewCompButton();
        addComputerPage.addComputer(compNameToDelete,beforeUpdate[0], beforeUpdate[1], beforeUpdate[2]);
        computerDBHomePage.filterComputerByName(compNameToDelete);
        computerDBHomePage.ClickOnFilteredComputer(compNameToDelete);
        editComputerPage.clickOnDeleteComputerButton();
        Assert.assertTrue(editComputerPage.verifyThatComputerIsDeleted("Computer has been deleted"),"Unable to Delete Computer");
    }

    @Test(priority = 9, groups="Regression")
    public void verifyFilterComputerByNameTest() throws Exception {
        String[] data=TestUtil.readExcelValues("Computer", "add_computer_db_values");
        String compName= TestUtil.randomString(5);
        computerDBHomePage.clickAddNewCompButton();
        addComputerPage.addComputer(compName,data[0], data[1], data[2]);
        computerDBHomePage.filterComputerByName(compName);
        computerDBHomePage.verifyFilter();
        computerDBHomePage.ClickOnFilteredComputer(compName);
        editComputerPage.clickOnDeleteComputerButton();
    }


    @AfterMethod()
    public void teardown()
    {
    	driver.quit();
 
    }


}
