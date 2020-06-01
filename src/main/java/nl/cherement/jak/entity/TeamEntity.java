package nl.cherement.jak.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name="name", nullable = false)
    public String name;

    @ManyToOne
    public UserEntity leader;

    @ManyToMany
    public List<UserEntity> members;

    @Override
    public String toString() {
        return "ColumnEntity [id=" + id + ", name=" + name +
                ", leader=" + leader + ", members=" + members + "]";
    }
}
