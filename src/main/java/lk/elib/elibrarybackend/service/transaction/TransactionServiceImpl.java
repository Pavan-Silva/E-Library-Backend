package lk.elib.elibrarybackend.service.transaction;

import lk.elib.elibrarybackend.dto.TransactionDto;
import lk.elib.elibrarybackend.entity.Transaction;
import lk.elib.elibrarybackend.exception.ResourceNotFoundException;
import lk.elib.elibrarybackend.repository.TransactionRepository;
import lk.elib.elibrarybackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionDto> findAll() {
        return Mapper.transactionListToDto(transactionRepository.findAll());
    }

    @Override
    public TransactionDto findById(int id) {
        Optional<Transaction> optTransaction = transactionRepository.findById(id);

        if (optTransaction.isPresent()) {
            return Mapper.transactionToDto(optTransaction.get());

        } else {
            throw new ResourceNotFoundException("Invalid transaction id - " + id);
        }
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        Transaction transaction = Mapper.dtoToTransaction(transactionDto);
        return Mapper.transactionToDto(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDto update(TransactionDto transactionDto) {
        if (transactionRepository.existsById(transactionDto.getId())) {
            Transaction transaction = Mapper.dtoToTransaction(transactionDto);
            return Mapper.transactionToDto(transactionRepository.save(transaction));

        } else {
            throw new ResourceNotFoundException("Invalid transaction id - " + transactionDto.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Invalid transaction id - " + id);
        }
    }
}
