package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository("daoImpDB")
public class DaoImpDB implements Dao {

   private SessionFactory sessionFactory;


   @Autowired
   public DaoImpDB(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }


   @Override
   @Transactional
   public void save(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.save(user);
   }


   @Override
   @Transactional
   public User getById(int id) {
      Session session = sessionFactory.getCurrentSession();
      return session.get(User.class, id);  // использование session.get() вместо запроса HQL
   }


   @Override
   @SuppressWarnings("unchecked")
   @Transactional
   public List<User> getAll() {
      Session session = sessionFactory.getCurrentSession();
      TypedQuery<User> query = session.createQuery("from User", User.class);
      return query.getResultList();
   }


   @Override
   @Transactional
   public void update(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.update(user); // обновляем существующую запись в базе данных
   }


   @Override
   @Transactional
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      User user = session.get(User.class, id);  // сначала находим пользователя по id
      if (user != null) {
         session.delete(user); // если пользователь найден, удаляем его
      }
   }
}

/*
Сейчас рассмотрим детально этроку запроса:
.createQuery("from User u join fetch u.car WHERE u.car.model = :model AND u.car.series = :series", User.class);

 "from User u join fetch u.car"
from User: это означает, что мы запрашиваем сущность User из базы данных.
u: это алиас для сущности User. С его помощью мы можем ссылаться на поля этого объекта в дальнейшем
 join fetch u.car: это инструкция для жадного соединения (fetch join) с другой сущностью, связанной с User.
 В данном случае, u.car — это связь между сущностью User и сущностью Car.

 Как работает join fetch?
join: выполняет соединение двух сущностей (User и Car) на уровне объекта.
fetch: указывает Hibernate, что нужно немедленно загрузить данные связанной сущности (Car).
 Это отличается от стандартного ленивого (lazy) соединения, при котором данные связанной сущности загружаются
 только при прямом обращении к ней, что и вызывает ошибку LazyInitializationException, если сессия уже закрыта.
Вместо получения лишь ссылки на объект Car (при ленивой загрузке), Hibernate загружает все поля объекта Car в один запрос.

 "WHERE u.car.model = :model AND u.car.series = :series"
WHERE: добавляет фильтрацию данных. Мы указываем условие для выборки, которое должно быть выполнено.
u.car.model = :model: фильтрует пользователей по модели автомобиля. :model — это именованный параметр,
 значение которого будет установлено программно с помощью метода query.setParameter("model", model).
u.car.series = :series: аналогично фильтрует по серии автомобиля.

 "User.class"
Второй параметр метода 'createQuery' — это тип, возвращаемый запросом. createQuery —
 это класс, который указывает Hibernate, что ожидается результат
 в виде объектов типа User. Таким образом, запрос возвращает список объектов User, а не массивы данных.
 */
