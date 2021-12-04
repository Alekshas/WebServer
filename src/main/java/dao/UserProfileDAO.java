package dao;

import accounts.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class UserProfileDAO {
    public UserProfile findByLogin(String login) {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserProfile.class, login);
    }

    public void save(UserProfile userProfile) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(userProfile);
            tx1.commit();
        }
    }

    public void update(UserProfile userProfile) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(userProfile);
            tx1.commit();
        }
    }

    public void delete(UserProfile userProfile) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(userProfile);
            tx1.commit();
        }
    }

    public ArrayList<UserProfile> findAll() {
        ArrayList<UserProfile> users = (ArrayList<UserProfile>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UserProfile ").list();
        return users;
    }
}
