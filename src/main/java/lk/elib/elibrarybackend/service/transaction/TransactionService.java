package lk.elib.elibrarybackend.service.transaction;

import lk.elib.elibrarybackend.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();

    Transaction findById(int id);

    Transaction save(Transaction transaction);

    Transaction update(Transaction transaction);

    void deleteById(int id);
}
