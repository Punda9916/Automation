package testcase;

import org.testng.annotations.Test;
import org.testng.Assert;

import base.BaseTest;
import page.OrderPage;
import page.PaymentPage;
import page.loginPage;
import page.productPage;

public class PaymentPageTest extends BaseTest{

	static String productName = "ZARA COAT 3";
	
	@Test
	
	public void Payment() {
		loginPage loginpage = new loginPage(getDriver());
		
		productPage productpage =loginpage.loginApplication("papupandu@gmail.com", "Papupandu@123");
		String login_Email="papupandu@gmail.com";
		productpage.addToCart(productName);
		productpage.goToCart();
		
		PaymentPage paymentpage = productpage.clickBuyNow();		
		Assert.assertTrue(paymentpage.isPaymentPageHeaderDisplayed());
		
		paymentpage.completePersonalInformation();
		OrderPage orderpage =paymentpage.completeShippingInformation_and_placeOrder();
		String paymentpage_Email= paymentpage.getPaymentEmail();
        Assert.assertEquals(login_Email, paymentpage_Email);
        
        String Ordered_Product=paymentpage.validateOrderedItem(productName);
        Assert.assertEquals(productName, Ordered_Product);
		
	}
	

	
}
