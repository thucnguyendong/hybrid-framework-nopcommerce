package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.UserLoginPageObject;
import pageObjects.nopcommerce.portal.UserProductPageObject;
import pageObjects.nopcommerce.portal.UserProductReviewPageObject;
import pageObjects.nopcommerce.portal.UserRegisterPageObject;
import pageObjects.nopcommerce.portal.UserSearchPageObject;
import pageObjects.nopcommerce.portal.myweb.UserAddressPageObject;
import pageObjects.nopcommerce.portal.myweb.UserBackInStockSubscriptionPageObject;
import pageObjects.nopcommerce.portal.myweb.UserChangePasswordPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.portal.myweb.UserDownloadableProductPageObject;
import pageObjects.nopcommerce.portal.myweb.UserMyProductReviewPageObject;
import pageObjects.nopcommerce.portal.myweb.UserOrderPageObject;
import pageObjects.nopcommerce.portal.myweb.UserRewardPointPageObject;
import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.admin.AdminProductDetailPageObject;
import pageObjects.nopcommerce.admin.AdminProductsPageObject;

public class PageGeneratorManager {
	
	public static PageGeneratorManager getPageGenerator() {
		return new PageGeneratorManager();
	}

	public UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}
	
	public UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}
	
	public UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}
	
	public UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}
	
	public UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}
	
	public UserOrderPageObject getUserOrderPage(WebDriver driver) {
		return new UserOrderPageObject(driver);
	}
	
	public UserDownloadableProductPageObject getUserDownloadableProductPage(WebDriver driver) {
		return new UserDownloadableProductPageObject(driver);
	}
	
	public UserBackInStockSubscriptionPageObject getUserBackInStockSubscriptionPage(WebDriver driver) {
		return new UserBackInStockSubscriptionPageObject(driver);
	}

	public UserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}

	public UserProductPageObject getUserProductPage(WebDriver driver) {
		return new UserProductPageObject(driver);
	}

	public UserProductReviewPageObject getUserProductReviewPage(WebDriver driver) {
		return new UserProductReviewPageObject(driver);
	}
	
	
	public AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
	
	public AdminProductsPageObject getAdminProductsPage(WebDriver driver) {
		return new AdminProductsPageObject(driver);
	}
	
	public AdminProductDetailPageObject getAdminProductDetailPage(WebDriver driver) {
		return new AdminProductDetailPageObject(driver);
	}
}
