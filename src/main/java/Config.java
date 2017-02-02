/**
 * Created by Irby on 2/1/17.
 */
public class Config {
    private String username = "adlister_user";
    private String password = "pumpkins";
            private String url = "jdbc:mysql://localhost/adlister_db";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
