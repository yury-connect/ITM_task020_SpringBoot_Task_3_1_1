import web.model.User;
import web.util.UserUtils;

import java.util.List;



public class Testes {
    public static void main(String[] args) {

        List<User> users = UserUtils.generateUsers(20);
        users.forEach(System.out::println);
    }
}
