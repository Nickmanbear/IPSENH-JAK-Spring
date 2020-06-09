package nl.cherement.jak.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    @Column(name="active")
    public boolean active = true;

    @Column(name="roles")
    public String roles = "";

    @Column(name="permissions")
    public String permissions = "";

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }

        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", username=" + username +
                ", password=" + password + ", active=" + active +
                ", roles=" + roles + ", permissions=" + permissions + "]";
    }
}

