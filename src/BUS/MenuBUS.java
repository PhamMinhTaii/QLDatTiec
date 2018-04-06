/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MenuDAO;
import entity.*;
import java.util.List;

/**
 *
 * @author Minh Taii
 */
public class MenuBUS {

    private final MenuDAO menuDAO;

    public MenuBUS() {
        menuDAO = new MenuDAO();
    }

    public List<Menu> loadMenu(String titleMenu) {
        return menuDAO.loadMenu(titleMenu);
    }
     // List load All
     public List<Menu> loadMenuAll() {
        return menuDAO.loadAllMenu();
    }

    // title menu
    public List<TitleMenu> loadTitleMenu() {
        return menuDAO.loadTitleMenu();
    }

    // Luu checkBox chon mon
    public void update(Menu mn) {
        System.out.println("-------vao MenuBUS -------");
        System.out.println(mn.getMenuId() + " " + mn.getMenuName() + " " + mn.getTitleMenu());
        menuDAO.update(mn);
    }
    

}
