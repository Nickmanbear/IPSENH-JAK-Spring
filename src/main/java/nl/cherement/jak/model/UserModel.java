package nl.cherement.jak.model;

public class UserModel {

    private long id;
    private String username;
    private String password;
    private int active;
    private int status;
    private String roles = "";
    private String permissions = "";

    public UserModel(long id, String username, String password, int active, int status, String roles, String permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.status = status;
        this.roles = roles;
        this.permissions = permissions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}

