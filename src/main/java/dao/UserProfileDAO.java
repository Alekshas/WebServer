package dao;

import accounts.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;

public class UserProfileDAO {
    SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public UserProfile findByLogin(String login) {

        try (Session session = sessionFactory.openSession()) {
            return session.get(UserProfile.class, login);
        }
    }

    public void save(UserProfile userProfile) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(userProfile);
            tx1.commit();
        }
    }

    public void update(UserProfile userProfile) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(userProfile);
            tx1.commit();
        }
    }

    public void delete(UserProfile userProfile) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(userProfile);
            tx1.commit();
        }
    }

    public ArrayList<UserProfile> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            ArrayList<UserProfile> users = (ArrayList<UserProfile>) session.createQuery("From UserProfile ").list();
            return users;
        }
    }
}
