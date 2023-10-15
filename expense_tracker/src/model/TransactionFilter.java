package model;

import java.util.List;

// Interface for all Filter Classes
public interface TransactionFilter {
    List<Integer> filter(List<Transaction> transactions, String filterValue);
}
