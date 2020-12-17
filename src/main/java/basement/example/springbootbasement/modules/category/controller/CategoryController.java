package basement.example.springbootbasement.modules.category.controller;

import java.util.List;
import java.util.Map;

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

import basement.example.springbootbasement.modules.category.service.CategoryService;
import basement.example.springbootbasement.modules.db.model.Category;
import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Category} Controller
 * 
 * @author Hasan Mahmud
 * @since 2020-12-17
 *************************************************************************/
@RestController
@RequestMapping("/api/expenseTracker/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
	
	private final CategoryService service;

	/*************************************************************************
	 * Create a new Category
	 * 
	 * @param ob {@link Category} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Category}
	 *************************************************************************/
	@PostMapping
	public Category create(@Valid @RequestBody Category ob, HttpServletRequest rq, HttpServletResponse rs) {
		ob.setCreatedBy(Long.valueOf(rq.getHeader("createdBy")));
		ob.setCreatedByEmp(rq.getHeader("createdByEmp") + " (ID:" + rq.getHeader("createdBy") + ")");
		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(ob, rs);
	}

	/*************************************************************************
	 * Get all active {@link Category}
	 * 
	 * @param pageSize
	 * @param page
	 * @return {@link List< Category>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<Category> getAllActive(@RequestParam int page, @RequestParam int pageSize) {
		return service.getAll(true, PageRequest.of(page, pageSize));
	}

	/*************************************************************************
	 * Get all inactive {@link Category}
	 * 
	 * @param pageSize
	 * @param page
	 * @return {@link List< Category>}
	 *************************************************************************/
	@GetMapping("/getAll/inactive")
	public List<Category> getAllInactive(@RequestParam int page, @RequestParam int pageSize) {
		return service.getAll(false, PageRequest.of(page, pageSize));
	}

	/*************************************************************************
	 * Get active {@link Category}
	 * 
	 * @param id Id of a {@link Category}
	 * @return {@link Category}
	 *************************************************************************/
	@GetMapping("/get/{id}")
	public Category getById(@PathVariable Long id) {
		return service.getById(id);
	}

	/*************************************************************************
	 * Update {@link Category}
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@PutMapping
	public Category update(@Valid @RequestBody Category ob, HttpServletRequest rq, HttpServletResponse rs) {
		ob.setUpdatedBy(Long.valueOf(rq.getHeader("updatedBy")));
		ob.setUpdatedByEmp(rq.getHeader("updatedByEmp") + " (ID:" + rq.getHeader("updatedBy") + ")");
		return service.update(ob, rs);
	}

	/*************************************************************************
	 * Get All active dropdown data
	 * 
	 * @return
	 *************************************************************************/
	@GetMapping("getAll/active/dropdown")
	public List<Map<String, Object>> getAllActiveDropdown() {
		return service.getAllActiveDropdown();
	}

	/*************************************************************************
	 * Check if Category name already exist
	 * 
	 * @return
	 *************************************************************************/
	@GetMapping("/exists/byName")
	public Boolean existsByName(@RequestParam String name) {
		return service.existsByName(name);
	}
}
