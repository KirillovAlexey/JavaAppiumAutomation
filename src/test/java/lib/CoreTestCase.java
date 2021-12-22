package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    protected Platform platform;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.platform = new Platform();
        driver = this.platform.getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int time) {
        driver.runAppInBackground(time);
    }
//    private AppiumDriver getDriver() throws Exception {
//        String driverName = System.getenv("DRIVER");
//
//        if (driverName.equals(ANDROID_DRIVER)) {
//            driver = new AndroidDriver(new URL(appiumURL), this.getCapabilitiesByPlatformEnv());
//        } else if (driverName.equals(IOS_DRIVER)) {
//            driver = new IOSDriver(new URL(appiumURL), this.getCapabilitiesByPlatformEnv());
//        } else {
//            throw new Exception("Cannot get run driver from env variable. Driver value " + driverName);
//        }
//        return driver;
//    }
}
