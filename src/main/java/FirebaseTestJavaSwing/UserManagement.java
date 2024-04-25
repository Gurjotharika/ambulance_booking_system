package FirebaseTestJavaSwing;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private static List<UserN> users = new ArrayList<>();

    public static boolean registerUser(String username, String email, String phone_no, String password) {
        if (findUser(username) != null) {
            return false; // Username already exists
        }

        users.add(new UserN(username, email, phone_no, password, "", "", "user"));
        return true;
    }

    public static boolean registerDriver(String username, String password, String email, String phone, String vehicle_no, String vehicle_model) {
        if (findUser(username) != null) {
            return false;
        }

        users.add(new UserN(username, email, phone, password, vehicle_no, vehicle_model, "driver"));
        return true;
    }

    public static UserN loginUser(String username, String password) {
        UserN user = findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Invalid credentials
    }

    private static UserN findUser(String username) {
        for (UserN user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
