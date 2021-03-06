package entity;
// Generated Apr 13, 2018 11:49:02 AM by Hibernate Tools 4.3.1



/**
 * BookingDetail generated by hbm2java
 */
public class BookingDetail  implements java.io.Serializable {


     private BookingDetailId id;
     private Booking booking;
     private Menu menu;
     private String discount;

    public BookingDetail() {
    }

	
    public BookingDetail(BookingDetailId id, Booking booking, Menu menu) {
        this.id = id;
        this.booking = booking;
        this.menu = menu;
    }
    public BookingDetail(BookingDetailId id, Booking booking, Menu menu, String discount) {
       this.id = id;
       this.booking = booking;
       this.menu = menu;
       this.discount = discount;
    }
    public BookingDetail( Booking booking, Menu menu, String discount) {
       
       this.booking = booking;
       this.menu = menu;
       this.discount = discount;
    }
   
    public BookingDetailId getId() {
        return this.id;
    }
    
    public void setId(BookingDetailId id) {
        this.id = id;
    }
    public Booking getBooking() {
        return this.booking;
    }
    
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public String getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(String discount) {
        this.discount = discount;
    }




}


