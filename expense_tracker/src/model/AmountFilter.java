package model;

import java.util.ArrayList;
import java.util.List;

// To filter transactions based on amount
public class AmountFilter implements TransactionFilter {

    // Returns list of rows of filtered transactions
    public List<Integer> filter(List<Transaction> transactions, String filterValue) {
        double filterDoubleValue = Double.parseDouble(filterValue); // convert string value to double.
        List<Integer> filteredTransactionRows = new ArrayList<>();

        // To get row number for all transactions with given amount
        for (int row=0;row<transactions.size();row++) {
            if(transactions.get(row).getAmount() == filterDoubleValue){
                filteredTransactionRows.add(row);
            }
        }
        return filteredTransactionRows;
    }
}
