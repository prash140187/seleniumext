/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.component;

import pr.selenium.seleniumext.component.container.Container;
import pr.selenium.seleniumext.query.Selector;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Prashant
 */
public abstract class AbstractComponent {
    
    public abstract String getXType();
    
    protected int position;
    protected String selector;
    protected String description;
    protected Container parent;
    
    public AbstractComponent(String selector){
        this.selector = selector;
    }
    
    public AbstractComponent(Selector selector){
        this.selector = selector.getSelectorString();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getDescription() {
        String desc = StringUtils.isEmpty(description) ? this.selector : this.description;
        desc = StringUtils.isEmpty(desc) ? this.getClass().getSimpleName() : desc;
        return desc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }
    
    /** Selenium Util Methods
     * @param <T>
     * @param t
     * @return  t*/
    public <T extends AbstractComponent> T findParentByType(T t) {
        return t;
    }
}
