package BUS;

import DAO.RoomDAO;
import entity.Booking;
import java.util.List;

public class RoomBUS {
    
    private final RoomDAO roomDAO = new RoomDAO();
    
     public List<Booking> findListRoomID() {
         try {
             return roomDAO.findListRoomID();
         } catch (Exception e) {
             throw e;
         }
     }
     
      public String findRoomName(String roomID) {
          try {
              return roomDAO.findRoomName(roomID);
          } catch (Exception e) {
              throw  e;
          }
      }
}
