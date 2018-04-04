package entity;
// Generated Apr 3, 2018 4:37:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Room generated by hbm2java
 */
public class Room  implements java.io.Serializable {


     private String roomId;
     private Category category;
     private String roomName;
     private String location;
     private String price;
     private String description;
     private Boolean status;
     private Set bookingDetails = new HashSet(0);

    public Room() {
    }

	
    public Room(String roomId, Category category) {
        this.roomId = roomId;
        this.category = category;
    }
    public Room(String roomId, Category category, String roomName, String location, String price, String description, Boolean status, Set bookingDetails) {
       this.roomId = roomId;
       this.category = category;
       this.roomName = roomName;
       this.location = location;
       this.price = price;
       this.description = description;
       this.status = status;
       this.bookingDetails = bookingDetails;
    }
   
    public String getRoomId() {
        return this.roomId;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
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
    public Set getBookingDetails() {
        return this.bookingDetails;
    }
    
    public void setBookingDetails(Set bookingDetails) {
        this.bookingDetails = bookingDetails;
    }




}


