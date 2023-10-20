package controller;

import model.*;
import view.ExpenseTrackerView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }

  // To filter transactions and highlight them
  public boolean applyFilter(String FilterChoice, String FilterValue){
    switch(FilterChoice){
      case "Category":{
        //Check if category value entered is valid
        if (!InputValidation.isValidCategory(FilterValue)) {
          return false;
        }
        TransactionFilter categoryFilter = new CategoryFilter();
        //Call filter function to get rows to be highlighted
        List<Integer> filteredTransactionRows = categoryFilter.filter(model.getTransactions(),FilterValue);
        //Call view to highlight the table
        view.highlightTable(filteredTransactionRows);
        return true;
      }
      case "Amount":{
        if (!InputValidation.isValidAmount(Double.parseDouble(FilterValue))) {
          return false;
        }
        TransactionFilter amountFilter = new AmountFilter();
        //Call filter function to get rows to be highlighted
        List<Integer> filteredTransactionRows = amountFilter.filter(model.getTransactions(),FilterValue);
        //Call view to highlight the table
        view.highlightTable(filteredTransactionRows);
        return true;
      }
      default:{
        break;
      }

    }
    return false;
  }

  public boolean removeTransaction(int selectedRow) {

    //Check if valid row is selected
    if(selectedRow == -1 || selectedRow >= model.getTransactions().size()){
      return false;
    }

    //Get transaction for selected row
    Transaction removeTransaction = model.getTransactions().get(selectedRow);

    //Remove transaction from transactions list
    model.removeTransaction(removeTransaction);

    //Remove transaction from view table
    view.getTableModel().removeRow(selectedRow);

    refresh();
    return true;
  }

  // Other controller methods
}