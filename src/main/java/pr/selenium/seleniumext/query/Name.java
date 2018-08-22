/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.query;

/**
 *
 * @author Prashant
 */
public class Name extends AbstractSelector {

    public Name(String value) {
        super(value);
    }

    public String getSelectorString() {
        return "[name=" + value + "]";
    }
    
}
