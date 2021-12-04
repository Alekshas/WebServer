package accounts;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserProfile {

    @Id
    @Column
    private final String login;
    @Column
    private final String pass;

    public UserProfile(String login, String pass) {
        this.login = login;
        this.pass = pass;

    }

    public UserProfile() {
        login = null;
        pass = null;
    }

    public String getLogin() {
        return login;
    }
    public String getPass() {
        return pass;
    }
}
