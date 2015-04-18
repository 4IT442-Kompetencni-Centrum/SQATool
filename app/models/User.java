package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SQA_USER")
public class User extends AbstractVersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String username;
    public String password;
    @OneToMany(mappedBy="user")
    private List<RoleInBusiness> roleInBusiness;
    public String degree;
    public String email;
    public String firstName;
    public String lastName;
    @ManyToOne(fetch=FetchType.EAGER)
    public StateUser stateUser;

    public User(){}

    public User(String userName, String password){
        this.username = userName;
        this.password = password;
    }

    public List<RoleInBusiness> getRoleInBusiness() {
		return roleInBusiness;
	}

	public void setRoleInBusiness(List<RoleInBusiness> roleInBusiness) {
		this.roleInBusiness = roleInBusiness;
	}

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

    public static class Page{

        private final int pageSize;
        private final long totalRowCount;
        private final int pageIndex;
        private final List<User> userList;

        public Page(int pageSize, long totalRowCount, int pageIndex, List<User> userList){
            this.totalRowCount = totalRowCount;
            this.pageSize = pageSize;
            this.pageIndex = pageIndex;
            this.userList = userList;
        }

        public boolean hasPrev(){
            return pageIndex > 1;
        }

        public boolean hasNext(){
            return (totalRowCount / pageSize) >= pageIndex;
        }

        public int getPageSize(){
            return pageSize;
        }

        public long getTotalRowCount() {
            return totalRowCount;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public List<User> getUserList() {
            return userList;
        }
    }
}
