package s22.carRest.domain;

/**
 * Class to keep credentials information. 
 * Not entity - but POJO.
 * based on book of Juha Hinkula. 
 * 
 * do we need this?
 *
 */
public class AccountCredentials {
	private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
}
