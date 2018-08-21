/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.query;

/**
 *
 * @author ASUS
 */
public abstract class AbstractSelector implements Selector {
    
    protected String value;
    
    public AbstractSelector(String value) {
        super();
        this.value = value;
    }
}
