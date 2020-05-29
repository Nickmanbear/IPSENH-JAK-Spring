package nl.cherement.jak.entity;

import javax.persistence.*;

@Entity
@Table(name="cards")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    public ColumnEntity column;

    @ManyToOne
    public UserEntity assignedUser;

    @Column(name="name", nullable = false)
    public String name;

    @Column(name="description")
    public String description;

    @Column(name="priority")
    public Long priority;

    @Column(name="points")
    public long points;

    @Override
    public String toString() {
        return "CardEntity [id=" + id + ", column=" + column +
                ", name=" + name + ", description=" + description   +
                ", priority=" + priority + ", points=" + points + "]";
    }
}