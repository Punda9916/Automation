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
		FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
	    prop.load(fr);
	
	 String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	if(browserName.equalsIgnoreCase("chrome")){
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup(); //base
		if(browserName.contains("headless")) {
			options.addArguments("headless");
		}
	    driver.set(new ChromeDriver(options)); //base
	    driver.get().get(prop.getProperty("testurl")); //properties
	    driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10) );
	    System.out.println("Chrome Started Successfully");
	}
	
	else if(browserName.equalsIgnoreCase("edge")) {
		EdgeOptions options = new EdgeOptions();
		System.setProperty("webdriver.edge.driver","C:\\Users\\NAGAVENI P\\Edge driver\\msedgedriver.exe"); 
		//WebDriverManager.edgedriver().setup(); //base
		if(browserName.contains("headless")) {
			options.addArguments("headless");
		}
		 driver.set(new EdgeDriver(options)); //base
	    driver.get().get(prop.getProperty("testurl")); //properties
	    driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10) );
	    System.out.println("Edge Started Successfully");
	}
		
	}

protected WebDriver getDriver() {
	
	return driver.get();
}

public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(40));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			
}

public String getScreenShot(String testCaseName) throws IOException {
	
	TakesScreenshot ts=  (TakesScreenshot)driver.get();
	File src=ts.getScreenshotAs(OutputType.FILE);
	File dest= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\reports" +testCaseName+ ".png");
	FileUtils.copyFile(src, dest);
	return System.getProperty("user.dir")+"\\src\\test\\resources\\reports" +testCaseName+ ".png";
}
                                                                        
@AfterMethod(alwaysRun=true)
	public void teardown() {
		if(driver.get()!= null) {
			
			driver.get().quit();
			driver.remove();
			System.out.println("TearDown Successfully!...");
		}
	
		
	}
     
}

