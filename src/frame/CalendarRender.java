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
        
        // 선택된 셀 강조
        if (row == selectedRow && column == selectedCol) {
            cell.setBackground(Color.YELLOW); // 강조할 배경색
        } else {
            cell.setBackground(Color.WHITE); // 기본 배경색
        }

        return cell;
    }
}