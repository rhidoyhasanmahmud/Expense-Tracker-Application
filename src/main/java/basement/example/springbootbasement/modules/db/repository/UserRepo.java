package basement.example.springbootbasement.modules.db.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import basement.example.springbootbasement.modules.db.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	List<User> findAllByIsActiveOrderByIdDesc(boolean isActive, Pageable pageable);

	List<User> findAllByIsActive(boolean isActive);
}
