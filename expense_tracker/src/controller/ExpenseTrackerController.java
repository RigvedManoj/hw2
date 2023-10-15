package controller;

import model.*;
import view.ExpenseTrackerView;

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

  // To get filtered category transactions and highlight them
  public boolean applyCategoryFilter(String categoryFilterValue) {
    if (!InputValidation.isValidCategory(categoryFilterValue)) {
      return false;
    }
    TransactionFilter categoryFilter = new CategoryFilter();
    List<Integer> filteredTransactionRows = categoryFilter.filter(model.getTransactions(),categoryFilterValue);
    view.highlightTable(filteredTransactionRows);
    return true;
  }

  // To get filtered amount transactions and highlight them
  public boolean applyAmountFilter(String amountFilterValue) {
    if (!InputValidation.isValidAmount(Double.parseDouble(amountFilterValue))) {
      return false;
    }
    TransactionFilter amountFilter = new AmountFilter();
    List<Integer> filteredTransactionRows = amountFilter.filter(model.getTransactions(),amountFilterValue);
    view.highlightTable(filteredTransactionRows);
    return true;
  }
  // Other controller methods
}