package GFG.Maven.GFG.MAven;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2WebAutomation {
	
	@Test

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Imran/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize the browser
		driver.manage().window().maximize();

		// Launching website
		driver.get("https://juice-shop.herokuapp.com/#/");


		// click on dismiss button on modal window
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-dialog-0")));
		WebElement modalDismissButton = driver
				.findElement(By.xpath("//*[@id='mat-dialog-0']//button[@aria-label='Close Welcome Banner']"));
		modalDismissButton.click();
		System.out.println("Modal Window Closed");
		WebElement cookieButton = driver
				.findElement(By.xpath("//*[@class='cc-compliance']//a[contains(@aria-label,'dismiss cookie message')]"));
		cookieButton.click();

		//click on first product
		driver.findElement(By.xpath("//div[@class='mat-grid-tile-content']")).click();
		WebElement productInfo = driver.findElement(By.xpath("//*[contains(@id,'mat-dialog')]"));
		if(productInfo.isDisplayed()){
			System.out.println("Product Info opened on Pop up window");
		}

		// verify product image is present on popup 
		WebElement productImage = driver.findElement(By.xpath("//div[@class='mat-grid-tile-content']//*[contains(@alt,'Apple Juice')]"));
	    Assert.assertEquals(true, productImage.isDisplayed());
		System.out.println("Product image is present on popup");
		
		// verify Reviews on popup and close popup
		WebElement reviews = driver.findElement(By.xpath("//mat-panel-title/span[2]"));
		WebElement closePopup = driver.findElement(By.xpath("//*[contains(@aria-label,'Close Dialog')]"));
        
		if (reviews.isDisplayed()) {
            reviews.click();
            System.out.println("Reviews are present for the product");
            Thread.sleep(20000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("arguments[0].scrollIntoView();", closePopup);
		}else{
			System.out.println("Reviews are not present for the product");
		}
            try {
            	closePopup.click();
            	System.out.println("Pop up Closed");
            } catch (Exception e) {
            	System.out.println("Pop up did not get Close");
            }
		driver.quit();
	}
}