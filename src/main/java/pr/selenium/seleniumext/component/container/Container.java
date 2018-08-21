/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.component.container;

import pr.selenium.seleniumext.component.AbstractComponent;
import pr.selenium.seleniumext.query.Selector;

/**
 *
 * @author Prashant
 */
public abstract class Container extends AbstractComponent {
    
    public Container(Selector selector) {
        super(selector);
    }
    
}
