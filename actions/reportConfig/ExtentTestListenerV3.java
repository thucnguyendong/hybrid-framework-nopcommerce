package reportConfig;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import commons.BaseTest;

public class ExtentTestListenerV3 extends BaseTest implements ITestListener {
	private static ExtentReports extent = ExtentManagerV3.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private ExtentTest extentTest;

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Extent Reports Version 3 Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Extent Reports Version 3 Test Suite is ending!"));
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test.get().pass(result.getMethod().getMethodName() + " passed!");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getWebdriver();
		try {
			test.get().log(Status.FAIL,"Test failed!",MediaEntityBuilder.createScreenCaptureFromBase64String(saveScreenShootAsBase64(webDriver)).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.get().fail(result.getThrowable());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}
}