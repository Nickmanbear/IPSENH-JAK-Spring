package nl.cherement.jak.entity;

import javax.persistence.*;

@Entity
@Table(name="columns")
public class ColumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    public BoardEntity board;

    @Column(name="name", nullable = false)
    public String name;

    @Override
    public String toString() {
        return "ColumnEntity [id=" + id + ", board=" + board + ", name=" + name + "]";
    }
}