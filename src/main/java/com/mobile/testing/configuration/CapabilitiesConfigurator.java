package com.mobile.testing.configuration;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.AVD;
import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;

import java.io.File;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesConfigurator {

  private CapabilitiesConfigurator() {}

  public static DesiredCapabilities getLocalCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(UDID, ConfigurationReader.get().udid());
    capabilities.setCapability(AVD, ConfigurationReader.get().localDeviceName());
    capabilities.setCapability(APP_PACKAGE, ConfigurationReader.get().appPackage());
    capabilities.setCapability(APP_PACKAGE, ConfigurationReader.get().appPackage());
    capabilities.setCapability(APP_ACTIVITY, ConfigurationReader.get().appActivity());
    capabilities.setCapability(APP, new File(ConfigurationReader.get().appPath()).getAbsolutePath());

    return capabilities;
  }

}
