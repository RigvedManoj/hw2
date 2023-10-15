package model;

import java.util.ArrayList;
import java.util.List;

public class AmountFilter implements TransactionFilter {
    public static List<Integer> filter(List<Transaction> transactions, String filterValue) {
        double filterIntValue = Double.parseDouble(filterValue);
        List<Integer> filteredTransactionRows;
        filteredTransactionRows = new ArrayList<>();
        for (int row=0;row<transactions.size();row++) {
            if(transactions.get(row).getAmount() == filterIntValue){
                filteredTransactionRows.add(row);
            }
        }
        return filteredTransactionRows;
    }
}
