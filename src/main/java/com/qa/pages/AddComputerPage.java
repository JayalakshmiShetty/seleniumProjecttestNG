package com.qa.pages;


import com.qa.util.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class AddComputerPage extends ComputerDBHomePage {





	@FindBy(how = How.ID, using = "name")
	@CacheLookup
    WebElement tboxComputerName;

	@FindBy(how = How.ID, using = "introduced")
	@CacheLookup
    WebElement tboxIntroducedDate;

	@FindBy(how = How.ID, using = "discontinued")
	@CacheLookup
    WebElement tboxDiscontinuedDate;

	@FindBy(how = How.ID, using = "company")
	@CacheLookup
    WebElement btnCompany;

	@FindBy(how = How.XPATH, using = ".//input[@type='submit']")
	@CacheLookup
    WebElement btnCreateComputer;

	@FindBy(how = How.XPATH, using = ".//a[contains(text(),'Cancel')]")
	@CacheLookup
	WebElement btnCancel;


	@FindBy(how = How.XPATH, using = ".//div[@class='clearfix error']")
	@CacheLookup
	WebElement errorMsg;

	public AddComputerPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Add new computer by entering value to all fields.
	 */
	public void enterComputerDetails(String newCompName, String introduceDate, String discontinueDate, String company) throws InterruptedException {
		TestUtil.enterText(tboxComputerName, newCompName);
		TestUtil.enterText(tboxIntroducedDate, introduceDate);
		TestUtil.enterText(tboxDiscontinuedDate, discontinueDate);
		TestUtil.clickElement(btnCompany);
		TestUtil.selectFromDropdownList(btnCompany, company);
	}

		public void clickOnCreateComputerButton() throws InterruptedException {
		TestUtil.clickElement(btnCreateComputer);
	}

	public void clickOnCancelButton() throws InterruptedException {
		TestUtil.clickElement(btnCancel);
	}

	public boolean verifyErrorMessage(String msg1, String msg2) throws InterruptedException {
		if(errorMsg.getText().contains(msg1)&&errorMsg.getText().contains(msg2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void addComputer(String newCompName, String introduceDate, String discontinueDate, String company) throws InterruptedException {
        enterComputerDetails(newCompName, introduceDate, discontinueDate, company);
        clickOnCreateComputerButton();
    }

	public boolean verifyThatHomepageisShownOnClickingCancelButton(){
		if(tboxSearch.isDisplayed()){
			return true;}
			else{
				return false;
		}

	}
}
