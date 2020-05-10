package nl.cherement.jak.model;

public class BoardModel {

    private long id;

	private long userId;

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

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return "BoardModel [id=" + id + ", userId=" + userId + ", name=" + name + "]";
    }
}