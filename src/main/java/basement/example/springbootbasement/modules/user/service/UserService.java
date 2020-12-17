package basement.example.springbootbasement.modules.user.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;

import basement.example.springbootbasement.modules.db.model.User;

/*************************************************************************
 * {@link User} service class
 * 
 * @author Hasan Mahmud
 * @since 2020-11-23
 *************************************************************************/
public interface UserService {

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @param rs
	 * @return {@link User}
	 *************************************************************************/
	User create(User ob, HttpServletResponse rs);

	/*************************************************************************
	 * Get all {@link User} by isActive Flag
	 * 
	 * @param isActive active status of {@link User}
	 * @param pageable
	 * @return {@link List<User>}
	 *************************************************************************/
	List<User> getAll(boolean isActive, Pageable pageable);

	/*************************************************************************
	 * Get {@link User} by id and isActive
	 * 
	 * @param id Id of a {@link User}
	 * @return {@link User}
	 *************************************************************************/
	User getById(Long id);

	/*************************************************************************
	 * Update {@link User}
	 * 
	 * @param ob {@link User} object
	 * @param rs
	 * @return {@link User}
	 *************************************************************************/
	User update(User ob, HttpServletResponse rs);

}
