package nl.cherement.jak.model;

public class CardModel {

    private long id;

	private long columnId;

    private String name;

    private String description;

    private Long priority;

    private Long points;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getColumnId() {
		return columnId;
	}

	public void setColumnId(long columnId) {
		this.columnId = columnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	@Override
    public String toString() {
        return "CardModel [id=" + id + ", columnId=" + columnId +
                ", name=" + name + ", description=" + description   +
				", priority=" + priority + ", points=" + points + "]";
    }
}