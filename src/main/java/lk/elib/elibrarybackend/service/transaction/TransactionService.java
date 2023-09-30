package lk.elib.elibrarybackend.service.transaction;

import lk.elib.elibrarybackend.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> findAll();

    TransactionDto findById(int id);

    TransactionDto save(TransactionDto transactionDto);
}
