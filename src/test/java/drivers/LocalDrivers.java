package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.LocalConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDrivers implements WebDriverProvider {

    static LocalConfig config = ConfigFactory.create(LocalConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
//                .setPlatformVersion(config.version())
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.url());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
//        String appVersion = config.appVersion();
//        String appUrl = config.appUrl() + appVersion;
        String appPath = config.appPath();

        File app = new File(appPath);
//        if (!app.exists()) {
//            try (InputStream in = new URL(appUrl).openStream()) {
//                copyInputStreamToFile(in, app);
//            } catch (IOException e) {
//                throw new AssertionError("Failed to download application", e);
//            }
//        }
        return app.getAbsolutePath();
    }
}
