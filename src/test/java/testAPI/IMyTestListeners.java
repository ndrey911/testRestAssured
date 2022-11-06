package testAPI;

import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class IMyTestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
