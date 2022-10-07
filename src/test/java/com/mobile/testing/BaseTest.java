package com.mobile.testing;

import com.mobile.testing.driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {

  @BeforeClass
  public void createSession() {
    DriverManager.getDriver();
  }

  @AfterMethod
  public void resetApp() {
    DriverManager.getDriver().resetApp();
  }

  @AfterClass
  public void closeSession() {
    DriverManager.closeDriver();
    DriverManager.closeAppium();
    DriverManager.closeEmulator();
  }
}
