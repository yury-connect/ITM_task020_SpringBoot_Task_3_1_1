package web.dao;

import web.model.User;

import java.util.List;


public interface UserDao {

    void save(User user);

    User getById(int id);

    List<User> getAll();

    void update(User user);

    void delete(int id);
}
