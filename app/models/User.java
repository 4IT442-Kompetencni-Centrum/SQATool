package models;


import play.data.validation.Constraints;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

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

        private int pageSize;
        private long totalRowCount;
        private int pageIndex;
        private List<User> userList;

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

    }
}
