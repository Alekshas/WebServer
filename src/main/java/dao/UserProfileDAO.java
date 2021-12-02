package dao;

import accounts.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.constraints.NotNull;

public class UserProfileDAO implements DAO<UserProfile, String> {
    private final SessionFactory sessionFactory;

    public UserProfileDAO(@NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(@NotNull final UserProfile userProfile){
         try(final Session session =sessionFactory.openSession()){
             session.beginTransaction();
             session.save(userProfile);
             session.getTransaction().commit();
         }
    }

    @Override
    public UserProfile read(String user) {
        try (final Session session = sessionFactory.openSession()) {

            final UserProfile result = session.get(UserProfile.class, user);

            return result != null ? result : new UserProfile();
        }
    }

    @Override
    public void update(UserProfile userProfile) {
        try(final Session session =sessionFactory.openSession()){
            session.beginTransaction();
            session.update(userProfile);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(UserProfile userProfile) {
        try(final Session session =sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(userProfile);
            session.getTransaction().commit();
        }
    }
}
