package com.lbg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.demo.domain.Cart;
import com.lbg.demo.repo.CartRepo;

@Service

public class CartService {
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
