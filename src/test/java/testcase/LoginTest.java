package testcase;

import org.testng.annotations.Test;


import base.BaseTest;
import page.loginPage;


public class LoginTest extends BaseTest{

	static String productName = "ZARA COAT 3";

	@Test(groups="Login")
	
public  void logging() 

	{
		System.out.println("Driver in test: " + getDriver());
	loginPage loginpage = new loginPage(getDriver());
	loginpage.loginApplication("papupandu@gmail.com", "Papupandu@123");
	
	
	}
	
	
	/*productPage productpage=new productPage(driver);
	productpage.loginApplication("papupandu@gmail.com", "Papupandu@123");
	productpage.getProductName(productName);
	productpage.addToCart(productName);
	productpage.checkOut();*/

	
	}

	

	//div[@class='card-body']/button[2]
	
	
   // productPage productpage=new productPage(driver);
   //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
   // WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("[id='toast-container']"))));
   /* List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
    Boolean cart = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
    System.out.println(cart);
    Assert.assertTrue(cart);*/

	