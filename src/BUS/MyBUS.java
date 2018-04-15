package BUS;

import DAO.Connettion;
import Model.MyClass;
import entity.Booking;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MyBUS extends Connettion {

    private final RoomBUS roomBUS = new RoomBUS();
    private final UserBUS userBUS = new UserBUS();
    private final CustomerBUS customerBUS = new CustomerBUS();
    private final ConceptBUS conceptBUS = new ConceptBUS();
    private double countTotal;

    public List<MyClass> findListMyClass(String key) throws Exception {

        try {
            List<Booking> list = roomBUS.findListRoomID(key);
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
                double quantity = roomBUS.takeQuantity(idRoom);
                countPriceMenu *= quantity;
                countTotal = priceConcept + priceRoom + countPriceMenu;
                String countTotalString = String.format("%0,3.0f", countTotal);
                double vat = countTotal * 0.1;
                String vatString = String.format("%0,3.0f", vat);
                String money = String.format("%0,3.0f", countTotal + vat);
                String userName = userBUS.findUserName(idUser);
                String roomName = roomBUS.findRoomName(idRoom);
                String custommerName = customerBUS.findUserName(idCus);
                MyClass my = new MyClass(userName, roomName, custommerName, e.getBookingDate(), countTotalString, vatString, money);
                mylist.add(my);
            });
            return mylist;
        } catch (ParseException e) {
            throw e;
        }
    }
}
