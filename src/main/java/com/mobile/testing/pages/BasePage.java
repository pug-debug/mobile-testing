package com.mobile.testing.pages;

import com.mobile.testing.driver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

  public BasePage() {
    PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
  }

}
