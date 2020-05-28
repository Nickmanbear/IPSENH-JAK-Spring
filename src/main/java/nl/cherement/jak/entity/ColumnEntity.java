package nl.cherement.jak.entity;

import javax.persistence.*;

@Entity
@Table(name="columns")
public class ColumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="board_id")
    private long boardId;

    @Column(name="name", nullable = false)
    private String name;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long columnId) {
        this.boardId = columnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ColumnEntity [id=" + id + ", boardId=" + boardId + ", name=" + name + "]";
    }
}