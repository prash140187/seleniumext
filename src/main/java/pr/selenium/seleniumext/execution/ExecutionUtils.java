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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author ASUS
 */
public class ExecutionUtils {
    
    private ExecutionUtils(){}
    
    private static final Log log = LogFactory.getLog(ExecutionUtils.class);
    
    public static final String GET_CMP_TMPL = "Ext.ComponentQuery.query('%s')[%d]";
    public static final String GET_CMP = "return " + GET_CMP_TMPL + " != null;";
    public static final String VOID_ACTION = GET_CMP_TMPL + ".%s";
    public static final String RETURN_ACTION = "return " + VOID_ACTION;
    
    public static final String GET_COMBO_STORE = "var combo = " + GET_CMP_TMPL + "; var store = combo.getStore();";
    public static final String GET_VALUE_FOR_REC_IN_COMBO = GET_COMBO_STORE + " var rec = store.findRecord(combo.displayField, %s, 0, false, false, false); var val = rec != null ? rec.get(combo.valueField) : null;";
    public static final String GET_VALUE_FOR_REC_IN_COMBO_LN = GET_COMBO_STORE + " var rec = store.findRecord(combo.displayField, %d, 0, false, false, false); var val = rec != null ? rec.get(combo.valueField) : null;";
    public static final String SET_COMBO_RAW_VALUE = GET_VALUE_FOR_REC_IN_COMBO + " if(val != null){combo.setValue(val)}else{combo.setRawValue('%s')}"; 
    public static final String SET_COMBO_RAW_VALUE_LN = GET_VALUE_FOR_REC_IN_COMBO_LN + " if(val != null){combo.setValue(val)}else{combo.setRawValue('%s')}";
    
    public static final String FIRE_EVENT = "var cmp = " + GET_CMP_TMPL + "; cmp.fireEvent('%s', cmp %s);";
    public static final String MASK_LOADING = "return Ext.ComponentQuery.query('loadmask{isVisible() == true}').length > 0";
    
    public static final String MSG_BOX = "Ext.ComponentQuery.query('messagebox')[0]";
    public static final String MSG_BOX_VISIBLE = "return " + MSG_BOX + ".isVisible();";
    public static final String MSG_BOX_CLOSE = MSG_BOX + ".close();";
    public static final String MSG_BOX_TITLE = "return " + MSG_BOX + ".title";
    public static final String MSG_BOX_TEXT = "Ext.ComponentQuery.query('messagebox displayfield')[0].getEl().getHTML()";
    public static final String MSG_BOX_TEXT_SEARCH = "return "  + MSG_BOX_TEXT + ".search('%s') != -1;";
    public static final String MSG_BOX_ACTION = "var msg = " + MSG_BOX + "; var btn = Ext.ComponentQuery.query('button[text=%s]', msg)[0]; btn.btnEl.dom.click()";
    
    public static final String GET_DATA_INDX = "Ext.ComponentQuery.query('%s')[0].dataIndex";
    //public static final String GET_DATA_INDX = 
    public static final String GET_VALUE_FROM_GRID = "var prop = " + GET_DATA_INDX + 
            " return String(Ext.ComponentQuery.query('%s')[%d].getStore().getAt(%d-1).get(prop));";
    public static final String GRID_EDIT_PLUGIN = "var plugin = null; var plgns = " + GET_CMP_TMPL + ".plugins; " +
            "for(var i=0; i<plgns.length; i++){if(plgns[i].startEdit){plugin = plgns[i];break;}} ";
    public static final String GRID_EDIT = GRID_EDIT_PLUGIN + 
            "if(plugin){plugin.startEdit(%d-1, %d-1);}";
    public static final String GRID_EDIT_STOP = GRID_EDIT_PLUGIN + 
            "if(plugin){plugin.completeEdit();}";
    public static final String GRID_EDITOR = 
            "var editor = Ext.ComponentQuery.query('%s gridcolumn{getVisibleIndex() == (%d-1)}')[0].getEditor();";
    public static final String GRID_EDIT_VALUE = GRID_EDITOR + " editor.setValue('%s');";
    public static final String GRID_EDIT_VALUE_LN = GRID_EDITOR + " editor.setValue(%d);";
    public static final String GRID_RANGE = "var prop = " + GET_DATA_INDX + 
            "var range = Ext.ComponentQuery.query('%s')[%d].getStore().getRange();";
    public static final String GRID_CHECK_VALUE = GRID_RANGE + "if(range.length == 0){return false;}" + 
            "for(var i=0; i<range.length; i++){if(range[i].get(prop) != '%s'){return false;}} return true;";
    public static final String GRID_CHECK_VALUE_BOOLEAN = GRID_RANGE + "if(range.length == 0){return false;}" + 
            "for(var i=0; i<range.length; i++){if(range[i].get(prop) != %s){return false;}} return true;"; 
    
    private static WebDriver driver;
    
    public static WebDriver getDriver(){
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
            log.info("CHROME DRIVER LOCATION: " + CHROME_PATH + "\\" + CHROME_EXE_NAME);
            IS_CHROME = true;
        }else{
            log.info("IE DRIVER LOCATION: " + IE_PATH + "\\" + IE_EXE_NAME);
            log.info("IE BROWSER VERSION: " + IE_VERSION);
            IS_CHROME = false;
        }
    }
    
    public static void init(String url) {
        if(IS_CHROME) initCHROME();
        else initIE();
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }
    
    public static void initCHROME() {
        File file = new File(CHROME_PATH + "\\" + CHROME_EXE_NAME);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability("requireWindowFocus", false);
        driver = new ChromeDriver(cap);
    }
    
    public static void initIE() {
        File file = new File(IE_PATH + "\\" + IE_EXE_NAME);
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        cap.setCapability(CapabilityType.VERSION, IE_VERSION);
        cap.setCapability("requireWindowFocus", false);
        driver = new InternetExplorerDriver(cap);
    }
    
    public static void closeDriver(){
        try{
            if(driver != null) driver.close();
        } catch(Exception e) {
            log.error(e);
        }
        
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM " + CHROME_EXE_NAME);
            Runtime.getRuntime().exec("taskkill /F /IM " + IE_EXE_NAME);
        } catch(IOException e) {
            log.error(e);
        }
    }
    
    public static Object executeScript(String script){
        log.debug(script);
        return ((JavascriptExecutor) driver).executeScript(script);
    }
    
    public static void refresh(){
        getDriver().navigate().refresh();
    }
    
    public static JavascriptExecutor getJs(){
        return (JavascriptExecutor) getDriver();
    }
}
