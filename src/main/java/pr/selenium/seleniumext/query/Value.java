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
public class Value extends AbstractSelector {

    private boolean exact = true;
    
    public Value(String value) {
        super(value);
    }
    
    public Value(String value, boolean exact) {
        super(value);
        this.exact = exact;
    }

    public String getSelectorString() {
        if(this.exact) return "[value=" + value + "]";
        return "{getValue()!=null}{getValue().search(\\'" + value + "\\') != -1}";
    }
}
