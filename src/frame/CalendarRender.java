package frame;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CalendarRender extends DefaultTableCellRenderer {
    private int selectedRow = -1;
    private int selectedCol = -1;

    public void setSelectedCell(int row, int col) {
        this.selectedRow = row;
        this.selectedCol = col;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // ���õ� �� ����
        if (row == selectedRow && column == selectedCol) {
            cell.setBackground(Color.YELLOW); // ������ ����
        } else {
            cell.setBackground(Color.WHITE); // �⺻ ����
        }

        return cell;
    }
}