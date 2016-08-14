package learning.java.annotation.orm;


public class ORMTest {
    public static void main(String[] args) {
        UserFilter userFilter = new UserFilter();
        userFilter.setId(1);
        userFilter.setUserName("Tom");
        userFilter.setAge(15);
        String sql = userFilter.query();
        System.out.println(sql);
    }
}
