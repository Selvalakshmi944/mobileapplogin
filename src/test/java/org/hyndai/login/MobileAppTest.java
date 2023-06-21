package org.hyndai.login;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class MobileAppTest {
	public static AppiumDriver driver;

	public static void scrollDown() {
		Dimension size = driver.manage().window().getSize();
		Double startHeight = size.getHeight() * 0.5;
		int start = startHeight.intValue();
		Double endHeight = size.getHeight() * 0.2;
		int end = endHeight.intValue();
		TouchAction action = new TouchAction(driver);
		action.longPress(PointOption.point(0, start)).moveTo(PointOption.point(0, end)).release().perform();

	}

	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "Galaxy A22");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "12");
		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement optEnglish = driver.findElementByAccessibilityId("Select English");
		optEnglish.click();

		WebElement btnContinue = driver.findElement(By.id("continue_button"));
		btnContinue.click();

		WebElement btnSkipSignIn = driver.findElement(By.id("skip_sign_in_button"));
		btnSkipSignIn.click();

		WebElement optShopping = driver
				.findElement(By.xpath("//android.view.View[contains(@content-desc,'Keep shopping')]"));
		optShopping.click();

		scrollDown();

		WebElement btnByNow = driver.findElement(By.xpath("//*[@text='Buy Now']"));
		btnByNow.click();

		driver.quit();
	}

}
