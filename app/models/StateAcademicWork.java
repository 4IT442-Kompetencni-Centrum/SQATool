package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Enumeration StateAcademicWork
 * @author Miroslav Cech (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_STATE_ACADEMIC_WORK")
public class StateAcademicWork extends AbstractEnumWithKey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

}

