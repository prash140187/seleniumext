/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.execution;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pr.selenium.seleniumext.component.AbstractComponent;
import pr.selenium.seleniumext.component.exception.ComponentNotFoundException;
import static pr.selenium.seleniumext.execution.Scripts.*;
import pr.selenium.seleniumext.query.QueryUtils;

/**
 *
 * @author Prashant
 */
public class ScriptExecutor {
    
    public ScriptExecutor(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    
    public ScriptExecutor(){}
    
    private static final Log LOG = LogFactory.getLog(ScriptExecutor.class);
    private static final Long DEFAULT_WAIT;
    static {
        DEFAULT_WAIT = System.getProperty("driver.defaultwait") != null ? 
                Long.parseLong(System.getProperty("driver.defaultwait")) : 10000L;
    }
    
    private WebDriver webDriver;
    
    public void logThreadInteruption(){
        LOG.info("Thread InterruptedException");
    }
    
    public boolean checkIfAvailable(AbstractComponent cmp, long waitTime) {
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean ret = false;
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            boolean objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                ret = true;
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        return ret;
    }
    
    /**
     *
     * @param cmp
     * @return
     */
    public boolean checkIfAvailable(AbstractComponent cmp){
        return checkIfAvailable(cmp, DEFAULT_WAIT);
    }
    
    public void voidAction(AbstractComponent cmp, String action, long waitTime){
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean objectFound = false;
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                webDriver.executeScript(String.format(VOID_ACTION, selector, cmp.getPosition(), action));
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        if(!objectFound) {
            throw new ComponentNotFoundException(cmp);
        }
    }
    
    public void voidAction(AbstractComponent cmp, String action){
        this.voidAction(cmp, action, DEFAULT_WAIT);
    }
    
    /**
     * @param cmp
     * @param event
     * @param commaSeparatedArgs
     * @param waitTime
     */
    public void fireEvent(AbstractComponent cmp, String event, String commaSeparatedArgs, long waitTime){
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean objectFound = false;
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                webDriver.executeScript(String.format(FIRE_EVENT, selector, cmp.getPosition(), event, commaSeparatedArgs));
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        if(!objectFound) {
            throw new ComponentNotFoundException(cmp);
        }
    }
    
    public void fireEvent(AbstractComponent cmp, String event, String commaSeparatedArgs){
        fireEvent(cmp, event, commaSeparatedArgs, DEFAULT_WAIT);
    }
    
    public boolean booleanAction(AbstractComponent cmp, String action, long waitTime){
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean objectFound = false, ret = false;
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                ret = (Boolean)webDriver.executeScript(String.format(RETURN_ACTION, selector, cmp.getPosition(), action));
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        if(!objectFound) {
            throw new ComponentNotFoundException(cmp);
        }
        return ret;
    }
    
    public boolean booleanAction(AbstractComponent cmp, String action){
        return this.booleanAction(cmp, action, DEFAULT_WAIT);
    }
    
    public String stringAction(AbstractComponent cmp, String action, long waitTime){
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean objectFound = false;
        String ret = "";
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                Object val = (Boolean)webDriver.executeScript(String.format(RETURN_ACTION, selector, cmp.getPosition(), action));
                ret = val != null ? val.toString() : "";
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        if(!objectFound) {
            throw new ComponentNotFoundException(cmp);
        }
        return ret;
    }
    
    public String stringAction(AbstractComponent cmp, String action){
        return this.stringAction(cmp, action, DEFAULT_WAIT);
    }
    
    public Long numberAction(AbstractComponent cmp, String action, long waitTime){
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean objectFound = false;
        Long ret = 0L;
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                ret = (Long)webDriver.executeScript(String.format(RETURN_ACTION, selector, cmp.getPosition(), action));
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        if(!objectFound) {
            throw new ComponentNotFoundException(cmp);
        }
        return ret;
    }
    
    public Long numberAction(AbstractComponent cmp, String action){
        return this.numberAction(cmp, action, DEFAULT_WAIT);
    }
    
    public Date dateAction(AbstractComponent cmp, String action, long waitTime){
        String selector = QueryUtils.generateQuerySelector(cmp);
        int execCount = 0;
        boolean objectFound = false;
        Date ret = null;
        while(execCount < 20) {
            execCount++;
            String script = String.format(GET_CMP, selector, cmp.getPosition());
            objectFound = (Boolean) webDriver.executeScript(script);
            if(objectFound) {
                ret = (Date)webDriver.executeScript(String.format(RETURN_ACTION, selector, cmp.getPosition(), action));
                break;
            }
            try{
                Thread.sleep(waitTime/20);
            } catch (InterruptedException ex) {
                logThreadInteruption();
            }
        }
        if(!objectFound) {
            throw new ComponentNotFoundException(cmp);
        }
        return ret;
    }
    
    public Date dateAction(AbstractComponent cmp, String action){
        return this.dateAction(cmp, action, DEFAULT_WAIT);
    }
    
    public void waitForMaskToClear(long waitTime){
        try{
            int execCount = 0;
            boolean maskLoading = true;
            while(execCount < 20 && maskLoading) {
                execCount++;
                maskLoading = (Boolean) webDriver.executeScript(MASK_LOADING);
                Thread.sleep(waitTime/20);
            }
        } catch (InterruptedException ex) {
            logThreadInteruption();
        }
    }
    
    public void waitForMaskToClear(){
        this.waitForMaskToClear(DEFAULT_WAIT);
    }
}
