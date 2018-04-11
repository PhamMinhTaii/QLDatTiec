package DAO;

import entity.Booking;
import entity.Room;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class RoomDAO extends Connettion {

    public List<Booking> findListRoomID() {
        connettion();

        try {
            Criteria criteria = session.createCriteria(Booking.class);
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
}
