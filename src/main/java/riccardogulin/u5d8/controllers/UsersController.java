package riccardogulin.u5d8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardogulin.u5d8.entities.User;
import riccardogulin.u5d8.payloads.NewUserPayload;
import riccardogulin.u5d8.services.UsersService;

import java.util.UUID;

/*

1. GET http://localhost:3001/users
2. POST http://localhost:3001/users (+req.body)
3. GET http://localhost:3001/users/{userId}
4. PUT http://localhost:3001/users/{userId} (+req.body)
5. DELETE http://localhost:3001/users/{userId}

*/

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping
	public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
	                          @RequestParam(defaultValue = "10") int size,
	                          @RequestParam(defaultValue = "id") String sortBy
	) {
		// Posso mettere dei valori di default per i query params, per far si che non ci siano errori se il client non li imposta
		return this.usersService.findAll(page, size, sortBy);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody NewUserPayload payload) {

		return this.usersService.save(payload);
	}

	@GetMapping("/{userId}")
	public User getById(@PathVariable UUID userId) {
		return this.usersService.findById(userId);
	}

	@PutMapping("/{userId}")
	public User getByIdAndUpdate(@PathVariable UUID userId, @RequestBody NewUserPayload payload) {
		return this.usersService.findByIdAndUpdate(userId, payload);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void getByIdAndDelete(@PathVariable UUID userId) {
		this.usersService.findByIdAndDelete(userId);
	}
}
