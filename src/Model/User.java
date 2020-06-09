package Model;

public class User {
    private String id;
    private String username;
    private String mail;

    public User(String id, String username, String mail) {
        this.id = id;
        this.username = username;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
