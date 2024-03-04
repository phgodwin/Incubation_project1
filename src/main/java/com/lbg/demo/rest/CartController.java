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

//The @RestController annotation indicates that this class is a Spring MVC controller that handles incoming HTTP requests.
//Unlike a traditional @Controller, which returns views, a @RestController returns data (usually in JSON or XML format) directly to the client.
@RestController
//The @RequestMapping annotation specifies the base URL path for all endpoints defined in this controller.
//In this case, all endpoints in this controller will start with /cart.
@RequestMapping("/cart")
// The @CrossOrigin annotation allows cross-origin requests from different domains.
//It enables the controller to respond to requests from web pages hosted on other domains (useful for client-side applications).
@CrossOrigin

public class CartController {
//The constructor takes a single argument of type CartService.
//This is an example of dependency injection, where the CartService is injected into the controller.

//	The CartController defines several endpoint methods:
//		createCart(@RequestBody Cart newCart): Handles a POST request to create a new cart. The newCart object is received from the request body, and the CartService is used to create and return the cart.
//		getCarts(): Handles a GET request to retrieve a list of all carts. The CartService is used to fetch and return the list of carts.
//		getCart(@PathVariable int id): Handles a GET request to retrieve a specific cart by its id. The CartService is used to fetch the cart, and a ResponseEntity is returned (which allows customizing the HTTP response).
//		removeCart(@PathVariable int id): Handles a DELETE request to remove a cart by its id. The CartService is used to delete the cart, and a boolean indicating success or failure is returned.
//		(Commented out) updateCart(@PathVariable int id, @RequestBody Cart newCart): This method would handle a PATCH request to update an existing cart. However, it’s currently commented out.
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

//@PatchMapping("/update/{id}")
//public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart newCart) {
//	return this.service.updateCart(id, newCart);
//	}
}
