package com.lbg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.demo.domain.Item;
import com.lbg.demo.repo.ItemRepo;

@Service
public class ItemService {
//	The ItemService defines several methods:
//		createItem(Item newItem): Saves a new item in the database using the ItemRepo. The newItem object is passed as an argument.
//		It returns a ResponseEntity<Item> with the created item and an HTTP status of CREATED.
//		getItems(): Retrieves a list of all items from the database using the ItemRepo.
//		getItem(int id): Retrieves a specific item by its id. The ItemRepo is used to fetch the item, and a ResponseEntity is returned (which allows customizing the HTTP response).
//		If the item is not found, it returns an HTTP status of NOT_FOUND.
//		removeItem(int id): Deletes an item by its id. The ItemRepo is used to delete the item, and a boolean indicating success or failure is returned.
//		updateItem(int id, Item newItem): Updates an existing item by its id. The ItemRepo is used to update the item, and a ResponseEntity is returned.
//		If the item is not found, it returns an HTTP status of NOT_FOUND.
//		The existing item is modified based on the properties of the newItem.
	private ItemRepo repo;

	public ItemService(ItemRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {
		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItems() {
		return this.repo.findAll();
	}

	public ResponseEntity<Item> getItem(int id) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item body = found.get();

		return ResponseEntity.ok(body);
	}

	public boolean removeItem(int id) {

		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}

	public ResponseEntity<Item> updateItem(int id, Item newItem) {

		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item existing = found.get();

		if (newItem.getName() != null) {
			existing.setName(newItem.getName());
		}
		if (newItem.getPrice() != null) {
			existing.setPrice(newItem.getPrice());
		}

		if (newItem.getQuantity() != null) {
			existing.setQuantity(newItem.getQuantity());
		}

		if (newItem.getCart() != null) {
			existing.setCart(newItem.getCart());
		}

		Item updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);

	}
}

//In summary, the ItemService class encapsulates the business logic related to items. 
//It interacts with the ItemRepo to perform CRUD operations on items. 
//The actual database operations are delegated to the repository, and the service layer handles error responses and business logic
