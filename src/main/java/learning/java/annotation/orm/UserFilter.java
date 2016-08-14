package learning.java.annotation.orm;


@Table("T_USER")
public class UserFilter extends Filter{

    @Column("ID")
    private int id;

    @Column("USER_NAME")
    private String userName;

    @Column("AGE")
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
