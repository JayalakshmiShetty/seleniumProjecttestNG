package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.security.SecureRandom;

public class EditComputerPage extends TestBase {



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

	@FindBy(how = How.XPATH, using = ".//input[@value='Save this computer']")
	@CacheLookup
    WebElement btnSaveComputer;

	@FindBy(how = How.XPATH, using = ".//section/div[@class='alert-message warning']")
	@CacheLookup
	WebElement alertMessage;

	@FindBy(how = How.XPATH, using =".//input[@value='Delete this computer']")
	@CacheLookup
	WebElement btnDeleteComputer;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Cancel')]")
    @CacheLookup
    WebElement btnCancel;


	public EditComputerPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Edit computer by editing all fields of edit computer Enter random string
	 * to computer name
	 */
	public String getSelectedComputerName() {
		String compNameBeforeUpdate = tboxComputerName.getAttribute("value");
	return compNameBeforeUpdate;}


		public void enterEditComputerDetails(String compNameAfterUpdate, String introduceDate, String discontinueDate, String company) throws InterruptedException {
		TestUtil.enterText(tboxComputerName, compNameAfterUpdate);
		TestUtil.enterText(tboxIntroducedDate, introduceDate);
		TestUtil.enterText(tboxDiscontinuedDate, discontinueDate);
		TestUtil.selectFromDropdownList(btnCompany, company);
	}

	public boolean verifyThatCompNameIsModified(String newCompany) {
		boolean val=alertMessage.getText().contains(newCompany);
		return val;
	}
		public void clickOnSaveButton()
	{
		btnSaveComputer.click();
	}

	public void clickOnDeleteComputerButton() throws InterruptedException {
		TestUtil.clickElement(btnDeleteComputer);
	}

    public void filterAndDeleteComputerAfterTests(String computerName) throws InterruptedException {
	    new ComputerDBHomePage().filterComputerByName(computerName);
        new ComputerDBHomePage().ClickOnFilteredComputer(computerName);
        TestUtil.clickElement(btnDeleteComputer);
    }
	public boolean verifyThatComputerIsDeleted(String newCompany) {
		boolean val=alertMessage.getText().contains(newCompany);
		return val;
	}
    public void clickOnCancelButton() throws InterruptedException {
        TestUtil.clickElement(btnCancel);
    }
}
