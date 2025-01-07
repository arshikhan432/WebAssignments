package GFG.Maven.GFG.MAven;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Task3WebAutomation extends functionsClass {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Imran/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize the browser
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://juice-shop.herokuapp.com/#/register");

		//close alerts & popup
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

		//validate registration form 
		registration(driver, "", "", "", "", "");
		for (int i = 0; i < 5; i++) {
			verifyInputValidationError(driver, i);
		}
		driver.findElement(By.xpath("//span[@class='mat-slide-toggle-bar']")).click();
		registration(driver, "testlogin@example.com", "test1234$", "test1234$", "Your eldest siblings middle name?",
				"khan");

		//verify Registration completion message
		FluentWait<WebDriver> w = new WebDriverWait(driver, 10, 100).ignoring(NoSuchElementException.class);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='mat-simple-snack-bar-content']")));
		String message = driver.findElement(By.xpath("//*[@class='mat-simple-snack-bar-content']")).getText();
		if (message.contains("Registration completed successfully")) {
			System.out.println(message);
		} else {
			throw new Exception("Registration message not displaying");
		}
		//login to website
		login(driver, "testlogin@example.com", "test1234$");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navbarAccount")));
		driver.findElement(By.id("navbarAccount")).click();
		WebElement logOut = driver.findElement(By.id("navbarLogoutButton"));
		if (logOut.isDisplayed()) {
			System.out.println("User gets logged in");
		} else {
			throw new Exception("User is not logged in");
		}
		driver.quit();
	}

}
