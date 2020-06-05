package nl.cherement.jak.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @OnDelete( action = OnDeleteAction.CASCADE )
    public CardEntity card;

    @ManyToOne
    @OnDelete( action = OnDeleteAction.CASCADE )
    public ColumnEntity from;

    @ManyToOne
    @OnDelete( action = OnDeleteAction.CASCADE )
    public ColumnEntity to;

    @Column(nullable = false)
    public Timestamp timestamp;

    @Override
    public String toString() {
        return "EventEntity [id=" + id + ", card=" + card +
                ", from=" + from + ", to=" + to + ", timestamp=" + timestamp + "]";
    }
}