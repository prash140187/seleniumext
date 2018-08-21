/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.query;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ASUS
 */
public class Title extends AbstractSelector {

    private boolean exact = true;
    
    public Title(String value) {
        super(value);
    }
    
    public Title(String value, boolean exact) {
        super(value);
        this.exact = exact;
    }

    public String getSelectorString() {
        if(StringUtils.isEmpty(value)) return "";
        if(this.exact) return "[title=" + value + "]";
        return "{title!=null}{title.search(\\'" + value + "\\') != -1}";
    }
}
