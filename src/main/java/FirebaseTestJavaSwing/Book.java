package FirebaseTestJavaSwing;

public class Book {
    private String bookingID;
    private String ambulanceId;
    private String name;
    private String location;
    private String phone;
    private String price;
    private String trx_id;

    public Book(String bookingID, String ambulanceId, String name, String location, String price, String trx_id) {
        this.bookingID = bookingID;
        this.ambulanceId = ambulanceId;
        this.name = name;
        this.location = location;
        this.price = price;
        this.trx_id = trx_id;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getAmbulanceId() {
        return ambulanceId;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }

    public String getTrx_id() {
        return trx_id;
    }
}
