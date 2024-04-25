package FirebaseTestJavaSwing;

public class UserN {
    private String username;
    private String email;
    private String phone;
    private String password;
    private String vehicle_no;
    private String vehicle_model;
    private String user_type;

    public UserN(String username, String email, String phone, String password, String vehicle_no, String vehicle_model, String user_type) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.vehicle_no = vehicle_no;
        this.vehicle_model = vehicle_model;
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getVehicle_no() { return vehicle_no; }
    public String getVehicle_model() { return vehicle_model; }
    public String getUser_type() { return user_type; }
}
