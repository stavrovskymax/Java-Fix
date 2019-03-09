package ru.ivmiit.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import ru.ivmiit.models.User;
import ru.ivmiit.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UsersDaoImpl implements UsersDao {

    private final String HQL_SELECT_ALL_USER_WITH_CAR = "select user.firstName as firstName, user.lastName as lastName, car.model as model from User user left join user.cars as car";

    private final String HQL_SELECT_PASSWORD_BY_LOGIN = "select user.password from User as user where user.login = :param1";

    public boolean exist(String login, String password) {
        // Old implement
        /*List<String> userPasswords = (List<String>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(HQL_SELECT_PASSWORD_BY_LOGIN).setParameter("param1", login).list();
        if (!userPasswords.isEmpty()) {
            String userPassword = userPasswords.get(0);
            return BCrypt.checkpw(password, userPassword);
        }
        return false;*/
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Criteria userCriteria = session.createCriteria(User.class).add(Restrictions.eq("login", login));
        User user = (User) userCriteria.uniqueResult();
        if (user != null) {
            String userPassword = user.getPassword();
            return BCrypt.checkpw(password, userPassword);
        }
        return false;
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from User").list();
        return users;
    }
}
