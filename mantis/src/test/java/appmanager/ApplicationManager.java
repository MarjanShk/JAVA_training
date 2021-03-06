package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Admin on 26.02.2016.
 */
public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private SessionHelper sessionHelper;
    private ManageUsersHelper manageUsersHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        //wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
    public SessionHelper getSessionHelper() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }
    public ManageUsersHelper manageUsers() {
        if(manageUsersHelper== null){
            manageUsersHelper = new ManageUsersHelper(this);
        }
        return manageUsersHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().window().maximize();
            wd.get(properties.getProperty("baseUrl"));
        }
        return wd;
    }

}
