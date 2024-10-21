package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import web.dao.Dao;
import web.model.User;

import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private Dao dao;


    @Autowired
//    @Qualifier("DaoImpl")
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }



    @Override
    public void save(User user) {
        dao.save(user);
    }


    @Override
    public User getById(int id) {
        return dao.getById(id);
    }


    @Override
    public List<User> getAll() {
        return dao.getAll()  ;
    }


    @Override
    public void update(User user) {
        dao.update(user);
    }


    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
