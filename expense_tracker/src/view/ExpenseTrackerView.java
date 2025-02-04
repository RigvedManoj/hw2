package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton removeTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  // Create Filter fields
  private JTextField filterField;
  private JComboBox<String> filterChoice;
  private JButton filterBtn;


  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    removeTransactionBtn = new JButton("Remove Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create Filter Components
    JLabel filterLabel = new JLabel("Filter: ");
    filterField = new JTextField(10);

    String[] choices = { "Amount","Category"};
    filterChoice = new JComboBox<>(choices);

    filterBtn = new JButton("Filter");

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);

    // Add Filter Components
    inputPanel.add(filterLabel);
    inputPanel.add(filterChoice);
    inputPanel.add(filterField);

    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(removeTransactionBtn);

    // Add Filter Button
    buttonPanel.add(filterBtn);

    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);

        List<Integer> emptyList = new ArrayList<>();
        transactionsTable.setDefaultRenderer(Object.class, new Highlighter(emptyList));
      // Fire table update
        transactionsTable.updateUI();

    }
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public JButton getRemoveTransactionBtn() { return removeTransactionBtn;}
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public JButton getFilterBtn() {
    return filterBtn;
  }

  public String getFilterField() {
    return filterField.getText();
  }

  public String getFilterChoice() {
    return Objects.requireNonNull(filterChoice.getSelectedItem()).toString();
  }

  // To make GUI change to highlight filtered rows
  public void highlightTable(List<Integer> filteredTransactionRows) {
    transactionsTable.setDefaultRenderer(Object.class, new Highlighter(filteredTransactionRows));
    transactionsTable.updateUI();
  }
}
