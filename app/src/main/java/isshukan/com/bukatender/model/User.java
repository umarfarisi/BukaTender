package isshukan.com.bukatender.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Muhammad Umar Farisi
 * @created 09/05/2017
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String username;
    private String password;

    public User(JSONObject userJSON) throws JSONException {
        this.id = userJSON.getInt("id");
        this.name = userJSON.getString("name");
        this.username = userJSON.getString("username");

        if (userJSON.has("email")){
            this.email = userJSON.getString("email");
        }
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
