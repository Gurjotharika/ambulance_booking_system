
package FirebaseTestJavaSwing;

import java.util.prefs.Preferences;

public class App {

    public static void main(String[] args) {
        if (Utilitis.getLogin()) {
            if (Utilitis.getUserType().equals("admin")) {
                new AdminFrame().show();
            } else if (Utilitis.getUserType().equals("driver")) {
                new DriverHome().show();
            } else {
                new PatientHome().show();
            }
        } else {
            new LoginFrame().show();
        }


    }

}
