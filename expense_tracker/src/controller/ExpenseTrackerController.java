package controller;

import view.ExpenseTrackerView;

import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
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