package nl.cherement.jak.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="cards")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @OnDelete( action = OnDeleteAction.CASCADE )
    public ColumnEntity column;

    @ManyToOne
    public UserEntity assignedUser;

    @Column(name="name", nullable = false)
    public String name;

    @Column(name="description")
    public String description;

    @Column(name="priority")
    public Long priority = 0L;

    @Column(name="points")
    public Long points = 0L;

    @Override
    public String toString() {
        return "CardEntity [id=" + id + ", column=" + column +
                ", name=" + name + ", description=" + description   +
                ", priority=" + priority + ", points=" + points + "]";
    }
}