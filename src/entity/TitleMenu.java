package entity;
// Generated Apr 13, 2018 11:49:02 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TitleMenu generated by hbm2java
 */
public class TitleMenu  implements java.io.Serializable {


     private String titleId;
     private String titleName;
     private Set menus = new HashSet(0);

    public TitleMenu() {
    }

	
    public TitleMenu(String titleId) {
        this.titleId = titleId;
    }
    public TitleMenu(String titleId, String titleName, Set menus) {
       this.titleId = titleId;
       this.titleName = titleName;
       this.menus = menus;
    }
   
    public String getTitleId() {
        return this.titleId;
    }
    
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
    public String getTitleName() {
        return this.titleName;
    }
    
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    public Set getMenus() {
        return this.menus;
    }
    
    public void setMenus(Set menus) {
        this.menus = menus;
    }




}


