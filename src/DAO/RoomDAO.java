package DAO;

import entity.Booking;
import entity.BookingDetail;
import entity.Room;
import entity.Menu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class RoomDAO extends Connettion {
    private Date dateNow;
    private String keyWord;

    
    public List<Booking> findListRoomID(String key) throws ParseException {
        connettion();

        try {
            Criteria criteria = session.createCriteria(Booking.class);
            if (key.matches("\\d{4}-\\d{2}-\\d{2}")) {
                if (!key.isEmpty()) {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    String keyWord = key;
                    dateNow = f.parse(keyWord);
                    Criterion c = Restrictions.eq("bookingDate", dateNow);
                    criteria.add(c);
                }
            }
            else{
                
            }

            return criteria.list();
        } catch (HibernateException e) {
            trans.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public String findRoomName(String roomID) {
        connettion();
        Criteria criteria = session.createCriteria(Room.class);
        try {
            Criterion id = Restrictions.eq("roomId", roomID);
            criteria.add(id);
            return ((Room) criteria.list().stream().findFirst().get()).getRoomName();
        } catch (HibernateException e) {
            trans.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<BookingDetail> findAllListBookingDetail() {
        connettion();
        Criteria criteria = session.createCriteria(BookingDetail.class);
        try {
            List<BookingDetail> list = criteria.list();
            return list;
        } catch (HibernateException e) {
            trans.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public double priceRoom(String roomId) {
        try {
            connettion();
            Criteria criteria = session.createCriteria(Room.class);
            Criterion id = Restrictions.eq("roomId", roomId);
            criteria.add(id);
            String price = ((Room) criteria.list().stream().findFirst().get()).getPrice();
            return Double.parseDouble(price);
        } catch (NumberFormatException | HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public double priceMenu(String menuId) {
        try {
            connettion();
            Criteria criteria = session.createCriteria(Menu.class);
            Criterion id = Restrictions.eq("menuId", menuId);
            criteria.add(id);
            String price = ((Menu) criteria.list().stream().findFirst().get()).getPrice();
            return Double.parseDouble(price);
        } catch (NumberFormatException | HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public double takeQuantity(String roomId) {
        try {
            connettion();
            Criteria criteria = session.createCriteria(Room.class);
            Criterion id = Restrictions.eq("roomId", roomId);
            criteria.add(id);
            String quan = ((Room) criteria.list().stream().findFirst().get()).getQuantityTable();
            return Double.parseDouble(quan);
        } catch (NumberFormatException | HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

}
