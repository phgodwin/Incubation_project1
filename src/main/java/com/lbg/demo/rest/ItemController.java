package com.lbg.demo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.demo.domain.Item;
import com.lbg.demo.service.ItemService;

@RestController
@RequestMapping("/item")
@CrossOrigin

public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
		return this.service.createItem(newItem);
	}

	@GetMapping("/get")
	public List<Item> getItems() {
		return this.service.getItems();
	}

//getItem(@PathVariable int id): Handles a GET request to retrieve a specific item by its id. 
//	The ItemService is used to fetch the item, and a ResponseEntity is returned (which allows customizing the HTTP response).
	@GetMapping("/get/{id}")
	public ResponseEntity<Item> getItem(@PathVariable int id) {
		return this.service.getItem(id);
	}

//removeItem(@PathVariable int id): Handles a DELETE request to remove an item by its id. 
//	The ItemService is used to delete the item, and a boolean indicating success or failure is returned.
	@DeleteMapping("/delete/{id}")
	public boolean removeItem(@PathVariable int id) {
		return this.service.removeItem(id);
	}

//updateItem(@PathVariable int id, @RequestBody Item newItem): Handles a PATCH request to update an existing item. 
//	The ItemService is used to update the item, and a ResponseEntity is returned.
	@PatchMapping("/update/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item newItem) {
		return this.service.updateItem(id, newItem);
	}

}
