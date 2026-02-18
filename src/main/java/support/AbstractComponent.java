package support;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AbstractComponent  {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		
	}


	
	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
				
	}
	
public void waitForElementToDisappear(By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
				
	}
	
public WebElement waitForElementToBeClickable(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.elementToBeClickable(findBy));
				
	}


	
	public void selectByVisibleText(WebElement element,String text)
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
		
	}
	
	public void selectByIndex(By element, int index) {
		Select select = new Select(driver.findElement(element));
		select.selectByIndex(index);
	}
	
	public void selectFromAutoSuggestive(By inputBox, By optionsLocator, String valueToSelect) {
		driver.findElement(inputBox)      .sendKeys(valueToSelect);
		waitForElementToAppear(optionsLocator);
		
		List<WebElement>options = driver.findElements(optionsLocator);
		
		for(WebElement option: options)
		{
			if(option.getText().equalsIgnoreCase(valueToSelect))
			{
				option.click();
				break;
			}
			
		}
	}
	
	public void scrollIntoView(WebElement Element) 
	
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", Element);
	}
	
	public void clickWithScroll(WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.elementToBeClickable(element)); 
		element.click();
	}
	
	public  void clickWithSafe(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		try {
			element.click();
		}
		catch(ElementClickInterceptedException e) {
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		}
		
			
		
		
		}
		
	}
	


	
