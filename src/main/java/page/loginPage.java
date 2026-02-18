package page;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import support.AbstractComponent;

public class loginPage extends AbstractComponent {

	WebDriver driver;
	
	By user_Email=By.id("userEmail");
	By user_Password=By.id("userPassword");
	By login_btn=By.id("login");

	public loginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	

	
	

	public productPage loginApplication(String Email, String password)
	{
		
	    driver.findElement(user_Email).sendKeys(Email);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
	    driver.findElement(user_Password).sendKeys(password);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
	    driver.findElement(login_btn).click();
	    driver.manage().window().maximize();
	    
	    return new productPage(driver);
	    
	}
	
	
	
}
