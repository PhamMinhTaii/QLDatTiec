package entity;
// Generated Apr 10, 2018 1:15:47 PM by Hibernate Tools 4.3.1



/**
 * BookingDetail generated by hbm2java
 */
public class BookingDetail  implements java.io.Serializable {


     private String bookingId;
     private Booking booking;
     private Concept concept;
     private Menu menu;
     private Service service;
     private String price;
     private String discount;

    public BookingDetail() {
    }

	
    public BookingDetail(Booking booking) {
        this.booking = booking;
    }
    public BookingDetail(Booking booking, Concept concept, Menu menu, Service service, String price, String discount) {
       this.booking = booking;
       this.concept = concept;
       this.menu = menu;
       this.service = service;
       this.price = price;
       this.discount = discount;
    }
   
    public String getBookingId() {
        return this.bookingId;
    }
    
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public Booking getBooking() {
        return this.booking;
    }
    
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public Concept getConcept() {
        return this.concept;
    }
    
    public void setConcept(Concept concept) {
        this.concept = concept;
    }
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(String discount) {
        this.discount = discount;
    }




}


