package Model;

import java.util.Date;

public class MyClass {

    /**
     * @return the vat
     */
    public String getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(String vat) {
        this.vat = vat;
    }

    /**
     * @return the totalMoney
     */
    public String getTotalMoney() {
        return totalMoney;
    }

    /**
     * @param totalMoney the totalMoney to set
     */
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    private String userName;
    private String roomName;
    private String custommerName;
    private Date date;
    private String money;
    private String vat;
    private String totalMoney;

    public MyClass() {

    }

    public MyClass(String userName, String roomName, String custommerName, Date date, String count, String vat, String total) {
        this.userName = userName;
        this.roomName = roomName;
        this.custommerName = custommerName;
        this.date = date;
        this.money = count;
        this.vat = vat;
        this.totalMoney = total;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the custommerName
     */
    public String getCustommerName() {
        return custommerName;
    }

    /**
     * @param custommerName the custommerName to set
     */
    public void setCustommerName(String custommerName) {
        this.custommerName = custommerName;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the price
     */
   
}
