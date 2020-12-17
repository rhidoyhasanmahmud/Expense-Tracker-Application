package basement.example.springbootbasement.modules.transaction.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basement.example.springbootbasement.modules.db.model.Transaction;
import basement.example.springbootbasement.modules.db.repository.CategoryRepo;
import basement.example.springbootbasement.modules.db.repository.TransactionRepo;
import basement.example.springbootbasement.modules.db.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link Transaction} implementation class
 * 
 * @author Hasan Mahmud
 * @since 2020-12-17
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepo repo;
	private final UserRepo userRepo;
	private final CategoryRepo categoryRepo;

	/*************************************************************************
	 * Create a new Transaction
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@Override
	public Transaction create(Transaction ob, HttpServletResponse rs) {
		try {
			ob.setUser(userRepo.findById(ob.getUserId()).get());
			ob.setCategory(categoryRepo.findById(ob.getCategoryId()).get());
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  Transaction: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ob;
		}
	}

	/*************************************************************************
	 * Get all {@link Transaction} by isActive Flag
	 * 
	 * @param isActive active status of {@link Transaction}
	 * @param pageable
	 * @return {@link Transaction}
	 *************************************************************************/
	@Override
	public List<Transaction> getAll(boolean isActive, Pageable pageable) {
		return repo.findAllByIsActiveOrderByIdDesc(isActive, pageable).stream().peek(ob -> {
			ob = setRelationalObjectsVal(ob);
		}).collect(Collectors.toList());
	}

	private Transaction setRelationalObjectsVal(Transaction ob) {
		if (ob.getUser() != null) {
			ob.setUserId(ob.getUser().getId());
			ob.setUserName(ob.getUser().getFullName());
		}
		if (ob.getCategory() != null) {
			ob.setCategoryId(ob.getCategory().getId());
			ob.setCategoryName(ob.getCategory().getName());
		}
		return ob;
	}

	/*************************************************************************
	 * Get {@link Transaction} by id and isActive
	 * 
	 * @param id Id of a {@link Transaction}
	 * @return {@link List< Transaction>}
	 *************************************************************************/
	@Override
	public Transaction getById(Long id) {
		Transaction ob = repo.findById(id).get();
		return ob;
	}

	/*************************************************************************
	 * Update {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@Override
	public Transaction update(Transaction ob, HttpServletResponse rs) {
		try {
			ob.setUser(userRepo.findById(ob.getUserId()).get());
			ob.setCategory(categoryRepo.findById(ob.getCategoryId()).get());
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to update  Transaction: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ob;
		}
	}
}
