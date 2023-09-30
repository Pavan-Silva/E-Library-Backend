package lk.elib.elibrarybackend.repository;

import lk.elib.elibrarybackend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    boolean existsById(int id);
}