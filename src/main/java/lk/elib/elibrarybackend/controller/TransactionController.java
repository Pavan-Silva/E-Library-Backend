package lk.elib.elibrarybackend.controller;

import lk.elib.elibrarybackend.dto.TransactionDto;
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
    public List<TransactionDto> findAllTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable int id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public TransactionDto addNewTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.save(transactionDto);
    }
}
