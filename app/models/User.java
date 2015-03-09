package models;


import play.data.validation.Constraints;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SQA_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Constraints.Required
    public String userName;
    @Constraints.Required
    public String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public static User findById(long id){
        return JPA.em().find(User.class, id);
    }

    public void save(){
        JPA.em().persist(this);
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
