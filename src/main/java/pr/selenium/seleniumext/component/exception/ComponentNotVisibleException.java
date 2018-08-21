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
public class ComponentNotVisibleException extends ComponentException{
    
    public ComponentNotVisibleException(AbstractComponent component){
        super(component, "Component not visible.");
    }
}
