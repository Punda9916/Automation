package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.OrderPage;
import page.loginPage;

public class OrderConfirmationPageTest extends BaseTest {

	static String productName = "ZARA COAT 3";
	@Test(dependsOnGroups="Login")
	
	
	public void orderConfirmation() {
		
		loginPage loginpage = new loginPage(getDriver());
		loginpage.loginApplication("papupandu@gmail.com", "Papupandu@123");
		
		OrderPage orderPage= new OrderPage(getDriver());
		orderPage.goToOrderTab();
		orderPage.ValidatingOrder(productName);
		Assert.assertTrue(orderPage.validateOrderInMyOrders(productName));
	} 
}
