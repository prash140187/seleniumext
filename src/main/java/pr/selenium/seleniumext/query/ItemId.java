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
public class ItemId extends AbstractSelector {

    public ItemId(String value) {
        super(value);
    }

    public String getSelectorString() {
        return "[itemId=" + value + "]";
    }
    
}
