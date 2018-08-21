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
public class Text extends AbstractSelector {

    private boolean exact = true;
    private boolean checkVisibility;
    
    public Text(String value) {
        super(value);
    }
    
    public Text(String value, boolean exact) {
        super(value);
        this.exact = exact;
    }
    
    public Text(String value, boolean exact, boolean checkVisibility) {
        super(value);
        this.exact = exact;
        this.checkVisibility = checkVisibility;
    }

    public String getSelectorString() {
        String selectorString = exact ? "[text=" + value + "]" 
                : "{text!=null}{text.search(\\'" + value + "\\') != -1}";
        return checkVisibility ? (selectorString + "{isVisible()==true}") : selectorString;
    }
}
