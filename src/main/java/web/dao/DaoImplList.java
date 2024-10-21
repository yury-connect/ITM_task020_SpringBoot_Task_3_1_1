package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.ArrayList;
import java.util.List;


@Repository
public class DaoImplList implements Dao {

    private List<User> users;
    private int counterId;


    public DaoImplList() {
        this.users = new ArrayList<>();
    }


    @Override
    public void save(User user) {
        user.setId(counterId++);
        users.add(user);
    }

    @Override
    public User getById(int id) {
        User user = users.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return user;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void update(User user) {
        User updated = users.stream().filter(item -> item.getId() == user.getId()).findFirst().get();
        updated.setUserName(user.getUserName());
        updated.setPassword(user.getPassword());
        updated.setEmail(user.getEmail());
        updated.setFullName(user.getFullName());
        updated.setDateBirth(user.getDateBirth());
        updated.setAddress(user.getAddress());
    }

    @Override
    public void delete(int id) {
        users.removeIf(c -> c.getId() == id); // удаляет все элементы, удовлетворяющие условию
    }
}
