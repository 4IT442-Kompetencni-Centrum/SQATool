package models;


import javax.persistence.*;

@Entity
@Table(name="SQA_USER_LOGGED_ON_ACTIVITY")
public class UserLoggedOnActivity extends AbstractVersionedEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long userLoggedOnActivityId;

    @ManyToOne(fetch = FetchType.EAGER)
    protected User user;

    @ManyToOne(fetch = FetchType.EAGER)
    protected Activity activity;

    @ManyToOne(fetch = FetchType.EAGER)
    protected TypeRoleOnActivity typeRoleOnActivity;


    /**
     * Get User Logged On Activity Id
     *
     * @return Long
     */
    public Long getUserLoggedOnActivityId() {
        return userLoggedOnActivityId;
    }


    /**
     * Set User Logged On Activity Id
     *
     * @param Long userLoggedOnActivityId
     * @return Long
     */
    public UserLoggedOnActivity setUserLoggedOnActivityId(Long userLoggedOnActivityId) {
        this.userLoggedOnActivityId = userLoggedOnActivityId;

        return this;
    }


    /**
     * Get User
     *
     * @return User
     */
    public User getUser() {
        return user;
    }


    /**
     * Set User
     *
     * @param User user
     * @return UserLoggedOnActivity
     */
    public UserLoggedOnActivity setUser(User user) {
        this.user = user;

        return this;
    }


    /**
     * Get Activity
     *
     * @return Activity
     */
    public Activity getActivity() {
        return activity;
    }


    /**
     * Set Activity
     *
     * @param Activity activity
     * @return UserLoggedOnActivity
     */
    public UserLoggedOnActivity setActivity(Activity activity) {
        this.activity = activity;

        return this;
    }


    /**
     * Get Role On Activity
     *
     * @return TypeRoleOnActivity
     */
    public TypeRoleOnActivity getTypeRoleOnActivity() {
        return typeRoleOnActivity;
    }


    /**
     * Set Role On Activity
     *
     * @param RoleOnActivity typeRoleOnActivity
     * @return UserLoggedOnActivity
     */
    public UserLoggedOnActivity setTypeRoleOnActivity(TypeRoleOnActivity typeRoleOnActivity) {
        this.typeRoleOnActivity = typeRoleOnActivity;

        return this;
    }
}
