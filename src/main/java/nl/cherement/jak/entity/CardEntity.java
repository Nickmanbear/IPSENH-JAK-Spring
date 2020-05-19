package nl.cherement.jak.entity;

import nl.cherement.jak.model.CardModel;

import javax.persistence.*;

@Entity
@Table(name="cards")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name="column_id")
	private long columnId;

    @Column(name="name", nullable = false)
    private String name;

	@Column(name="description")
    private String description;

	@Column(name="priority")
    private Long priority;

	@Column(name="points")
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

	public void importModal(CardModel cardModel) {
		setId(cardModel.getId());
		setColumnId(cardModel.getColumnId());
		setName(cardModel.getName());
		setDescription(cardModel.getDescription());
		setPriority(cardModel.getPriority());
		setPoints(cardModel.getPoints());
	}

    @Override
    public String toString() {
        return "CardEntity [id=" + id + ", columnId=" + columnId +
                ", name=" + name + ", description=" + description   +
				", priority=" + priority + ", points=" + points + "]";
    }
}