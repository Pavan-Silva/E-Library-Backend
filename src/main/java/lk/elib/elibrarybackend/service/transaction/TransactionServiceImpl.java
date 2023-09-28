package lk.elib.elibrarybackend.service.transaction;

import lk.elib.elibrarybackend.entity.Transaction;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(int id) {
        Optional<Transaction> optTransaction = transactionRepository.findById(id);

        if (optTransaction.isPresent()) {
            return optTransaction.get();

        } else {
            throw new ResourceNotFoundException("Invalid transaction id - " + id);
        }
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        Optional<Transaction> optTransaction = transactionRepository.findById(transaction.getId());

        if (optTransaction.isPresent()) {
            return transactionRepository.save(transaction);

        } else {
            throw new ResourceNotFoundException("Invalid transaction id - " + transaction.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Transaction> optTransaction = transactionRepository.findById(id);

        if (optTransaction.isPresent()) {
            transactionRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid transaction id - " + id);
        }
    }
}
