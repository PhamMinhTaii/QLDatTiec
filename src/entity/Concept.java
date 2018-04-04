package entity;
// Generated Apr 3, 2018 4:37:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Concept generated by hbm2java
 */
public class Concept  implements java.io.Serializable {


     private String conceptId;
     private String conceptName;
     private String price;
     private String description;
     private Boolean status;
     private String image;
     private Set bookingDetails = new HashSet(0);

    public Concept() {
    }

	
    public Concept(String conceptId) {
        this.conceptId = conceptId;
    }
    public Concept(String conceptId, String conceptName, String price, String description, Boolean status, String image, Set bookingDetails) {
       this.conceptId = conceptId;
       this.conceptName = conceptName;
       this.price = price;
       this.description = description;
       this.status = status;
       this.image = image;
       this.bookingDetails = bookingDetails;
    }
   
    public String getConceptId() {
        return this.conceptId;
    }
    
    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }
    public String getConceptName() {
        return this.conceptName;
    }
    
    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
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
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    public Set getBookingDetails() {
        return this.bookingDetails;
    }
    
    public void setBookingDetails(Set bookingDetails) {
        this.bookingDetails = bookingDetails;
    }




}

