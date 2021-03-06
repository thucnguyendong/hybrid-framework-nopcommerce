package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.portal.UserProductPageUI;

public class UserProductPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductPageObject(WebDriver driver) {
		this.driver = driver;
	}	
	public UserProductReviewPageObject clickReview() {
		waitForElementClickable(driver, UserProductPageUI.ADD_REVIEW_BUTTON);
		clickElement(driver, UserProductPageUI.ADD_REVIEW_BUTTON);
		return PageGeneratorManager.getPageGenerator().getUserProductReviewPage(driver);
	}
}