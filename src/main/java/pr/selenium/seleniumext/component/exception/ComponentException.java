/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.component.exception;

import pr.selenium.seleniumext.component.AbstractComponent;

/**
 *
 * @author Prashant
 */
public abstract class ComponentException extends RuntimeException {
    
    private AbstractComponent component;
    private String msgPrefix = "Eror in Component.";
    
    public ComponentException(){
        super();
    }
    
    public ComponentException(AbstractComponent component, String msgPrefix){
        super();
        this.component = component;
        this.msgPrefix = msgPrefix;
    }
    
    @Override
    public String getMessage(){
        if(this.component == null) return super.getMessage();
        return this.msgPrefix + " " + component.getDescription();
    }
}
