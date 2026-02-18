package page;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import support.AbstractComponent;

public class productPage  extends AbstractComponent{
	
	WebDriver driver;
	
/*	public productPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	@FindBy(css=".card")
	List<WebElement> productList;
	
	By addToCart =By.xpath(".//button[ contains( text() ,'Add To Cart')]");
	By productListBy=By.cssSelector(".mb-3");
	By alert_product_added_to_cart = By.xpath(" //div [contains(text(),'Product Added To Cart')] ");
	By go_To_cart = By.xpath("//*[@routerlink='/dashboard/cart']");
	By Buy_Now_btn= By.xpath("//*[contains(@class, 'btn btn-primary') and contains(text(), 'Buy Now')]");
	By Thanks_For_Order = By.xpath("//h1[@class='hero-primary' ]");
	By spinner = By.cssSelector(".ngx-spinner-overlay");

	public productPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	private List<WebElement> getProductList()
	{
		waitForElementToAppear(productListBy);
		System.out.println(productListBy);
        return productList;
        
    }
	
	//private String getProductName(String productName)
	/*{
		for(WebElement product:getProductList()) {
			
			String ActualName=product.findElement(By.cssSelector("h5 b")).getText().trim();
			System.out.println("Checking=" +ActualName);
			
			if(ActualName.equalsIgnoreCase(productName)) {
				System.out.println("MatchedProduct="+ActualName);
				
				return ActualName;
		}
	}
		throw new NoSuchElementException("Product not found: " + productName); }
	*/
	private WebElement getProductName(String productName)
	{
	  WebElement product=getProductList().stream().filter(products->products.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase(productName)).findFirst() .orElse(null);
      if(product==null) {
    	  throw new RuntimeException("Product not found: " + productName);
      }
	  return product;	  
	}
	
	public void addToCart(String productName) {
	    WebElement product = getProductName(productName);

	    String productZ = product.findElement(By.cssSelector("h5 b")).getText().trim();
	    if (productZ.equalsIgnoreCase(productName)) {
	        // IMPORTANT: search inside the matched product card
	    	waitForElementToDisappear( spinner);
	        WebElement addButton= product.findElement(addToCart);
	        clickWithScroll(addButton);
	    }

	    System.out.println("Clicked Add To Cart for: " + productZ);
	    
	    waitForElementToAppear(alert_product_added_to_cart);
	}



	public void goToCart() {
		
		
		clickWithSafe(driver.findElement(go_To_cart));
		
	}
	
	public PaymentPage clickBuyNow()
	{
		driver.findElement(Buy_Now_btn).click();
		return new PaymentPage(driver);
	}


	
}