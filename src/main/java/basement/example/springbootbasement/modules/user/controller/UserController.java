package basement.example.springbootbasement.modules.user.controller;

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

import basement.example.springbootbasement.modules.db.model.User;
import basement.example.springbootbasement.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link User} Controller
 * 
 * @author Hasan Mahmud
 * @since 2020-11-23
 *************************************************************************/
@RestController
@RequestMapping("/api/deviceTracking/User")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)

public class UserController {

	private final UserService service;

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link User}
	 *************************************************************************/
	@PostMapping
	public User create(@Valid @RequestBody User ob, HttpServletRequest rq, HttpServletResponse rs) {
		ob.setCreatedBy(Long.valueOf(rq.getHeader("createdBy")));
		ob.setCreatedByEmp(rq.getHeader("createdByEmp") + " (ID:" + rq.getHeader("createdBy") + ")");
		rs.setStatus(HttpServletResponse.SC_CREATED);
		return service.create(ob, rs);
	}

	/*************************************************************************
	 * Get all active {@link User}
	 * 
	 * @param pageSize
	 * @param page
	 * @return {@link List< User>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<User> getAllActive(@RequestParam int page, @RequestParam int pageSize) {
		return service.getAll(true, PageRequest.of(page, pageSize));
	}

	/*************************************************************************
	 * Get all inactive {@link User}
	 * 
	 * @param pageSize
	 * @param page
	 * @return {@link List< User>}
	 *************************************************************************/
	@GetMapping("/getAll/inactive")
	public List<User> getAllInactive(@RequestParam int page, @RequestParam int pageSize) {
		return service.getAll(false, PageRequest.of(page, pageSize));
	}

	/*************************************************************************
	 * Get active {@link User}
	 * 
	 * @param id Id of a {@link User}
	 * @return {@link User}
	 *************************************************************************/
	@GetMapping("/get/{id}")
	public User getById(@PathVariable Long id) {
		return service.getById(id);
	}

	/*************************************************************************
	 * Update {@link User}
	 * 
	 * @param ob {@link User} object
	 * @return {@link User}
	 *************************************************************************/
	@PutMapping
	public User update(@Valid @RequestBody User ob, HttpServletRequest rq, HttpServletResponse rs) {
		ob.setUpdatedBy(Long.valueOf(rq.getHeader("updatedBy")));
		ob.setUpdatedByEmp(rq.getHeader("updatedByEmp") + " (ID:" + rq.getHeader("updatedBy") + ")");
		return service.update(ob, rs);
	}

}
