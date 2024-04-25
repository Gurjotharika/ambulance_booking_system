package FirebaseTestJavaSwing;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class BookingManagement {
    private static List<Book> book = new ArrayList<>();

    public static boolean createBook(Book _book) {
        book.add(_book);
        return true;
    }
}

