package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.entity.Transaction;
import lk.elib.elibrarybackend.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable int id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction addNewTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionById(@PathVariable int id) {
        transactionService.deleteById(id);
    }
}
