package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import support.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	WebDriver driver;
	
	

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	

By payment_Page_header = By.xpath("//*[text()=' Payment Method ']");
By country_dropdown =By.xpath("//*[@placeholder='Select Country']");
By options= By.xpath("//*[contains(@class, 'ta-item list-group-item')]");
By Expiry_Month=By.xpath("(//select[@class='input ddl'])[1]");
By Expiry_Date=By.xpath("(//select[@class='input ddl'])[2]");
By placeOrder= By.xpath("//a[text()='Place Order ']");
By name_On_Card= By.xpath("//*[contains(text(),'Name on Card')]/..//*[@class='input txt']");
By apply_Coupon=By.name("coupon");
By ccv_Code =By.xpath("//*[contains(text(),'CVV Code ')]/..//*[@class='input txt']");
By payment_Email=By.xpath("//*[contains(@class, 'input txt text-validated ng')]");
By Ordered_product=By.xpath("//div[@class='item__title']");



public String validateOrderedItem(String productName){
	
	return driver.findElement(Ordered_product).getText();
	
}

public void placeOrderAndConfirm() {
	driver.findElement(placeOrder).click();
	
}
public boolean isPaymentPageHeaderDisplayed() {
	
	waitForElementToAppear(payment_Page_header);
	boolean displayed =driver.findElement(payment_Page_header).isDisplayed();
	return displayed;
	}

public void completePersonalInformation() {
	
	
	selectByIndex(Expiry_Month, 01);
	selectByIndex(Expiry_Date, 25);
	driver.findElement(name_On_Card).sendKeys("Papu Pandu");
	driver.findElement(ccv_Code).sendKeys("3456");;
	waitForElementToBeClickable(placeOrder);
	
	
}

public String getPaymentEmail() {
	
	return driver.findElement(payment_Email).getAttribute("value");
	
}
public OrderPage completeShippingInformation_and_placeOrder() {
	
	selectFromAutoSuggestive(country_dropdown,options,"India" );
	return new OrderPage(driver);
	
}



}
