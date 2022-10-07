package com.mobile.testing.driver;

import static java.lang.String.format;

import com.mobile.testing.configuration.AddressConfigurator;
import com.mobile.testing.configuration.CapabilitiesConfigurator;
import com.mobile.testing.configuration.ConfigurationReader;
import com.mobile.testing.configuration.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverManager {

  private static final Logger LOG = LogManager.getRootLogger();
  private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType.valueOf(
      ConfigurationReader.get().env().toUpperCase());
  private static AppiumDriver<MobileElement> driver;

  private DriverManager() {
  }

  public static AppiumDriver<MobileElement> getDriver() {
    if (driver == null) {
      driver = createDriver();
    }
    return driver;
  }

  private static AppiumDriver<MobileElement> createDriver() {
    switch (ENVIRONMENT_TYPE) {
      case LOCAL:
        driver = new AndroidDriver<>(
            AddressConfigurator.getAppiumDriverLocalService(ConfigurationReader.get().appiumPort()),
            CapabilitiesConfigurator.getLocalCapabilities());
        break;
      default:
        throw new IllegalArgumentException(
            format("Unexpected environment value: %s", ENVIRONMENT_TYPE));
    }
    LOG.info("Driver is created");
    LOG.info("Environment type is " + ENVIRONMENT_TYPE);
    return driver;
  }

  public static void closeDriver() {
    Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
      driverInstance.quit();
      driver = null;
      LOG.info("Driver was closed");
    });
  }

  public static void closeEmulator() {
    try {
      Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.get().udid()));
      LOG.info("Emulator was closed");
    } catch (IOException e) {
      LOG.info("Emulator wasn't closed, message: " + e.getMessage());
    }
  }

  public static void closeAppium() {
    AddressConfigurator.stopService();
  }


}
