package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import support.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	By orders_tab= By.xpath("//button[@routerlink='/dashboard/myorders']");
	By ordered_list=By.xpath("//tr//td[2]");
	
	
    public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	boolean isFound=false;
	
	public void goToOrderTab() {
		driver.findElement(orders_tab).click();
	}
	
	public boolean validateOrderInMyOrders(String productName) {
		List<WebElement> orderedProductName= driver.findElements(ordered_list);
		
	waitForElementToAppear(orders_tab);
	
	for (WebElement orderedProductNames : orderedProductName)
	{
		if(orderedProductNames.getText().equalsIgnoreCase(productName))
		isFound=true;
		break;	
	}
	
	return isFound;
	}
	
	public boolean ValidatingOrder(String productName) {
		List<WebElement> orderedProductName= driver.findElements(ordered_list);
		
		boolean ordered =orderedProductName.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
		return ordered ;
	}
	
	
}