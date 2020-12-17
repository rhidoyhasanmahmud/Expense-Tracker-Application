package basement.example.springbootbasement.modules.db.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import basement.example.springbootbasement.modules.db.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	List<Category> findAllByIsActiveOrderByIdDesc(boolean isActive, Pageable pageable);

	List<Category> findAllByIsActive(boolean isActive);

	Boolean existsByNameIgnoreCase(String name);
}
