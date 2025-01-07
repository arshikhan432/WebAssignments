package GFG.Maven.GFG.MAven;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Task1WebAutomation extends functionsClass{

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

		// select last value present in dropdown of items per page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement itemsPerPage = driver.findElement(By.id("mat-select-value-1"));
		js.executeScript("arguments[0].scrollIntoView();", itemsPerPage);
		WebElement cookieButton = driver
				.findElement(By.xpath("//*[@class='cc-compliance']//a[contains(@aria-label,'dismiss cookie message')]"));
		cookieButton.click();
		Thread.sleep( 15000 );
		itemsPerPage.click();

		// selecting max option from dropdown
		List<WebElement> allOptions = driver.findElements(By.xpath("//*[contains(@id,'mat-option')]/span"));
		int value = allOptions.size();
		for (int i = value - 1; i < value; i++) {
			allOptions.get(i).getText();
			allOptions.get(i).click();
		}

		// verify all items are displaying on Homepage
		js.executeScript("arguments[0].scrollIntoView();", itemsPerPage);
		WebElement nextPageNavigation = driver.findElement(By.xpath("//*[contains(@aria-label,'Next page')]"));
		if (!(nextPageNavigation.isEnabled())) {
			// total count present in range selector at the bottom of Homepage
			WebElement countInRange = driver
					.findElement(By.xpath("//div[contains(@class,'mat-paginator-range-label')]"));
			String totalCountInRange = countInRange.getText();
			String[] s = totalCountInRange.split(" ");
			String totalCountInRangeSelector = s[s.length - 1];

			// total items present on Homepage
			List<WebElement> productsOnHomepage = driver
					.findElements(By.xpath("//div[contains(@class,'mat-grid-tile-content')]"));
			int productsFound = productsOnHomepage.size();
			String totalProductsOnHomepage = String.valueOf(productsFound);

			assertData(totalCountInRangeSelector, totalProductsOnHomepage, "Total items on Homepage");
		} else {
			throw new Exception("All items are not present on Homepage");
		}
		driver.quit();
	}

}