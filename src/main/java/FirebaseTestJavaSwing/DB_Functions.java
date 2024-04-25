package FirebaseTestJavaSwing;

import static java.awt.image.ImageObserver.ERROR;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DB_Functions {

    private Connection conn;

    public DB_Functions() {
        conn = DBConnect.connect();
    }

    public boolean checkLogin(String email, String password) {
        try {
            String sql = "SELECT * FROM `users` WHERE `email` = '" + email + "' AND `password_hash` = '" + password + "' LIMIT 1;";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            ResultSet result = smt.executeQuery(sql);

            System.out.println("Login Result " + result);

            if (!result.next()) {
                return false;
            } else {
                System.out.println("User Type " + result.getString("user_type"));
                Utilitis.setLoginUserId(Integer.parseInt(result.getString("id")));
                Utilitis.setUserType(result.getString("user_type"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean registerUser(String name, String email, String password, String phone, String userType) {
        try {
            String sql = "INSERT INTO `users`(`email`, `password_hash`, `name`, `user_type` , `phone_number`) "
                    + "VALUES ('" + email + "','" + password + "','" + name + "', '" + userType + "' , '" + phone + "')";

            Statement smt = conn.createStatement();

            int result = smt.executeUpdate(sql);

            System.out.println("Register User Result " + result);

            if (result == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Register User Error: " + e.getMessage());
            return false;
        }
    }

    public DefaultTableModel getAmbulanceTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        try {
            String sql = "SELECT id, name, phone, added_user_id, create_at FROM ambulances";
            
            Statement smt = conn.createStatement();
            
            ResultSet result = smt.executeQuery(sql);

            // Get column names from ResultSetMetaData
            int columnCount = result.getMetaData().getColumnCount();
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(result.getMetaData().getColumnName(i));
            }
            model.setColumnIdentifiers(columnNames);

            // Add data rows
            while (result.next()) {
                Vector<Object> rowData = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(result.getObject(i));
                }
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            System.out.println("Ambulance Fetch Error: " + e.getMessage());
        } 
        
        return model;
    }

    public boolean bookingAmbulance(int ambulanceId, String name, String phone, String location, int price, String trxId) {
        try {
            String sql = "INSERT INTO " +
                    "`bookings`(`ambulance_id`, `name`, `phone`,`location`, `price`, `trx_id`) " +
                    "VALUES ('" + ambulanceId + "','" + name + "','" + phone + "','" + location + "','" + price + "','" + trxId + "')";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            int result = smt.executeUpdate(sql);

            System.out.println("Booking Ambulance Result " + result);

            if (result == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Booking Ambulance Error: " + e.getMessage());
            return false;
        }
    }

   public DefaultTableModel getBookingAmbulanceTableModel() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID");
    model.addColumn("Ambulance ID");
    model.addColumn("Name");
    model.addColumn("Phone");

    try {
        String sql = "SELECT * FROM `bookings`";
        Statement smt = conn.createStatement();
        ResultSet result = smt.executeQuery(sql);

        while (result.next()) {
            int id = Integer.parseInt(result.getString("id"));
            int ambulanceId = Integer.parseInt(result.getString("ambulance_id"));
            String name = result.getString("name");
            String phone = result.getString("phone");

            model.addRow(new Object[]{id, ambulanceId, name, phone});
        }
    } catch (Exception e) {
        System.out.println("Error fetching bookings: " + e.getMessage());
        e.printStackTrace();
        // return an empty model in case of error
        return model;
    }

    return model;
}

    public int getTodayTotalPrice() {
        try {
            int totalPrice = 0;

            String sql = "SELECT SUM(`price`) as totalPrice FROM `bookings` WHERE DATE(`created_at`) = CURDATE();";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            ResultSet result = smt.executeQuery(sql);

            System.out.println("Total Price Fetach Result " + result);

            while (result.next()) {
                System.out.println("Total Price  Fetach Value: " + result.getString("totalPrice"));
                totalPrice = Integer.parseInt(result.getString("totalPrice"));
            }
            return totalPrice;
        } catch (Exception e) {
            System.out.println("Total Price Error: " + e.getMessage());
            return 0;
        }
    }

    public int getTodayTotalBooking() {
        try {
            int totalBooking = 0;

            String sql = "SELECT COUNT(`id`) as totalBooking FROM `bookings` WHERE DATE(`created_at`) = CURDATE();";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            ResultSet result = smt.executeQuery(sql);

            System.out.println("Total Booking Fetach Result " + result);

            while (result.next()) {
                System.out.println("Total Booking  Fetach Value: " + result.getString("totalBooking"));
                totalBooking = Integer.parseInt(result.getString("totalBooking"));
            }
            return totalBooking;
        } catch (Exception e) {
            System.out.println("Total Booking Error: " + e.getMessage());
            return 0;
        }
    }

    public boolean addNewAmbulance(String ambulanceName, String ambulancePhone, String vehicle_model, String registration_number) {
        try {
            int userId = Utilitis.getLoginUserId();
         Random rand = new Random();

            String sql = "INSERT INTO `ambulances`(`id`,`name`, `phone`, `registration_number`, `vehicle_model`, `added_user_id`) "
                    + "VALUES ('" + rand.nextInt(1000) + "', '" + ambulanceName + "', '" + ambulancePhone + "', '" + registration_number + "', '" + vehicle_model + "', '" + userId + "')";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            int result = smt.executeUpdate(sql);

            System.out.println("Ambulance Add Result " + result);

            if (result == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ambulance Add Error: " + e.getMessage());
            return false;
        }
    }

    public JList<Ambulance> getAdminAmbulanceList(JLabel view, JLabel secondView) {
        //ArrayList<Ambulance> ambulances = new ArrayList<Ambulance>();

        DefaultListModel<Ambulance> model = new DefaultListModel<>();

        try {

            String sql = "SELECT * FROM `ambulances`";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            ResultSet result = smt.executeQuery(sql);

            System.out.println("Ambulance Fetach Result " + result);

            while (result.next()) {
                System.out.println("Ambulance Fetach Value: " + result.getString("name"));

                model.addElement(new Ambulance(
                        Integer.parseInt(result.getString("id")),
                        result.getString("name"),
                        result.getString("phone"),
                        Integer.parseInt(result.getString("added_user_id")),
                        result.getString("create_at")));
            }

        } catch (Exception e) {
            System.out.println("Ambulance Fetach Error: " + e.getMessage());
            return null;
        }

        JList<Ambulance> list = new JList<Ambulance>(model);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println("First index: " + listSelectionEvent.getFirstIndex());
                System.out.println(", Last index: " + listSelectionEvent.getLastIndex());
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.println(" Selections: ");
                        }
                        System.out.println(selections[i] + "/" + selectionValues[i] + " ");
                        Common.selectedAdminAmbulanc = (Ambulance) selectionValues[i];
                        view.setText("Name: " + Common.selectedAdminAmbulanc.getName());
                        secondView.setText("Conatct: " + Common.selectedAdminAmbulanc.getPhone());
                    }
                }
            }
        };

        list.addListSelectionListener(listSelectionListener);

        return list;
    }

    public boolean deleteAmbulance(int ambulanceId) {
        try {
            String sql = "DELETE FROM `ambulances` WHERE `id` = '" + ambulanceId + "';";

            System.out.println("SQL " + sql);

            Statement smt = conn.createStatement();

            boolean result = smt.execute(sql);

            System.out.println("Delete Ambulance Result " + result);

            return result;
        } catch (Exception e) {
            System.out.println("Delete Ambulance Error: " + e.getMessage());
            return false;
        }
    }
}
