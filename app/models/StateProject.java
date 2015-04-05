package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Enumeration StateProject
 * @author Tomas Michalicka (<a href='mailto:tomas@michalicka.com'>tomas@michalicka.com</a>)
 *
 */
@Entity
@Table(name="SQA_STATE_PROJECT")
public class StateProject extends AbstractEnumWithKey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

}
