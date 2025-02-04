import javax.swing.JOptionPane;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle filter button clicks
    view.getFilterBtn().addActionListener(e -> {
      // Get filter data from view
      String FilterValue = view.getFilterField();
      String FilterChoice = view.getFilterChoice();

      //Call controller to apply filter
      boolean filtered = controller.applyFilter(FilterChoice, FilterValue);
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid filter value entered");
        controller.refresh();
        view.toFront();
      }
    });

    // Handles remove transaction button clicks
    view.getRemoveTransactionBtn().addActionListener(e -> {

      // Get row selected by user from view
      int selectedRow = view.getTransactionsTable().getSelectedRow();

      // Call controller to remove transaction
      boolean added = controller.removeTransaction(selectedRow);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid row selected");
        view.toFront();
      }
    });
  }
}