package FirebaseTestJavaSwing;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class BookingTableModel implements TableModel {

    private List<Book> bookings;
    private String[] columnNames = {"Booking ID", "Ambulance ID", "Name", "Location", "Phone", "Price", "Transaction ID"};

    public BookingTableModel(List<Book> bookings) {
        this.bookings = bookings;
    }

    @Override
    public int getRowCount() {
        return bookings.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Set to true if you want editable cells
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book booking = bookings.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return booking.getBookingID();
            case 1:
                return booking.getAmbulanceId();
            case 2:
                return booking.getName();
            case 3:
                return booking.getLocation();
            case 4:
                return booking.getPhone();
            case 5:
                return booking.getPrice();
            case 6:
                return booking.getTrx_id();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
