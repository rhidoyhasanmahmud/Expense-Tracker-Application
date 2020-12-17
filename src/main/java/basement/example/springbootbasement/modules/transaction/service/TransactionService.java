package basement.example.springbootbasement.modules.transaction.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;

import basement.example.springbootbasement.modules.db.model.Transaction;

/*************************************************************************
 * {@link Transaction} service class
 * 
 * @author Hasan Mahmud
 * @since 2020-12-17
 *************************************************************************/
public interface TransactionService {
	/*************************************************************************
	 * Create a new Transaction
	 * 
	 * @param ob {@link Transaction} object
	 * @param rs
	 * @return {@link Transaction}
	 *************************************************************************/
	Transaction create(Transaction ob, HttpServletResponse rs);

	/*************************************************************************
	 * Get all {@link Transaction} by isActive Flag
	 * 
	 * @param isActive active status of {@link Transaction}
	 * @param pageable
	 * @return {@link List<Transaction>}
	 *************************************************************************/
	List<Transaction> getAll(boolean isActive, Pageable pageable);

	/*************************************************************************
	 * Get {@link Transaction} by id and isActive
	 * 
	 * @param id Id of a {@link Transaction}
	 * @return {@link Transaction}
	 *************************************************************************/
	Transaction getById(Long id);

	/*************************************************************************
	 * Update {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @param rs
	 * @return {@link Transaction}
	 *************************************************************************/
	Transaction update(Transaction ob, HttpServletResponse rs);
}