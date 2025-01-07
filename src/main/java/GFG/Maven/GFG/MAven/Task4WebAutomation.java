package GFG.Maven.GFG.MAven;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Task4WebAutomation extends functionsClass {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Imran/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize the browser
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://juice-shop.herokuapp.com/#/login");

		// close alerts & popup
		WebElement cookieButton = driver.findElement(
				By.xpath("//*[@class='cc-compliance']//a[contains(@aria-label,'dismiss cookie message')]"));
		cookieButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-dialog-0")));
		WebElement modalDismissButton = driver
				.findElement(By.xpath("//*[@id='mat-dialog-0']//button[@aria-label='Close Welcome Banner']"));
		modalDismissButton.click();
		System.out.println("Modal Window Closed");
		Thread.sleep(10000);

		// login to website
		login(driver, "test@example.com", "test1234$");

		// add products to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//mat-grid-tile//mat-card//div[2]//button[contains(@aria-label,'Add to Basket')][1]")));
		for (int i = 0; i < 5; i++) {
			addToCart(driver, i);
		}
		String expectedCartValue = "5";
		String actualCartValue = driver
				.findElement(By.xpath("//button[contains(@aria-label,'shopping cart')]//span/span[2]")).getText();
		Assert.assertEquals(actualCartValue, expectedCartValue);
		driver.findElement(By.xpath("//button[contains(@aria-label,'shopping cart')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement checkout = driver.findElement(By.id("checkoutButton"));
		js.executeScript("arguments[0].scrollIntoView();", checkout);
		checkout.click();
		driver.findElement(By.xpath("//button[contains(@aria-label,'new address')]")).click();
		WebElement country = driver.findElement(By.xpath("//div[1]/input[contains(@class,'mat-form-field')]"));
		country.sendKeys("US");
		WebElement name = driver.findElement(By.xpath("//div[2]/input[contains(@class,'mat-form-field')]"));
		name.sendKeys("John");
		WebElement mobile = driver.findElement(By.xpath("//div[3]/input[contains(@class,'mat-form-field')]"));
		mobile.sendKeys("1234567890");
		WebElement zipCode = driver.findElement(By.xpath("//div[4]/input[contains(@class,'mat-form-field')]"));
		zipCode.sendKeys("12345");
		WebElement address = driver.findElement(By.xpath("//div[5]/input[contains(@class,'mat-form-field')]"));
		address.sendKeys("Greenfield Village");
		WebElement city = driver.findElement(By.xpath("//div[6]/input[contains(@class,'mat-form-field')]"));
		city.sendKeys("Boston");
		WebElement state = driver.findElement(By.xpath("//div[7]/input[contains(@class,'mat-form-field')]"));
		state.sendKeys("Massachusetts");
		driver.findElement(By.id("submitButton]")).click();
		driver.findElement(By.xpath("//*[contains(@class,'mat-radio-button')]")).click();
		driver.findElement(By.xpath("//*[contains(@aria-label,'Proceed to payment')]")).click();
		driver.findElement(By.xpath("//mat-row[3]//*[contains(@class,'mat-radio-button')]")).click();
		driver.findElement(By.xpath("//*[contains(@aria-label,'Proceed to delivery method')]")).click();
		WebElement balance = driver.findElement(By.xpath("//mat-card//b/*[contains(@class,'confirmation')]"));
		String walletBalance = balance.getText();
		if (walletBalance.equals("0.00")){
			throw new Exception ("wallet balance is insufficient");
		}else{
			throw new Exception ("Wallet Balance =" +walletBalance+ "can be used for purchase");
		}
	}
	

}

