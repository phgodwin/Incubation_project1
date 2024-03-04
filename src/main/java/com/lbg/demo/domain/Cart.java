package com.lbg.demo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//The @Entity annotation indicates that this class is an entity and should be mapped to a database table.
//In this case, the Cart class represents a table in the database.
@Entity
public class Cart {
//The @Id annotation specifies that the id field is the primary key for the Cart entity.
//	The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation configures the primary key generation strategy.
//	enerationType.IDENTITY indicates that the database will automatically generate unique IDs for each row (usually using auto-increment columns).
//	In other words, when a new Cart entity is persisted, the database assigns a unique ID to it.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//he @JsonManagedReference annotation is not a standard JPA annotation; it’s specific to Jackson (used for JSON serialization/deserialization).
//	It helps manage bidirectional relationships between entities during JSON serialization.
//In this case, it’s used to handle the relationship between Cart and Item entities.
//When serializing Cart to JSON, it ensures that the items property is included, but it avoids infinite loop by not serializing the associated Item objects (since they have a back reference to Cart).
	@JsonManagedReference
//	the @OneToMany annotation establishes a one-to-many relationship between Cart and Item.
//	It indicates that one Cart can have multiple Item entities associated with it.
//The mappedBy attribute specifies the name of the field in the Item entity that maps back to the Cart entity. In this case, it’s the cart field in the Item class.
	@OneToMany(mappedBy = "cart")
	private List<Item> items;
//The default constructor initializes the Cart object.

	public Cart() {
		super();
	}

//The Cart class has the following fields:
//	id: The primary key for the Cart entity.
//	items: A list of associated Item entities.
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}

//n summary, the Cart class represents a database entity with an automatically generated primary key (id). 
//It has a one-to-many relationship with Item entities, and the @JsonManagedReference annotation helps manage the serialization of this relationship when converting to JSON.