package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	@Entity
	@Table(name = "SQA_ACADEMIC_WORK")
	public class AcademicWork extends AbstractVersionedEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String title;
	    private String supervisor;
	    private String description;
	    
	    @ManyToOne()
	    private User user;
		public User getUser() {
			return user;
		}




		public void setUser(User user) {
			this.user = user;
		}




		@ManyToOne()
	    private TypeAcademicWork type;
		@ManyToOne()
	    private StateAcademicWork state;

   
	    

	    public Long getId() {
			return id;
		}




		public void setId(Long id) {
			this.id = id;
		}




		public String getTitle() {
			return title;
		}




		public void setTitle(String title) {
			this.title = title;
		}




		public String getSupervisor() {
			return supervisor;
		}




		public void setSupervisor(String supervisor) {
			this.supervisor = supervisor;
		}




		public String getDescription() {
			return description;
		}




		public void setDescription(String description) {
			this.description = description;
		}




		public TypeAcademicWork getType() {
			return type;
		}




		public void setType(TypeAcademicWork type) {
			this.type = type;
		}




		public StateAcademicWork getState() {
			return state;
		}




		public void setState(StateAcademicWork state) {
			this.state = state;
		}




		public AcademicWork(){}
	    
	    


}
