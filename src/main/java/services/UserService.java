package services;

import accounts.UserProfile;
import dao.UserProfileDAO;

import java.util.List;

public class UserService {

    private UserProfileDAO userProfileDAO = new UserProfileDAO();

    public UserService() {
        // TODO document why this constructor is empty
    }

    public UserProfile findUserByLogin(String login) {
        return userProfileDAO.findByLogin(login);
    }

    public void saveUser(UserProfile userProfile) {
        userProfileDAO.save(userProfile);
    }

    public void deleteUser(UserProfile userProfile) {
        userProfileDAO.delete(userProfile);
    }

    public void updateUser(UserProfile userProfile) {
        userProfileDAO.update(userProfile);
    }

    public List<UserProfile> findAllUsers() {
        return userProfileDAO.findAll();
    }
}