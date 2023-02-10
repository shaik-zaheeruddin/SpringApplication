package com;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Autowired
  private PersonRepo personRepo;
	@GetMapping("/")
public List greet() {
		return personRepo.findAll();
}
}
