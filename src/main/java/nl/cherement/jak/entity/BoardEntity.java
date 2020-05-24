package nl.cherement.jak.entity;

import nl.cherement.jak.model.BoardModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@ManyToMany
	private List<UserEntity> users;

    @Column(name="name", nullable = false)
    private String name;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void importModal(BoardModel boardModel) {
		setId(boardModel.getId());
		setUsers(boardModel.getUsers());
		setName(boardModel.getName());
	}

    @Override
    public String toString() {
        return "BoardEntity [id=" + id + ", userId=" + users + ", name=" + name + "]";
    }


}