package com.lbg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.demo.domain.Cart;
import com.lbg.demo.repo.CartRepo;

@Service
//The @Service annotation indicates that this class is a Spring service component.
//It is typically used to define business logic and interact with repositories or other services.
public class CartService {
//	The CartService defines several methods:
//		createCart(Cart newCart): Saves a new cart in the database using the CartRepo. The newCart object is passed as an argument.
//		getCarts(): Retrieves a list of all carts from the database using the CartRepo.
//		getCart(int id): Retrieves a specific cart by its id. The CartRepo is used to fetch the cart, and a ResponseEntity is returned (which allows customizing the HTTP response).
//		removeCart(int id): Deletes a cart by its id. The CartRepo is used to delete the cart, and a boolean indicating success or failure is returned.
	private CartRepo repo;

	public CartService(CartRepo repo) {
		super();
		this.repo = repo;
	}

	public Cart createCart(Cart newCart) {
		return this.repo.save(newCart);
	}

	public List<Cart> getCarts() {
		return this.repo.findAll();
	}

	public ResponseEntity<Cart> getCart(int id) {
		Optional<Cart> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(found.get());
	}

//	public ResponseEntity<Cart> updateCart(int id, Cart newCart) {
//		Optional<Cart> found = this.repo.findById(id);
//
//		if (found.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}

	public boolean removeCart(int id) {
		this.repo.deleteById(id);

		return !this.repo.existsById(id);
	}

}
//In summary, the CartService class encapsulates the business logic related to carts.
//It interacts with the CartRepo to perform CRUD operations on carts. The actual database operations are delegated to the repository, and the service layer handles error responses and business logic.