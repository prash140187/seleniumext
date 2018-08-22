/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.selenium.seleniumext.execution;

/**
 *
 * @author Prashant
 */
public interface Scripts {
    
    public static final String GET_CMP_TMPL = "Ext.ComponentQuery.query('%s')[%d]";
    public static final String GET_CMP = "return " + GET_CMP_TMPL + " != null;";
    public static final String VOID_ACTION = GET_CMP_TMPL + ".%s";
    public static final String RETURN_ACTION = "return " + VOID_ACTION;
    
    public static final String GET_COMBO_STORE = "var combo = " + GET_CMP_TMPL + "; var store = combo.getStore();";
    public static final String GET_VALUE_FOR_REC_IN_COMBO = GET_COMBO_STORE + " var rec = store.findRecord(combo.displayField, %s, 0, false, false, false); var val = rec != null ? rec.get(combo.valueField) : null;";
    public static final String GET_VALUE_FOR_REC_IN_COMBO_LN = GET_COMBO_STORE + " var rec = store.findRecord(combo.displayField, %d, 0, false, false, false); var val = rec != null ? rec.get(combo.valueField) : null;";
    public static final String SET_COMBO_RAW_VALUE = GET_VALUE_FOR_REC_IN_COMBO + " if(val != null){combo.setValue(val)}else{combo.setRawValue('%s')}"; 
    public static final String SET_COMBO_RAW_VALUE_LN = GET_VALUE_FOR_REC_IN_COMBO_LN + " if(val != null){combo.setValue(val)}else{combo.setRawValue('%s')}";
    
    public static final String FIRE_EVENT = "var cmp = " + GET_CMP_TMPL + "; cmp.fireEvent('%s', cmp %s);";
    public static final String MASK_LOADING = "return Ext.ComponentQuery.query('loadmask{isVisible() == true}').length > 0";
    
    public static final String MSG_BOX = "Ext.ComponentQuery.query('messagebox')[0]";
    public static final String MSG_BOX_VISIBLE = "return " + MSG_BOX + ".isVisible();";
    public static final String MSG_BOX_CLOSE = MSG_BOX + ".close();";
    public static final String MSG_BOX_TITLE = "return " + MSG_BOX + ".title";
    public static final String MSG_BOX_TEXT = "Ext.ComponentQuery.query('messagebox displayfield')[0].getEl().getHTML()";
    public static final String MSG_BOX_TEXT_SEARCH = "return "  + MSG_BOX_TEXT + ".search('%s') != -1;";
    public static final String MSG_BOX_ACTION = "var msg = " + MSG_BOX + "; var btn = Ext.ComponentQuery.query('button[text=%s]', msg)[0]; btn.btnEl.dom.click()";
    
    public static final String GET_DATA_INDX = "Ext.ComponentQuery.query('%s')[0].dataIndex";
    //public static final String GET_DATA_INDX = 
    public static final String GET_VALUE_FROM_GRID = "var prop = " + GET_DATA_INDX + 
            " return String(Ext.ComponentQuery.query('%s')[%d].getStore().getAt(%d-1).get(prop));";
    public static final String GRID_EDIT_PLUGIN = "var plugin = null; var plgns = " + GET_CMP_TMPL + ".plugins; " +
            "for(var i=0; i<plgns.length; i++){if(plgns[i].startEdit){plugin = plgns[i];break;}} ";
    public static final String GRID_EDIT = GRID_EDIT_PLUGIN + 
            "if(plugin){plugin.startEdit(%d-1, %d-1);}";
    public static final String GRID_EDIT_STOP = GRID_EDIT_PLUGIN + 
            "if(plugin){plugin.completeEdit();}";
    public static final String GRID_EDITOR = 
            "var editor = Ext.ComponentQuery.query('%s gridcolumn{getVisibleIndex() == (%d-1)}')[0].getEditor();";
    public static final String GRID_EDIT_VALUE = GRID_EDITOR + " editor.setValue('%s');";
    public static final String GRID_EDIT_VALUE_LN = GRID_EDITOR + " editor.setValue(%d);";
    public static final String GRID_RANGE = "var prop = " + GET_DATA_INDX + 
            "var range = Ext.ComponentQuery.query('%s')[%d].getStore().getRange();";
    public static final String GRID_CHECK_VALUE = GRID_RANGE + "if(range.length == 0){return false;}" + 
            "for(var i=0; i<range.length; i++){if(range[i].get(prop) != '%s'){return false;}} return true;";
    public static final String GRID_CHECK_VALUE_BOOLEAN = GRID_RANGE + "if(range.length == 0){return false;}" + 
            "for(var i=0; i<range.length; i++){if(range[i].get(prop) != %s){return false;}} return true;"; 
    
}
