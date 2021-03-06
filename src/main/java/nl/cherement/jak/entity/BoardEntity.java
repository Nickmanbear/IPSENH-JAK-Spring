package nl.cherement.jak.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToMany
    public List<UserEntity> users;

    @Column(nullable = false)
    public String name;

    @ManyToOne
    public TeamEntity team;

    @Override
    public String toString() {
        return "BoardEntity [id=" + id + ", users=" + users + ", name=" + name + ", team=" + team + "]";
    }
}
