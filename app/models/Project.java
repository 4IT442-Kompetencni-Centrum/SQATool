package models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Petr Kadlec on 27.2.2015.
 */
@Entity
public class Project {

    @Id
    public Long id;
    public String name;

    public Project(String name){
        this.name = name;
    }
}
