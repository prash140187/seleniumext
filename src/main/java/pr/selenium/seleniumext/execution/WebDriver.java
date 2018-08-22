/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.execution;

import java.io.File;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Prashant
 */
public class WebDriver {
    
    public WebDriver(String url){
        super();
        this.init(url);
        this.initialised = true;
    }
    
    private static final Log LOG = LogFactory.getLog(WebDriver.class);
    private static org.openqa.selenium.WebDriver driver;
    
    private final boolean initialised;
    
    public org.openqa.selenium.WebDriver getDriver(){
        if(!initialised) throw new IllegalArgumentException("Driver not initialized");
        return driver;
    }
    
    private static final String CHROME_PATH;
    private static final String IE_PATH;
    private static final String IE_VERSION;
    private static final String IE_EXE_NAME;
    private static final String CHROME_EXE_NAME;
    private static final String RUNNABLE_EXTENSION;
    private static final boolean IS_CHROME;
    
    static {
        CHROME_PATH = System.getProperty("chromedriver.path");
        RUNNABLE_EXTENSION = System.getProperty("driver.extn") != null ? 
                "." + System.getProperty("driver.extn") : ".exe";
        IE_PATH = System.getProperty("iedriver.path");
        IE_VERSION = System.getProperty("iedriver.version") != null ? 
                System.getProperty("iedriver.version") : "11";
        IE_EXE_NAME = (System.getProperty("iedriver.exe") != null ? 
                System.getProperty("iedriver.exe") : "IEDriverServer_x86") + RUNNABLE_EXTENSION;
        CHROME_EXE_NAME = (System.getProperty("chromedriver.exe") != null ? 
                System.getProperty("chromedriver.exe") : "chromedriver") + RUNNABLE_EXTENSION;
        
        if(StringUtils.isEmpty(IE_PATH)){
            LOG.info("CHROME DRIVER LOCATION: " + CHROME_PATH + "\\" + CHROME_EXE_NAME);
            IS_CHROME = true;
        }else{
            LOG.info("IE DRIVER LOCATION: " + IE_PATH + "\\" + IE_EXE_NAME);
            LOG.info("IE BROWSER VERSION: " + IE_VERSION);
            IS_CHROME = false;
        }
    }
    
    public final void init(String url) {
        if(IS_CHROME) initCHROME();
        else initIE();
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }
    
    private static void initCHROME() {
        File file = new File(CHROME_PATH + "\\" + CHROME_EXE_NAME);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability("requireWindowFocus", false);
        driver = new ChromeDriver(cap);
    }
    
    private static void initIE() {
        File file = new File(IE_PATH + "\\" + IE_EXE_NAME);
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        cap.setCapability(CapabilityType.VERSION, IE_VERSION);
        cap.setCapability("requireWindowFocus", false);
        driver = new InternetExplorerDriver(cap);
    }
    
    public void closeDriver(){
        try{
            if(driver != null) driver.close();
        } catch(Exception e) {
            LOG.error(e);
        }
        
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM " + CHROME_EXE_NAME);
            Runtime.getRuntime().exec("taskkill /F /IM " + IE_EXE_NAME);
        } catch(IOException e) {
            LOG.error(e);
        }
    }
    
    public Object executeScript(String script){
        LOG.debug(script);
        return ((JavascriptExecutor) getDriver()).executeScript(script);
    }
    
    public void refresh(){
        getDriver().navigate().refresh();
    }
    
    public JavascriptExecutor getJs(){
        return (JavascriptExecutor) getDriver();
    }
    
}
