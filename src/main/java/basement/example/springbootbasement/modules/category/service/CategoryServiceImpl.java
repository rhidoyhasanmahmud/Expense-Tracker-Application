package basement.example.springbootbasement.modules.category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basement.example.springbootbasement.modules.db.model.Category;
import basement.example.springbootbasement.modules.db.repository.CategoryRepo;
import basement.example.springbootbasement.modules.db.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link Category} implementation class
 * 
 * @author Hasan Mahmud
 * @since 2020-12-17
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepo repo;
	private final UserRepo userRepo;

	/*************************************************************************
	 * Create a new Category
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public Category create(Category ob, HttpServletResponse rs) {
		try {
			ob.setUser(userRepo.findById(ob.getUserId()).get());
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  Category: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ob;
		}
	}

	/*************************************************************************
	 * Get all {@link Category} by isActive Flag
	 * 
	 * @param isActive active status of {@link Category}
	 * @param pageable
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public List<Category> getAll(boolean isActive, Pageable pageable) {
		return repo.findAllByIsActiveOrderByIdDesc(isActive, pageable).stream().peek(ob -> {
			ob = setRelationalObjectsVal(ob);
		}).collect(Collectors.toList());
	}

	private Category setRelationalObjectsVal(Category ob) {
		if (ob.getUser() != null) {
			ob.setUserId(ob.getUser().getId());
			ob.setUserName(ob.getUser().getFullName());
		}
		return ob;
	}

	/*************************************************************************
	 * Get {@link Category} by id and isActive
	 * 
	 * @param id Id of a {@link Category}
	 * @return {@link List< Category>}
	 *************************************************************************/
	@Override
	public Category getById(Long id) {
		Category ob = repo.findById(id).get();
		return ob;
	}

	/*************************************************************************
	 * Update {@link Category}
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public Category update(Category ob, HttpServletResponse rs) {
		try {
			ob.setUser(userRepo.findById(ob.getUserId()).get());
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to update  Category: ", e);
			rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ob;
		}
	}

	/*************************************************************************
	 * Get All active dropdown data
	 * 
	 * @return
	 *************************************************************************/

	@Override
	public List<Map<String, Object>> getAllActiveDropdown() {
		List<Map<String, Object>> list = new ArrayList<>();
		repo.findAllByIsActive(true).forEach(it -> {
			Map<String, Object> map = new HashMap<>();
			map.put("name", it.getName());
			map.put("id", it.getId());
			list.add(map);
		});
		return list;
	}

	/*************************************************************************
	 * Check if Category name already exist
	 * 
	 * @return
	 *************************************************************************/
	@Override
	public Boolean existsByName(String name) {
		return repo.existsByNameIgnoreCase(name);
	}

}
