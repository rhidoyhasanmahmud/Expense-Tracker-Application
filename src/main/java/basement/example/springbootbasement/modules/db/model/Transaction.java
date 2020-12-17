package basement.example.springbootbasement.modules.db.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the sts_transaction database table.
 *
 * @author Hasan Mahmud
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sts_transaction")
@ToString(callSuper = true)
public class Transaction extends AbstractPersistableEntity {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private Category category;

	@Transient
	private Long categoryId;

	@Transient
	private String categoryName;
    
    @ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private User user;
    
    @Transient
	private Long userId;

	@Transient
	private String userName;
    
	@Column(nullable = false)
	@NotNull
    private Double amount;
	
	@Column(nullable = false)
	@NotNull
    private String note;
	
	@Column(nullable = false)
	@NotNull
    private LocalDate transactionDate;

}
