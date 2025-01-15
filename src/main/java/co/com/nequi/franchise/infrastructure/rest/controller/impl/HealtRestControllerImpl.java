package co.com.nequi.franchise.infrastructure.rest.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;

@Getter
@RestController
@CrossOrigin("*")
public class HealtRestControllerImpl {

	@GetMapping("/")
	public ResponseEntity<String> healt() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}