package nl.cherement.jak.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    public CardEntity card;

    @ManyToOne
    public ColumnEntity from;

    @ManyToOne
    public ColumnEntity to;

    @Column(nullable = false)
    public Timestamp timestamp;

    @Override
    public String toString() {
        return "EventEntity [id=" + id + ", card=" + card +
                ", from=" + from + ", to=" + to + ", timestamp=" + timestamp + "]";
    }
}