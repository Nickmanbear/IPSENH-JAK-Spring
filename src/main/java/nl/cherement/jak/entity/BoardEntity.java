package nl.cherement.jak.entity;

import nl.cherement.jak.model.BoardModel;

import javax.persistence.*;

@Entity
@Table(name="board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name="user_id")
	private long userId;

    @Column(name="name", nullable = false)
    private String name;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long columnId) {
		this.userId = columnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void importModal(BoardModel boardModel) {
		setId(boardModel.getId());
		setUserId(boardModel.getUserId());
		setName(boardModel.getName());
	}

    @Override
    public String toString() {
        return "BoardEntity [id=" + id + ", userId=" + userId + ", name=" + name + "]";
    }
}