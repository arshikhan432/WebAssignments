package GFG.Maven.GFG.MAven;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class functionsClass {
	
	WebDriver driver = new ChromeDriver();
	
	public static void registration(WebDriver driver, String email, String password, String repeatPassword, String securityQuestion, String securityAnswer) throws InterruptedException {
	    WebElement emailInput = driver.findElement(By.id("emailControl"));
	    emailInput.sendKeys(email);       
	    WebElement passwordInput = driver.findElement(By.id("passwordControl"));
	    passwordInput.sendKeys(password);       
	    WebElement repeatPasswordInput = driver.findElement(By.id("repeatPasswordControl"));
	    repeatPasswordInput.sendKeys(repeatPassword);
	    WebElement securityQuestionInput = driver.findElement(By.name("securityQuestion"));
	    securityQuestionInput.sendKeys(securityQuestion);
	    WebElement securityAnswerInput = driver.findElement(By.id("securityAnswerControl"));
	    securityAnswerInput.sendKeys(securityAnswer);
	    Thread.sleep(500);
	    WebElement registerButton = driver.findElement(By.id("registerButton"));
	    registerButton.click();
	}
	
	public static void addToCart(WebDriver driver, int i) throws Exception{
			WebElement addIntoCart = driver.findElement(By.xpath("//mat-grid-tile//mat-card//div[2]//button[contains(@aria-label,'Add to Basket')][i]"));
			addIntoCart.click();
			FluentWait<WebDriver> wait1 = new WebDriverWait(driver, 10, 100).ignoring(NoSuchElementException.class);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='mat-simple-snack-bar-content']")));
			String message = driver.findElement(By.xpath("//*[@class='mat-simple-snack-bar-content']")).getText();
			if (message.contains("Placed")) {
				System.out.println(message);
			} else {
				throw new Exception("Product not added to cart");
			}
			
		}
	
	public static void login(WebDriver driver, String email, String password) {
	    WebElement emailInput = driver.findElement(By.id("email"));
	    emailInput.sendKeys(email);       
	    WebElement passwordInput = driver.findElement(By.id("password"));
	    passwordInput.sendKeys(password);       
	    WebElement loginButton = driver.findElement(By.id("loginButton"));
	    loginButton.click();
	}

	
    public static void verifyInputValidationError(WebDriver driver, int i) throws Exception{
		List<WebElement> errorMessage = driver.findElements(By.xpath("(//div//mat-error)"));
		String actualErrorMsg = errorMessage.get(i).getText();
        if (actualErrorMsg.isEmpty()) {
        	throw new Exception("Failed verifying : Error message not displaying");
        }else{
        System.out.println("Error message displaying as - " +actualErrorMsg);
    }
    }
    
    
    public static void assertData(Object expected, Object actual, String verificationType) throws Exception {
		if (!actual.equals(expected)) {
			throw new Exception("Failed verifying " + verificationType + " : Actual value - " + actual
					+ " not matching Expected value - " + expected);
		} else {
			System.out.println("Successfully verified " + verificationType + " : Actual value - " + actual
					+ " matching with Expected value - " + expected);
		}
	}
}
