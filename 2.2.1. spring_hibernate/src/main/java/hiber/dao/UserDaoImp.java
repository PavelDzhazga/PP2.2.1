package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUser(String model, int series) {

      TypedQuery<User> queryCar = sessionFactory
              .getCurrentSession()
              .createQuery("FROM User u JOIN FETCH u.car WHERE u.car.model = :model AND u.car.series = :series");
      queryCar.setParameter("model", model);
      queryCar.setParameter("series", series);
      return queryCar.getSingleResult();


   }

}
