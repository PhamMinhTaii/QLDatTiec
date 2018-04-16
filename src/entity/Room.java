package entity;
// Generated Apr 14, 2018 3:16:17 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Room generated by hbm2java
 */
public class Room  implements java.io.Serializable {


     private String roomId;
     private String roomName;
     private String location;
     private String price;
     private String description;
     private Boolean status;
     private String quantityTable;
     private Set bookings = new HashSet(0);

    public Room() {
    }

	
    public Room(String roomId) {
        this.roomId = roomId;
    }
    public Room(String roomId, String roomName, String location, String price, String description, Boolean status, String quantityTable, Set bookings) {
       this.roomId = roomId;
       this.roomName = roomName;
       this.location = location;
       this.price = price;
       this.description = description;
       this.status = status;
       this.quantityTable = quantityTable;
       this.bookings = bookings;
    }
   
    public String getRoomId() {
        return this.roomId;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomName() {
        return this.roomName;
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getQuantityTable() {
        return this.quantityTable;
    }
    
    public void setQuantityTable(String quantityTable) {
        this.quantityTable = quantityTable;
    }
    public Set getBookings() {
        return this.bookings;
    }
    
    public void setBookings(Set bookings) {
        this.bookings = bookings;
    }




}


