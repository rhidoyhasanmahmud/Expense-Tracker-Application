package basement.example.springbootbasement.modules.transaction.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import basement.example.springbootbasement.modules.db.model.Transaction;
import basement.example.springbootbasement.modules.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Transaction} Controller
 * 
 * @author Hasan Mahmud
 * @since 2020-12-17
 *************************************************************************/
@RestController
@RequestMapping("/api/expenseTracker/transaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransactionController {
	
	private final TransactionService service;

	/*************************************************************************
	 * Create a new Transaction
	 * 
	 * @param ob {@link Transaction} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@PostMapping
	public Transaction create(@Valid @RequestBody Transaction ob, HttpServletRequest rq, HttpServletResponse rs) {
		ob.setCreatedBy(Long.valueOf(rq.getHeader("createdBy")));
		ob.setCreatedByEmp(rq.getHeader("createdByEmp") + " (ID:" + rq.getHeader("createdBy") + ")");
		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(ob, rs);
	}

	/*************************************************************************
	 * Get all active {@link Transaction}
	 * 
	 * @param pageSize
	 * @param page
	 * @return {@link List< Transaction>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<Transaction> getAllActive(@RequestParam int page, @RequestParam int pageSize) {
		return service.getAll(true, PageRequest.of(page, pageSize));
	}

	/*************************************************************************
	 * Get all inactive {@link Transaction}
	 * 
	 * @param pageSize
	 * @param page
	 * @return {@link List< Transaction>}
	 *************************************************************************/
	@GetMapping("/getAll/inactive")
	public List<Transaction> getAllInactive(@RequestParam int page, @RequestParam int pageSize) {
		return service.getAll(false, PageRequest.of(page, pageSize));
	}

	/*************************************************************************
	 * Get active {@link Transaction}
	 * 
	 * @param id Id of a {@link Transaction}
	 * @return {@link Transaction}
	 *************************************************************************/
	@GetMapping("/get/{id}")
	public Transaction getById(@PathVariable Long id) {
		return service.getById(id);
	}

	/*************************************************************************
	 * Update {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@PutMapping
	public Transaction update(@Valid @RequestBody Transaction ob, HttpServletRequest rq, HttpServletResponse rs) {
		ob.setUpdatedBy(Long.valueOf(rq.getHeader("updatedBy")));
		ob.setUpdatedByEmp(rq.getHeader("updatedByEmp") + " (ID:" + rq.getHeader("updatedBy") + ")");
		return service.update(ob, rs);
	}

}

