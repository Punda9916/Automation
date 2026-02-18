package testcase;

import org.testng.annotations.Test;

import base.BaseTest;
import page.loginPage;
import page.productPage;


public class ProductPageTest extends BaseTest {
	
	static String productName = "ZARA COAT 3";
	@Test
	
public  void addProduct() 

	{
		
	loginPage loginpage = new loginPage(getDriver());
	productPage product_page =loginpage.loginApplication("papupandu@gmail.com", "Papupandu@123");
//	product_page.getProductList();
//	product_page.getProductName(productName);
	product_page.addToCart(productName);
	product_page.goToCart();
	}
	
	
	
			

}
