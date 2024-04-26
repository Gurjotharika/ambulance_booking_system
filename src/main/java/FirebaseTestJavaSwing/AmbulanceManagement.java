package FirebaseTestJavaSwing;

import java.util.ArrayList;
import java.util.List;

public class AmbulanceManagement {

    private static List<Ambulance> ambulanceList = new ArrayList<>();

    public static boolean createAmbulance(Ambulance ambulance) {
        ambulanceList.add(ambulance);
        return true;
    }

    public static boolean deleteAmbulance(Ambulance ambulance) {
        ambulanceList.remove(ambulance);
        return true;
    }

    public static boolean updateAmbulance(Ambulance ambulance) {
        ambulanceList.set(ambulanceList.indexOf(ambulance), ambulance);
        return true;
    }
}
