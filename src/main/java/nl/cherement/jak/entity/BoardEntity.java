package nl.cherement.jak.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToMany
    public List<UserEntity> users;

    @Column(name="name", nullable = false)
    public String name;

    @Override
    public String toString() {
        return "BoardEntity [id=" + id + ", users=" + users + ", name=" + name + "]";
    }
}