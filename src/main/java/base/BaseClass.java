package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utility.DockerStart;
import utility.DockerStop;

public class BaseClass {

	private static final Logger log = LogManager.getLogger(BaseClass.class);
	public static Properties prop;
	public WebDriver driver;

	private static void loadProperties() throws IOException {
		if (prop == null) {
			prop = new Properties();
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
				prop.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.error("Config file not found" + e);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				if (fis != null) {
					fis.close();
				}
			}
		}
	}

	@BeforeTest
	public void startDocker() throws IOException, InterruptedException {
		loadProperties();

		if (prop.getProperty("platformName").equalsIgnoreCase("Docker")) {
			DockerStart dockerstart = new DockerStart();
			dockerstart.startContainer();
			log.info("Docker Container Started successfully");
		}
	}

	@AfterTest
	public void stopDocker() throws IOException, InterruptedException {
		loadProperties();
		if (prop.getProperty("platformName").equalsIgnoreCase("Docker")) {
			DockerStop dockerstop = new DockerStop();
			dockerstop.stopContainer();
			log.info("Docker Container Stopped successfully");
		}
	}

	public WebDriver launchBrowser() throws IOException {
		loadProperties();
		String platformName = prop.getProperty("platformName");
		String browserName = prop.getProperty("browser");
		// For running in docker
		if (platformName.equalsIgnoreCase("Docker")) {

			if (browserName.equalsIgnoreCase("chrome")) {

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("chrome");

				URL url = new URL(prop.getProperty("DockerURL"));
				driver = new RemoteWebDriver(url, cap);
				log.info(browserName + " Invoked in" + " " + platformName);
			}

			else if (browserName.equalsIgnoreCase("firefox")) {

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("firefox");

				URL url = new URL(prop.getProperty("DockerURL"));
				driver = new RemoteWebDriver(url, cap);
				log.info(browserName + " Invoked in" + " " + platformName);
			}

			else if (browserName.equalsIgnoreCase("edge")) {

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("MicrosoftEdge");

				URL url = new URL(prop.getProperty("DockerURL"));
				driver = new RemoteWebDriver(url, cap);
				log.info(browserName + " Invoked in" + " " + platformName);
			}
		}

		else if (platformName.equalsIgnoreCase("Local")) {

			if (browserName.equalsIgnoreCase("chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(chromeOptions);
				log.info(browserName + " Invoked in" + " " + platformName);
			}

			else if (browserName.equalsIgnoreCase("firefox")) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver = new FirefoxDriver(firefoxOptions);
				log.info(browserName + " Invoked in" + " " + platformName);
			}

			else if (browserName.equalsIgnoreCase("edge")) {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--remote-allow-origins=*");
				driver = new EdgeDriver(edgeOptions);
				log.info(browserName + " Invoked in" + " " + platformName);
			}
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String destinationFile = (System.getProperty("user.dir") + "\\reports\\screenshot\\" + testCaseName + ".png");
		FileUtils.copyFile(source, new File(destinationFile));

		return destinationFile;
	}

}
