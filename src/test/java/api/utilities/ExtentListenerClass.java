package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {
	
	ExtentSparkReporter htmlReporter; //user interface
	ExtentReports reports; //common details
	ExtentTest test;
	
	public void configureReport() {
		
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "PetStoreReport"+timeStamp+".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//"+reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		reports.setSystemInfo("Machine: ", "testpc1");
		reports.setSystemInfo("OS: ", "windows 11");
		reports.setSystemInfo("user name: ", "Dhanush");
		
		htmlReporter.config().setDocumentTitle("PetStore Automation Report");
		htmlReporter.config().setReportName("PetStore exectuion result");
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("On start method invoked....");
	}

	public void onFinish(ITestContext context) {
		reports.flush();
		System.out.println("On finish method invoked....");
	}


	public void onTestStart(ITestResult result) {
        
		System.out.println("Name of test method started: "+result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println("Name of test method successfully executed:" + result.getName() );
		
		test = reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed testcase is: "+result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Name of test method failed:" + result.getName() );
		
		test = reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the passed testcase is: "+result.getName(), ExtentColor.RED));
		
		String screenshotpath = System.getProperty("user.dir")+"//Screenshots//"+result.getName()+".png";
		//File screenshotFile = new File(screenshotpath);
		
//		if(screenshotFile.exists()) {
//			test.fail("Captured Screenshot is below: "+test.addScreenCaptureFromPath(screenshotpath));
//		}
		
	}

	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Name of test method skipped:" + result.getName() );
		
		test = reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped testcase is: "+result.getName(), ExtentColor.ORANGE));
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
         
		
	}
	
	

}
