package tnp.TutorialsNinjaProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;

public class Search extends Base {
	WebDriver driver;
	public Search() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		driver = initialzeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product is not displayed in the search result");
	}
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.","No product is not displayed");
	}
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.","No product is not displayed");
	}

}
