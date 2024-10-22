package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository("daoImpDB")
public class DaoImpDB implements Dao {

   @PersistenceContext
   private EntityManager entityManager;



   @Override
   @Transactional
   public void save(User user) {
      if (user.getId() == 0) {
         entityManager.persist(user); // Для новых объектов используем persist()
      } else {
         entityManager.merge(user); // Для существующих объектов используем merge()
      }
   }


   @Override
   @Transactional
   public User getById(int id) {
      return entityManager.find(User.class, id);
   }


   @Override
   @SuppressWarnings("unchecked")
   @Transactional
   public List<User> getAll() {
      TypedQuery<User> query = entityManager.createQuery("FROM User ORDER BY id ASC", User.class);
      return query.getResultList();
   }


   @Override
   @Transactional
   public void update(User user) {
      entityManager.merge(user); // merge обновляет существующий объект в базе данных
   }


   @Override
   @Transactional
   public void delete(int id) {
      User user = entityManager.find(User.class, id);
      if (user != null) {
         entityManager.remove(user);
      }
   }
}
