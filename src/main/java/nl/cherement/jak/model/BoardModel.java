package nl.cherement.jak.model;

import nl.cherement.jak.entity.UserEntity;

import java.util.List;

public class BoardModel {

    private long id;

	private List<UserEntity> users;

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

    @Override
    public String toString() {
        return "BoardModel [id=" + id + ", userId=" + users + ", name=" + name + "]";
    }
}