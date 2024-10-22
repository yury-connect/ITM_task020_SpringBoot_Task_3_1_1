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

   // Используем EntityManager вместо SessionFactory
   @PersistenceContext
   private EntityManager entityManager;



   @Override
   @Transactional
   public void save(User user) {
      // Используем EntityManager для сохранения объекта
      if (user.getId() == 0) {
         // Для новых объектов используем persist()
         entityManager.persist(user);
      } else {
         // Для существующих объектов используем merge()
         entityManager.merge(user);
      }
   }


   @Override
   @Transactional
   public User getById(int id) {
      // Используем EntityManager для получения объекта по ID
      return entityManager.find(User.class, id);
   }


   @Override
   @SuppressWarnings("unchecked")
   @Transactional
   public List<User> getAll() {
      // Используем HQL-запрос для получения всех объектов User
      TypedQuery<User> query = entityManager.createQuery("FROM User ORDER BY id ASC", User.class);
      return query.getResultList();
   }


   @Override
   @Transactional
   public void update(User user) {
      // Используем EntityManager для обновления объекта
      entityManager.merge(user); // merge обновляет существующий объект в базе данных
   }


   @Override
   @Transactional
   public void delete(int id) {
      // Находим объект перед удалением
      User user = entityManager.find(User.class, id);
      if (user != null) {
         // Используем EntityManager для удаления объекта
         entityManager.remove(user);
      }
   }
}
