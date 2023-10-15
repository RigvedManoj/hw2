package model;

import java.util.ArrayList;
import java.util.List;

// To filter transactions based on category
public class CategoryFilter implements TransactionFilter {

    // Returns list of rows of filtered transactions
    public List<Integer> filter(List<Transaction> transactions, String filterValue) {
        List<Integer> filteredTransactionRows = new ArrayList<>();

        // To get row number for all transactions with given category
        for (int row=0;row<transactions.size();row++) {
            if(transactions.get(row).getCategory().equalsIgnoreCase(filterValue)){
                filteredTransactionRows.add(row);
            }
        }

        return filteredTransactionRows;
    }
}
