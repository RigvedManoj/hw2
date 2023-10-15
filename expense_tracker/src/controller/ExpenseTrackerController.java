package controller;

import model.AmountFilter;
import model.CategoryFilter;
import view.ExpenseTrackerView;

import java.util.ArrayList;
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

  public boolean filterCategoryTransaction(String categoryFilterValue) {
    if (!InputValidation.isValidCategory(categoryFilterValue)) {
      return false;
    }
    List<Integer> filteredTransactionRows = new ArrayList<>();
    filteredTransactionRows = CategoryFilter.filter(model.transactions,categoryFilterValue);
    view.highlightTable(filteredTransactionRows);
    return true;
  }

  public boolean filterAmountTransaction(String amountFilterValue) {
    if (!InputValidation.isValidAmount(Double.parseDouble(amountFilterValue))) {
      return false;
    }
    List<Integer> filteredTransactionRows = new ArrayList<>();
    filteredTransactionRows = AmountFilter.filter(model.transactions,amountFilterValue);
    view.highlightTable(filteredTransactionRows);
    return true;
  }
  // Other controller methods
}