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
public class Label extends AbstractSelector {

    private boolean boxLabel;
    
    public Label(String value) {
        super(value);
    }
    
    public Label(String value, boolean boxLabel) {
        super(value);
        this.boxLabel = boxLabel;
    }

    public String getSelectorString() {
        if(this.boxLabel) return "[boxLabel=" + value + "]";
        return "[fieldLabel=" + value + "]";
    }
    
}
