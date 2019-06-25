import com.oracle.tools.packager.Log;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WaitTool;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BasePageUtil extends BaseTest {

    protected WebDriver driver = BaseTest.driver;
    protected Logger log = Logger.getLogger(getClass());




    public BasePageUtil() {

    }

    public BasePageUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }


    protected WebElement waitForPresenceByElement(By by) {

       return WaitTool.waitForElementPresent(driver,by,10);
    }

    protected void  mouseHoverElement(){

    }

}
