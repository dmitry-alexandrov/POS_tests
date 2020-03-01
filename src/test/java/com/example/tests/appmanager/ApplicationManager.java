package com.example.tests.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    private final Properties properties;
    WebDriver driver;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private PollHelper pollHelper;
    private LocalPollHelper localPollHelper;
    private LocalValueHelper localValueHelper;
    private LocalDiscussionHelper localDiscussionHelper;
    private ContestHelper contestHelper;
    private ProjectHelper projectHelper;
    private NewsHelper newsHelper;
    public String baseUrl;
    public StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() {

     if ("".equals(properties.getProperty("selenium.server"))) {

         if (browser.equals(BrowserType.FIREFOX)) {
             driver = new FirefoxDriver();
         } else if (browser.equals(BrowserType.CHROME)) {
             System.setProperty("webdriver.chrome.driver", "C:\\Tools\\Tools\\chromedriver.exe");
             driver = new ChromeDriver();
         } else if (browser.equals(BrowserType.IE)) {
             driver = new InternetExplorerDriver();
         }
     } else {

         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setBrowserName(browser);
         driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);

        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://pos.maxitlab.com/og/login");
        pollHelper = new PollHelper(driver);
        localPollHelper = new LocalPollHelper(driver);
        localValueHelper = new LocalValueHelper(driver);
        localDiscussionHelper = new LocalDiscussionHelper(driver);
        contestHelper = new ContestHelper(driver);
        projectHelper = new ProjectHelper(driver);
        newsHelper = new NewsHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.login("admin", "APwM$25Ek4pEfXu1N");
        Dimension d = new Dimension(1920,1080);
//Resize the current window to the given dimension
        driver.manage().window().setSize(d);
    }



    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
    }

    public boolean isElementPresent(By by) {
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public PollHelper getPollHelper() {
        return pollHelper;
    }

    public LocalPollHelper getLocalPollHelper() { return localPollHelper; }

    public LocalValueHelper getLocalValueHelper() { return localValueHelper; }

    public LocalDiscussionHelper getLocalDiscussionHelper() { return localDiscussionHelper; }

    public ContestHelper getContestHelper() { return  contestHelper; }

    public ProjectHelper getProjectHelper() { return projectHelper; }

    public NewsHelper getNewsHelper() { return  newsHelper; }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
