package BUS;

import DAO.RoomDAO;
import entity.Booking;
import entity.BookingDetail;
import java.util.List;

public class RoomBUS {

    private final RoomDAO roomDAO = new RoomDAO();
    private final ConceptBUS conceptBUS = new ConceptBUS();
    double count;

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
            throw e;
        }
    }

    public List<BookingDetail> findAllListBookingDetail() {
        try {
            return roomDAO.findAllListBookingDetail();
        } catch (Exception e) {
            throw e;
        }
    }

    public double countPriceMenu(String bookingId) {
        try {
            List<BookingDetail> listDetail = roomDAO.findAllListBookingDetail();
            count = 0;
            listDetail.forEach(e -> {
                if (e.getBooking().getBookingId().equals(bookingId)) {
                    count += roomDAO.priceMenu(e.getMenu().getMenuId());
                }
            });
            return count;
        } catch (Exception e) {
            throw e;
        }
    }

    public double priceRoom(String roomId) {
        try {
            return roomDAO.priceRoom(roomId);
        } catch (Exception e) {
            throw e;
        }
    }

}
