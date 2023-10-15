package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilter implements TransactionFilter {
    public static List<Integer> filter(List<Transaction> transactions, String filterValue) {
        List<Integer> filteredTransactionRows;
        filteredTransactionRows = new ArrayList<>();
        for (int row=0;row<transactions.size();row++) {
            if(transactions.get(row).getCategory().equalsIgnoreCase(filterValue)){
                filteredTransactionRows.add(row);
            }
        }
        return filteredTransactionRows;
    }
}
