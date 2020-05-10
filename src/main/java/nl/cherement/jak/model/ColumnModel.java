package nl.cherement.jak.model;

public class ColumnModel {

    private long id;

	private long boardId;

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

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return "ColumnModel [id=" + id + ", boardId=" + boardId + ", name=" + name + "]";
    }
}