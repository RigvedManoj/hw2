package view;

import model.Transaction;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Highlighter extends DefaultTableCellRenderer {
    private List<Integer> rows;  // The rows you want to set a custom color for.

    public Highlighter(List<Integer> rows) {
        this.rows = new ArrayList<>(rows);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(rows.contains(row)){
            component.setBackground(new Color(173,255,168));
        }
        else{
            component.setBackground(table.getBackground());
        }
        return component;
    }
}
