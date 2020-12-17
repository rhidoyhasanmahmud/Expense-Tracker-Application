package basement.example.springbootbasement.modules.db.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import basement.example.springbootbasement.modules.db.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long>{
	List<Transaction> findAllByIsActiveOrderByIdDesc(boolean isActive, Pageable pageable);

	List<Transaction> findAllByIsActive(boolean isActive);
}
