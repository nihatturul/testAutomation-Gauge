import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

	public static WebDriver driver;
	protected final Logger log = Logger.getLogger(getClass());
	//public static String environment = System.getProperty("environment","PROD");

	@BeforeScenario
	public void setUp() throws MalformedURLException, Exception {

		String baseUrl = "https://www.n11.com";
		DesiredCapabilities capabilities;

		if (StringUtils.isEmpty(System.getProperty("key"))){
			capabilities = DesiredCapabilities.chrome();
//			capabilities = DesiredCapabilities.firefox();
//			FirefoxOptions options = new FirefoxOptions();
			
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--kiosk");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			driver = new ChromeDriver(capabilities);
//			System.setProperty("webdriver.gecko.driver", "properties/driver/geckodriver");
//			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
//			options.setProfile(new FirefoxProfile());
//			capabilities = DesiredCapabilities.firefox();
//			capabilities.setBrowserName("firefox");
//			capabilities.setCapability(FirefoxDriver.MARIONETTE, false);
		//	capabilities.setCapability(FirefoxDriver.BINARY, "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
//			capabilities.setCapability(FirefoxDriver.PROFILE, options);
//			driver = new FirefoxDriver(capabilities);
		}
		else {
			capabilities =  DesiredCapabilities.firefox();
			capabilities.setCapability("key", System.getProperty("key"));
			driver = new RemoteWebDriver(new URL("http://hub.testinium.io/wd/hub"), capabilities);
			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		}


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterScenario
	public void tearDown() throws Exception {
		driver.quit();
	}
}