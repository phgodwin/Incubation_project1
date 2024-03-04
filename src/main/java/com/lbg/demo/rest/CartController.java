package com.lbg.demo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.demo.domain.Cart;
import com.lbg.demo.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin

public class CartController {

	private CartService service;

	public CartController(CartService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public Cart createCart(@RequestBody Cart newCart) {
		return this.service.createCart(newCart);
	}

	@GetMapping("/get")
	public List<Cart> getCarts() {
		return this.service.getCarts();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Cart> getCart(@PathVariable int id) {
		return this.service.getCart(id);
	}

	@DeleteMapping("/delete/{id}")
	public boolean removeCart(@PathVariable int id) {
		return this.service.removeCart(id);
	}

//	@PatchMapping("/update/{id}")
//	public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart newCart) {
//		return this.service.updateCart(id, newCart);
//	}
}
