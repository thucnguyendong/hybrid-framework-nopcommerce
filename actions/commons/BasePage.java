
package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.portal.UserHomePageObject;
import pageObjects.nopcommerce.portal.myweb.UserAddressPageObject;
import pageObjects.nopcommerce.portal.myweb.UserBackInStockSubscriptionPageObject;
import pageObjects.nopcommerce.portal.myweb.UserChangePasswordPageObject;
import pageObjects.nopcommerce.portal.myweb.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.portal.myweb.UserDownloadableProductPageObject;
import pageObjects.nopcommerce.portal.myweb.UserMyProductReviewPageObject;
import pageObjects.nopcommerce.portal.myweb.UserOrderPageObject;
import pageObjects.nopcommerce.portal.myweb.UserRewardPointPageObject;
import pageUI.nopcommerce.admin.AdminBasePageUI;
import pageUI.nopcommerce.portal.UserBasePageUI;

public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	/** 
	 * Open browser
	 * @param driver
	 * @param Url of page
	 */	
	public void openBrowser(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	/** 
	 * Get Title of the current page
	 * @param driver
	 * @return the title of the current page
	 */	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	/** 
	 * Get URL of the current page
	 * @param driver
	 * @return the url of the current page
	 */	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	/** 
	 * Get html page source code of the current page
	 * @param driver
	 * @return the html source text of the current page
	 */	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	/** 
	 * Get all cookies of the current page
	 * @param driver
	 * @return list of all cookies
	 */	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	
	/** 
	 * Set cookie to the current page
	 * @param driver
	 * @return null
	 */	
	public void setAllCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie: cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(5);
		driver.navigate().refresh();
	}
	
	/** 
	 * Back to previous page of the web
	 * @param driver
	 */	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
		
	/** 
	 * Forward to next page of the web
	 * @param driver
	 */	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	/** 
	 * Refresh the current page of the web
	 * @param driver
	 */		
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	/** 
	 * Wait for the browser alert to appear
	 * @param driver
	 */		
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	/** 
	 * Confirm the browser alert
	 * @param driver
	 */		
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	/** 
	 * Close the browser alert
	 * @param driver
	 */		
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	/** 
	 * Get the text of the browser alert
	 * @param driver
	 */		
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	/** 
	 * Switch to another tab (only 2 tabs)
	 * @param driver
	 * @param ID of the first/home/default tab
	 */	
	public void switchToWindowNotHomeByID(WebDriver driver,String homeID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(homeID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	/** 
	 * Switch to another tab using window/tab title
	 * @param driver
	 * @param title of the another tab
	 */	
	public void switchToWindowByTitle(WebDriver driver,String windowTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(windowTitle)) {
				break;
			}
		}
	}
	
	/** 
	 * Close all tabs except the first/home/default tab
	 * @param driver
	 * @param ID of the first/home/default tab
	 */	
	public void closeAllWithoutParent(WebDriver driver,String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
		
	/** 
	 * Input text to the browser alert
	 * @param driver
	 */		
	public void inputToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	/** 
	 * Get xpath xpathLocator of the element
	 * @param xpath of the element
	 * @return xpathLocator of the element
	 */	
	private By getByXpath(String xpath) {
		return By.xpath(xpath);
	}
	
	/** 
	 * Find and get the Web Element
	 * @param driver
	 * @param xpathLocator xpathLocator of the element
	 * @return web element found
	 */	
	private WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	/** 
	 * Find and get the list of Web Element
	 * @param driver
	 * @param xpathLocator xpathLocator of elements
	 * @return list of web element found
	 */	
	private List<WebElement> getListElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	private String getDynamicLocator(String xpathLocator, String...params) {
		return String.format(xpathLocator, (Object[])params);
	}
	/** 
	 * Click element
	 * @param driver
	 * @param xpathLocator of the element
	 */	
	public void clickElement(WebDriver driver, String xpathLocator) {
		getElement(driver,xpathLocator).click();
	}
	
	public void clickElement(WebDriver driver, String locator, String...params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}
	
	/**
	 * Input string value into element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param input value inputed into the element
	 */	
	public void inputIntoElement(WebDriver driver, String xpathLocator, String input) {
		WebElement element = getElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(input);
	}
	
	public void inputIntoElement(WebDriver driver, String xpathLocator, String input, String...params) {
		WebElement element = getElement(driver, getDynamicLocator(xpathLocator, params));
		element.clear();
		element.sendKeys(input);
	}
	
	/**
	 * Select Element in the dropdownList
	 * @param driver
	 * @param xpathLocator of the element
	 * @param input visible text you want to select from the list
	 */	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String input) {
		waitForAllElementsToBePresenced(driver, xpathLocator+"/option");
		Select selectDropdownList = new Select(getElement(driver, xpathLocator));
		selectDropdownList.selectByVisibleText(input);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String input, String...params) {
		xpathLocator = getDynamicLocator(xpathLocator, params);
		waitForAllElementsToBePresenced(driver, xpathLocator+"/option");
		Select selectDropdownList = new Select(getElement(driver, xpathLocator));
		selectDropdownList.selectByVisibleText(input);
	}
	
	/**
	 * Get text of the selected item in the dropdownList
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public String getItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select selectDropdownList = new Select(driver.findElement(getByXpath(xpathLocator)));
		return selectDropdownList.getFirstSelectedOption().getText();
	}
	
	/**
	 * Get text of the selected item in the dropdownList
	 * @param driver
	 * @param xpathLocator of the dropdown element
	 * @param xpathLocator of the items in dropdown element
	 * @param value you want to select in the dropdown
	 */
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String itemValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		getElement(driver, parentLocator).click();		
		List<WebElement> allItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
		
		for (WebElement item: allItems) {
			if (item.getText().equals(itemValue)) {
				if(!item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInSecond(1);
				}
				item.click();
				break;
			}
		}
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @return element text
	 */	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).getText();
	}
	
	public String getElementText(WebDriver driver, String xpathLocator, String...params) {
		return getElement(driver, getDynamicLocator(xpathLocator, params)).getText();
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param attribute tag name of the element
	 * @return attribute value
	 */	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String...params) {
		return getElement(driver, getDynamicLocator(xpathLocator, params)).getAttribute(attributeName);
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param css tag name of the element
	 * @return css value of
	 */	
	public String getCssValue(WebDriver driver, String xpathLocator, String cssValue) {
		return getElement(driver, xpathLocator).getCssValue(cssValue);
	}
	
	/**
	 * get color of the rgba
	 * @param driver
	 * @return css value as string
	 */	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	/**
	 * Get size of multiple the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @return size of element list-
	 */
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator)).size();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator, String... params) {
		return driver.findElements(getByXpath(getDynamicLocator(xpathLocator, params))).size();
	}
	
	/**
	 * Check the checkbox to be selected before unchecking it
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	/**
	 * Check the checkbox to be unselected before checking it
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	/**
	 * Check the element to be enabled
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}
	
	/**
	 * Check the element to be displayed
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String...params) {
		return getElement(driver, getDynamicLocator(xpathLocator, params)).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		overideImplicitTimeout(driver,GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListElement(driver, xpathLocator);
		overideImplicitTimeout(driver,GlobalConstants.LONG_TIMEOUT);
		if(elements.size()==0) {
			return true;
		}
		else if(elements.size()>0 && (!elements.get(0).isDisplayed())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void overideImplicitTimeout(WebDriver driver, int timeouts) {
		driver.manage().timeouts().implicitlyWait(timeouts, TimeUnit.SECONDS);
	}
	
	/**
	 * Check the element to be selected
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isSelected();
	}
	
	/**
	 * Switch back to default/main frame
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * Switch to Frame/ iFrame
	 * @param driver
	 * @param xpathLocator of the frame
	 */
	public void switchToFrame(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getElement(driver, xpathLocator));
	}
	
	/**
	 * Hover to element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void moveToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, xpathLocator)).perform();
	}
	
	/**
	 * Interact with keyboard
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void sendKeyboardToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, xpathLocator),key).perform();
	}
	
	/**
	 * Inject a java script to web driver
	 * @param driver
	 * @param javaScript script user want to input
	 * @return result after script running (can be void, text, number...)
	 */
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}
	
	/**
	 * Get the innerText property of element
	 * @param driver
	 * @return innerText of Element
	 */
	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	/**
	 * Get the shadow element from root element
	 * @param driver
	 * @param element of root
	 * @return innerText of Element
	 */
	public WebElement getShadowDOM(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;",getElement(driver, xpathLocator)); 
	}
	
	/**
	 * Compare the innerText property of element with expected text
	 * @param driver
	 * @return true/false
	 */
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}
	
	/**
	 * Scroll to the bottom of the page
	 * @param driver
	 */
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * Navigate to another URL
	 * @param driver
	 * @param url of the landed page
	 */
	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	/**
	 * highlight the border of the element red
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	/**
	 * click the element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, xpathLocator));
	}
	
	/**
	 * scroll to the element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
	}
	
	/**
	 * input value to the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param value inputed into element
	 */
	public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, xpathLocator));
	}

	/**
	 * remove attribute of the element in DOM
	 * @param driver
	 * @param xpathLocator of the element
	 * @param attributeRemove attribute tag name of the element that user want to remove
	 */
	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
	}

	/**
	 * check that all jquery and javascript in the page are loaded fully
	 * @param driver
	 * @return true/false
	 */
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	/**
	 * get the validation message of the element (not in DOM)
	 * @param driver
	 * @param xpathLocator of the element
	 * @return validation message text
	 */
	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
	}

	/**
	 * check that the image is loaded fully
	 * @param driver
	 * @param locator of the image
	 * @return true/false
	 */
	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * wait for the element to display
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}
	
	/**
	 * wait for all elements to be display
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
		
	/**
	 * wait for the element to be clickable
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, params))));
	}
		
	/**
	 * wait for the element to be invisible/no long display
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}
	
	/**
	 * wait for all elements to be invisible/no long display
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	public void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, xpathLocator)));
	}
	
	/**
	 * wait for all elements to be presence
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	public void waitForAllElementsToBePresenced(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	
	public boolean waitForStaleness(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		return wait.until(ExpectedConditions.stalenessOf(driver.findElement(getByXpath(xpathLocator))));
	}
	
	public boolean waitForStaleness(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		return wait.until(ExpectedConditions.stalenessOf(driver.findElement(getByXpath(getDynamicLocator(xpathLocator, params)))));
	}
	
	public String getMultipleFileNames(String... fileNames) {
		String fullName = "";
		for (String file:fileNames) {
			fullName = fullName+file+"\n";
		}
		fullName = fullName.trim();
		return GlobalConstants.UPLOAD_FOLDER_PATH+fullName;
	}
	
	public boolean isDataStringSortedAscending(WebDriver driver, String xpathLocator) {
		List<WebElement> elementList = getListElement(driver, xpathLocator);
		ArrayList<String> arrayList = new ArrayList<>();
		for (WebElement element: elementList) {
			arrayList.add(element.getText());
		}
		
		ArrayList<String> sortedList = new ArrayList<>();
		sortedList.addAll(arrayList);
		Collections.sort(sortedList);
		return arrayList.equals(sortedList);
	}
	
	public boolean isDataStringSortedDescending(WebDriver driver, String xpathLocator) {
		List<WebElement> elementList = getListElement(driver, xpathLocator);
		ArrayList<String> arrayList = new ArrayList<>();
		for (WebElement element: elementList) {
			arrayList.add(element.getText());
		}
		
		ArrayList<String> sortedList = new ArrayList<>();
		sortedList.addAll(arrayList);
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return arrayList.equals(sortedList);
	}
	
	public boolean isDataFloatSortedAscending(WebDriver driver, String xpathLocator) {
		List<WebElement> elementList = getListElement(driver, xpathLocator);
		ArrayList<Float> arrayList = new ArrayList<>();
		for (WebElement element: elementList) {
			String str = element.getText().replaceAll("[^\\d.-]", "");
			arrayList.add(Float.parseFloat(str));
		}
		
		ArrayList<Float> sortedList = new ArrayList<>();
		sortedList.addAll(arrayList);
		Collections.sort(sortedList);
		return arrayList.equals(sortedList);
	}
	
	public boolean isDataFloatSortedDescending(WebDriver driver, String xpathLocator) {
		List<WebElement> elementList = getListElement(driver, xpathLocator);
		ArrayList<Float> arrayList = new ArrayList<>();
		for (WebElement element: elementList) {
			String str = element.getText().replaceAll("[^\\d.-]", "");
			arrayList.add(Float.parseFloat(str));
		}
		
		ArrayList<Float> sortedList = new ArrayList<>();
		sortedList.addAll(arrayList);
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return arrayList.equals(sortedList);
	}
	
	public boolean isDataDateSortedAscending(WebDriver driver, String xpathLocator) {
		List<WebElement> elementList = getListElement(driver, xpathLocator);
		ArrayList<Date> arrayList = new ArrayList<>();
		for (WebElement element: elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}
		
		ArrayList<Date> sortedList = new ArrayList<>();
		sortedList.addAll(arrayList);
		Collections.sort(sortedList);
		return arrayList.equals(sortedList);
	}
	

	public boolean isDataDateSortedDescending(WebDriver driver, String xpathLocator) {
		List<WebElement> elementList = getListElement(driver, xpathLocator);
		ArrayList<Date> arrayList = new ArrayList<>();
		for (WebElement element: elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}
		
		ArrayList<Date> sortedList = new ArrayList<>();
		sortedList.addAll(arrayList);
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return arrayList.equals(sortedList);
	}
	
	private Date convertStringToDate(String dateString) {
		dateString = dateString.replaceAll("[^\\d]", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * Create a random number
	 */	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	/**
	 * Create a random email
	 * @param: prefix of the email
	 * @param: domain name of the email
	 * @return: generated email
	 */	
	public String getRandomEmail(String prefix, String domain) {
		return prefix+getRandomNumber()+"@"+domain+".com";
	}
	
	/**
	 * Hard wait the test
	 * @param timeoutInSecond waiting time in second
	 */	
	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}
	
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		clickElement(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getPageGenerator().getPageGenerator().getUserCustomerInfoPage(driver);
	}
	
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADDRESS_LINK);
		clickElement(driver, UserBasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getPageGenerator().getUserAddressPage(driver);
	}
	
	public UserOrderPageObject openOrderPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ORDER_LINK);
		clickElement(driver, UserBasePageUI.ORDER_LINK);
		return PageGeneratorManager.getPageGenerator().getUserOrderPage(driver);
	}
	
	public UserDownloadableProductPageObject openDownloadableProductPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		clickElement(driver, UserBasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		return PageGeneratorManager.getPageGenerator().getUserDownloadableProductPage(driver);
	}
	
	public UserBackInStockSubscriptionPageObject openBackInStockSubscriptionPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.BACK_IN_STOCK_SUBSCRIPTION_LINK);
		clickElement(driver, UserBasePageUI.BACK_IN_STOCK_SUBSCRIPTION_LINK);
		return PageGeneratorManager.getPageGenerator().getUserBackInStockSubscriptionPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.REWARD_POINT_LINK);
		clickElement(driver, UserBasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getPageGenerator().getUserRewardPointPage(driver);
	}
	
	public UserChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CHANGE_PASSWORD_LINK);
		clickElement(driver, UserBasePageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getPageGenerator().getUserChangePasswordPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyReviewPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickElement(driver, UserBasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getPageGenerator().getUserMyProductReviewPage(driver);
	}
	
	public UserCustomerInfoPageObject clickMyAccountLink(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, UserBasePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getPageGenerator().getUserCustomerInfoPage(driver);
	}
	
	public UserHomePageObject clickUserLogOutLink(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK_AT_USER);
		clickElement(driver, UserBasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickAdminLogOutLink(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickElement(driver, UserBasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getPageGenerator().getAdminLoginPage(driver);
	}
	
	public AdminLoginPageObject openAdminPage(WebDriver driver,String url) {
		openBrowser(driver, url);
		return PageGeneratorManager.getPageGenerator().getAdminLoginPage(driver);
	}
	
	public UserHomePageObject openPortalPage(WebDriver driver,String url) {
		openBrowser(driver, url);
		return PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
	
	public void clickAdminSideMenuItem(WebDriver driver, String item) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_SIDE_MENU_ITEM, item);
		clickElement(driver, AdminBasePageUI.DYNAMIC_SIDE_MENU_ITEM, item);
	}
	
	public void clickAdminSideMenuSubItem(WebDriver driver, String item) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_SIDE_SUB_MENU_ITEM, item);
		clickElement(driver, AdminBasePageUI.DYNAMIC_SIDE_SUB_MENU_ITEM, item);
	}
	
	public void inputToTextboxByID(WebDriver driver, String textbox_id, String value) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID,textbox_id);
		inputIntoElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textbox_id);
	}
	
	public void selectDropdownListByName(WebDriver driver, String dropdown_name, String value) {
		selectItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_LIST_BY_NAME, value, dropdown_name);
	}
	
	public void clickButtonByText(WebDriver driver, String button_text) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT,button_text);
		clickElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, button_text);
	}
}
