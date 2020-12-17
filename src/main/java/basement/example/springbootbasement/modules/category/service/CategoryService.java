package basement.example.springbootbasement.modules.category.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;

import basement.example.springbootbasement.modules.db.model.Category;


/*************************************************************************
* {@link Category} service class 
* 
* @author Hasan Mahmud
* @since  2020-12-17
*************************************************************************/
public interface CategoryService {
	/*************************************************************************
     * Create a new Category
     * @param ob {@link Category} object
	 * @param rs 
     * @return {@link Category}
     *************************************************************************/
	Category create(Category ob, HttpServletResponse rs);
	/*************************************************************************
     * Get all {@link Category} by isActive Flag
     * @param isActive active status of {@link Category}
     * @param pageable
     * @return {@link List<Category>}
     *************************************************************************/
	List<Category> getAll(boolean isActive, Pageable pageable);
	/*************************************************************************
     * Get {@link Category} by id and isActive
     * @param id  Id of a {@link Category}
     * @return {@link Category}
     *************************************************************************/
	Category getById(Long id);
	/*************************************************************************
     * Update {@link Category}
     * @param ob {@link Category} object
	 * @param rs 
     * @return {@link Category}
     *************************************************************************/
	Category update(Category ob, HttpServletResponse rs);
	/*************************************************************************
     * Get All active dropdown data
     * @return
     *************************************************************************/
	List<Map<String, Object>> getAllActiveDropdown();
	
	/*************************************************************************
	 * Check if Category name already exist
	 * 
	 * @return
	 *************************************************************************/
	Boolean existsByName(String name);
}
