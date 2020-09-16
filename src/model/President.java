package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "presidents")
public class President {
	//public fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")	
	private int id;	
	@Column(name="NUMBER")	
	private int presidencyNumber;
	@Column(name="NAME")
	private String name;
	
	public President() {
		super();
	}
	
	public President(int presidencyNumber, String name) {
		this.presidencyNumber = presidencyNumber;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPresidencyNumber() {
		return presidencyNumber;
	}
	public void setPresidencyNumber(int presidencyNumber) {
		this.presidencyNumber = presidencyNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String returnPresidentDetails( ) {
		return Integer.toString(presidencyNumber) + ":" + name;
	}
}
