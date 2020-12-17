package basement.example.springbootbasement.modules.user.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basement.example.springbootbasement.modules.db.model.User;
import basement.example.springbootbasement.modules.db.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link User} implementation class
 * 
 * @author Hasan Mahmud
 * @since 2020-09-28
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepo repo;

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @return {@link User}
	 *************************************************************************/
	@Override
	public User create(User ob, HttpServletResponse rs) {
		try {
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  User: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ob;
		}
	}

	/*************************************************************************
	 * Get all {@link User} by isActive Flag
	 * 
	 * @param isActive active status of {@link User}
	 * @param pageable
	 * @return {@link User}
	 *************************************************************************/
	@Override
	public List<User> getAll(boolean isActive, Pageable pageable) {
		return repo.findAllByIsActiveOrderByIdDesc(isActive, pageable).stream().peek(ob -> {
		}).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get {@link User} by id and isActive
	 * 
	 * @param id Id of a {@link User}
	 * @return {@link List< User>}
	 *************************************************************************/
	@Override
	public User getById(Long id) {
		User ob = repo.findById(id).get();
		return ob;
	}

	/*************************************************************************
	 * Update {@link User}
	 * 
	 * @param ob {@link User} object
	 * @return {@link User}
	 *************************************************************************/
	@Override
	public User update(User ob, HttpServletResponse rs) {
		try {
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to update  User: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ob;
		}
	}

}
