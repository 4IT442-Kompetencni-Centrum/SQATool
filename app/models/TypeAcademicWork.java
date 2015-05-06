package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_TYPE_ACADEMIC_WORK")
public class TypeAcademicWork extends AbstractEnumWithKey{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}

