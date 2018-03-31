/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MenuDAO;
import entity.Menu;
import java.util.List;

/**
 *
 * @author Minh Taii
 */
public class MenuBUS {
    MenuDAO menuDAO=new MenuDAO();
    public List<Menu> loadMenu(){
        return menuDAO.loadMenu();
    }
    
}
