/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.query;

import org.apache.commons.lang.StringUtils;
import pr.selenium.seleniumext.component.AbstractComponent;

/**
 *
 * @author Prashant
 */
public class QueryUtils {
    
    public static String generateSimpleQuerySelector(AbstractComponent cmp){
        return cmp.getXType() + (StringUtils.isNotEmpty(cmp.getSelector()) ? cmp.getSelector() : "");
    }
    
    public static String generateQuerySelector(AbstractComponent cmp){
        String query = generateSimpleQuerySelector(cmp);
        AbstractComponent parent = cmp.getParent();
        while(parent != null){
            query = generateSimpleQuerySelector(parent) + " " + query;
            parent = parent.getParent();
        }
        return query;
    }
    
}
