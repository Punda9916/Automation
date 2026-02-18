package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterTest;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {


public static FileReader fr;
public static Properties prop = new Properties();
public static ThreadLocal <WebDriver> driver=new ThreadLocal<>();

@BeforeMethod(alwaysRun=true)

public void setup() throws IOException {
		
/*	if(driver==null) {
		System.out.println(System.getProperty("user.dir"));*/
	System.out.println(">>> Setup method called");
		fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
	    prop.load(fr);
	
	 String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");;
	if(browserName.contains("chrome"))
	{
		System.out.println("Inside chrome block Browsername ="+browserName);
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup(); //base
		
		if(browserName.contains("headless")) {
			options.addArguments("headless");
			options.addArguments("--window-size=1920,1080"); // Full HD
			System.out.println("running in headless mode");
		}
		else
		{
			System.out.println("running in head mode");
		}
	    driver.set(new ChromeDriver(options)); //base
	    getDriver().get(prop.getProperty("testurl")); //properties
	    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10) );
	    System.out.println("Chrome Started Successfully");
	    System.out.println("Driver initialized: " + getDriver());

	}
	
	
	else if(browserName.contains("edge")) 
	{
		System.out.println("Inside edge block Browsername ="+browserName);
		EdgeOptions options = new EdgeOptions();
		System.setProperty("webdriver.edge.driver","C:\\Users\\NAGAVENI P\\Edge driver\\msedgedriver.exe"); 
		//WebDriverManager.edgedriver().setup(); //base
		if(browserName.contains("headless")) {
			options.addArguments("headless");
			options.addArguments("--window-size=1920,1080"); 
		}
		else
		{
			System.out.println("running in head");
		}
		 driver.set(new EdgeDriver(options)); //base
	    getDriver().get(prop.getProperty("testurl")); //properties
	    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10) );
	    System.out.println("Edge Started Successfully");
	}
		
	}

protected WebDriver getDriver() {
	
	return driver.get();
}

public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			
}

public String getScreenShot(String testCaseName) throws IOException {
	
	TakesScreenshot ts=  (TakesScreenshot)getDriver();
	File src=ts.getScreenshotAs(OutputType.FILE);
	File dest= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\reports" +testCaseName+ ".png");
	FileUtils.copyFile(src, dest);
	return System.getProperty("user.dir")+"\\src\\test\\resources\\reports" +testCaseName+ ".png";
}
                                                                        
@AfterMethod(alwaysRun=true)
	public void teardown() {
		if(getDriver()!= null) {
			
			getDriver().quit();
			driver.remove();
			System.out.println("TearDown Successfully!...");
		}
	
		
	}
     
}

