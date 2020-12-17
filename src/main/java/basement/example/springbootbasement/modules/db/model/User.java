package basement.example.springbootbasement.modules.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the sts_user database table.
 *
 * @author Hasan Mahmud
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sts_user")
@ToString(callSuper = true)
public class User extends AbstractPersistableEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@NotNull
    private String fullName;
    
    @Column(nullable = false, unique = true)
	@NotNull
	@Email
    private String email;
    
    private String password;

}
