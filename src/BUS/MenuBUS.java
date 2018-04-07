/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import CommonConstance.ReplaceString;
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
       
           try {
                menuDAO.update(mn);
//            if (ktraMenu(mn.getMenuName(), mn.getPrice(), mn.getDescription()) == 1) {
//                menuDAO.update(mn);
//               //return 1;
//            }
            //return ktraMenu(mn.getMenuName(), mn.getPrice(), mn.getDescription());
            //menuDAO.update(mn);

        } catch (Exception ex) {
            throw ex;
        }
    }
    
    // them mon an
    public int addMenu(Menu mn) {
        try {
            if (ktraMenu(mn.getMenuName(), mn.getPrice(), mn.getDescription()) == 1) {
                menuDAO.addMenu(mn);
                return 1;
            }
            return ktraMenu(mn.getMenuName(), mn.getPrice(), mn.getDescription());
        } catch (Exception ex) {
            throw ex;
        }
    }
    // kiem tra menu
    public int ktraMenu(String menuName, String price, String description) {
        menuName = ReplaceString.UserName(menuName);// chuan hoa lai ten                
//        if (!menuName.matches("[\\p{L}\\s]+")) {
//            return -1;
//        }
//        if (!price.matches("\\d{3,9}")) {
//            return -2;
//        }
//        if (!description.matches("[\\p{L}\\s\\d.,?!]{0,}")) {
//            return -3;
//        }
        return 1;
    }
    // lay titlemenu
    public TitleMenu getTitleMenu(String id){
        return menuDAO.getTitleMenu(id);
    }
    

}
