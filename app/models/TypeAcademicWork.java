package models;

import javax.persistence.*;


@Entity
@Table(name = "SQA_TYPE_ACADEMIC_WORK")
public class TypeAcademicWork extends AbstractEnumWithKey{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long typeAcademicWorkId;

    /**
     * Get Type Academic Work Id
     *
     * @return Long
     */
    public Long getTypeAcademicWorkId() {
        return typeAcademicWorkId;
    }


    /**
     * Set Type Activity Id
     *
     * @param Long typeAcademicWorkId
     * @return TypeAcademicWork
     */
    public TypeAcademicWork setTypeAcademicWorkId(Long typeAcademicWorkId) {
        this.typeAcademicWorkId = typeAcademicWorkId;

        return this;
    }
}
