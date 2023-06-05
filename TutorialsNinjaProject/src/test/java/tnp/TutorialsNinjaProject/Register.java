package tnp.TutorialsNinjaProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.qa.utils.Utilities;


public class Register extends Base {
	WebDriver driver;

	public Register() {
		super();
	}
	@BeforeMethod
	public void SetUp() {
		driver = initialzeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();	
	} 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void verifyRegisteringAndAccountWithMandtoryField() 
	{
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telePhoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!","Account success page is not created");
	}
	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFileds() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telePhoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@name=\"newsletter\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!","Account success page is not created");

	}
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("deeprao@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telePhoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@name=\"newsletter\" and @value=\"1\"]")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualWarning, "Warning: E-Mail Address is already registered!","Warning meassage regarding duplicate email address is not displayed");

	}
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

		String actualPrivacyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage= dataProp.getProperty("privacyPolicyWarning");
		Assert.assertTrue(actualPrivacyWarning.contains(expectedWarningMessage),"Privacy Warning meesage is not displayed");


		String actualFirstNameWaring = driver.findElement(By.xpath("//input[@id=\"input-firstname\"]/following-sibling::div")).getText();
		String expectFastNameWarning = dataProp.getProperty("fastNameWarning");
		Assert.assertTrue(actualFirstNameWaring.contains(expectFastNameWarning),"First name Warning message is not displayed");

		String actualLastNameWaring = driver.findElement(By.xpath("//input[@id=\"input-lastname\"]/following-sibling::div")).getText();
		String expectLastNameWarning = dataProp.getProperty("lastNameWarning");
		Assert.assertTrue(actualLastNameWaring.contains(expectLastNameWarning),"Last name Warning message is not displayed");

		String actualEmailWaring = driver.findElement(By.xpath("//input[@id=\"input-email\"]/following-sibling::div")).getText();
		String expectEmailWarning = dataProp.getProperty("emailWarning");
		Assert.assertTrue(actualEmailWaring.contains(expectEmailWarning),"Email Address Warning message is not displayed");

		String actualTelePhoneWaring = driver.findElement(By.xpath("//input[@id=\"input-telephone\"]/following-sibling::div")).getText();
		String expectTelephoneWarning= dataProp.getProperty("telephoneWarning");
		Assert.assertTrue(actualTelePhoneWaring.contains(expectTelephoneWarning),"Tele Phone Warning message is not displayed");

		String actualPasswordWaring = driver.findElement(By.xpath("//input[@id=\"input-password\"]/following-sibling::div")).getText();
		String expectPasswordWarning=dataProp.getProperty("passwordWarning");
		Assert.assertTrue(actualPasswordWaring.contains(expectPasswordWarning),"Password Warning message is not displayed");

	}

}
