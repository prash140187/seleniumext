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
public class ComponentNotFoundException extends ComponentException {
    
    public ComponentNotFoundException(AbstractComponent component){
        super(component, "Unable to find Component.");
    }
    
}
