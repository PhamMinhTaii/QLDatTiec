package BUS;

import DAO.Connettion;
import Model.MyClass;
import entity.Booking;
import java.util.ArrayList;
import java.util.List;

public class MyBUS extends Connettion {

    private final RoomBUS roomBUS = new RoomBUS();
    private final UserBUS userBUS = new UserBUS();
    private final CustomerBUS customerBUS = new CustomerBUS();
    private final ConceptBUS conceptBUS = new ConceptBUS();
    private double countTotal;

    public List<MyClass> findListMyClass() {

        try {
            List<Booking> list = roomBUS.findListRoomID();
            List<MyClass> mylist = new ArrayList<>();

            list.forEach((e) -> {
                countTotal = 0;
                String idUser = e.getUser().getId();
                String idRoom = e.getRoom().getRoomId();
                String idCus = e.getCustomer().getCustomterId();
                String idConcept = e.getConcept().getConceptId();
                ///----- TÍnh tiền -- 
                double priceConcept = conceptBUS.priceConcept(idConcept);
                double priceRoom = roomBUS.priceRoom(idRoom);
                double countPriceMenu = roomBUS.countPriceMenu(e.getBookingId());
                 
                countTotal = priceConcept + priceRoom + countPriceMenu;
                String money = String.format("%0,3.0f", countTotal);
                String userName = userBUS.findUserName(idUser);
                String roomName = roomBUS.findRoomName(idRoom);
                String custommerName = customerBUS.findUserName(idCus);
                MyClass my = new MyClass(userName, roomName, custommerName, e.getBookingDate(), money);
                mylist.add(my);
            });
            return mylist;
        } catch (Exception e) {
            throw e;
        }
    }
}
