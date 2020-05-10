package nl.cherement.jak.entity;

import nl.cherement.jak.model.CardModel;

import javax.persistence.*;

@Entity
@Table(name="TBL_CARDS")
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

	public CardEntity(CardModel model) {
		setId(model.getId());
		setColumnId(model.getColumnId());
		setName(model.getName());
		setDescription(model.getDescription());
	}
    
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

    @Override
    public String toString() {
        return "CardEntity [id=" + id + ", columnId=" + columnId +
                ", name=" + name + ", description=" + description   + "]";
    }
}