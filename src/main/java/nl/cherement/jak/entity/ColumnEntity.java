package nl.cherement.jak.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="columns")
public class ColumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @OnDelete( action = OnDeleteAction.CASCADE )
    public BoardEntity board;

    @Column(name="name", nullable = false)
    public String name;

    @Override
    public String toString() {
        return "ColumnEntity [id=" + id + ", board=" + board + ", name=" + name + "]";
    }
}