package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ComputerDBHomePage extends TestBase {
	public static String UpdatedCompName;
	public static String ReturnedCompName = null;



	String computername = null;

	@FindBy(how = How.XPATH, using = ".//input[@id='searchbox']")
	@CacheLookup
    WebElement tboxSearch;

	@FindBy(how = How.ID, using = "searchsubmit")
	@CacheLookup
    WebElement btnSearchSubmit;

	@FindBy(how = How.XPATH, using = ".//table[@class='computers zebra-striped']//tbody")
	@CacheLookup
    WebElement filterComps;

	@FindBy(how = How.XPATH, using = ".//section//h1")
	@CacheLookup
    WebElement labelrecords;

	@FindBy(how = How.XPATH, using = ".//li[@class='next']")
	@CacheLookup
    WebElement btnNext;

	@FindBy(how = How.ID, using = "add")
	@CacheLookup
    WebElement btnAddComp;

	@FindBy(how = How.XPATH, using = ".//table[@class='computers zebra-striped']//tbody//tr[1]/td[1]/a")
	@CacheLookup
    WebElement FirstCellName;

	@FindBy(how = How.XPATH, using = ".//table[@class='computers zebra-striped']//tbody//tr[1]/td[1]/a")
	@CacheLookup
    WebElement FirstCellNameEditComp;


	@FindBy(how = How.XPATH, using = ".//section/div[@class='alert-message warning']")
	@CacheLookup
	WebElement alertMessage;

	public ComputerDBHomePage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Enter computer name and click on Search
	 */
	public void filterComputerByName(String computerName) throws InterruptedException {
		tboxSearch.sendKeys(computerName);
		computername = computerName;
		btnSearchSubmit.click();
	}

	/**
	 * To click on Add new computer button
	 */
	public void clickAddNewCompButton() {
		btnAddComp.click();
	}

	/**
	 * Click on computer for edit
	 */
	public void ClickOnFilteredComputer(String newCompName) {
		FirstCellName.click();
	}

	/**
	 *  Filter by Computer name and get text from the first cell.
	 */
	public void verifyCompName(String newCompName) {
		tboxSearch.sendKeys(newCompName);
		btnSearchSubmit.click();
		ReturnedCompName = FirstCellNameEditComp.getText();
	}

	/**
	 * Verify created computer name from the success message
	 */
	public boolean verifyThatCompNameIsAdded(String newCompany) {
		boolean val=alertMessage.getText().contains(newCompany);
		return val;
	}

	/**
	 * Get total No. of computers from countitle and validate filter computer
	 * name with each computer.
	 */
	public void verifyFilter() {

	}
}
