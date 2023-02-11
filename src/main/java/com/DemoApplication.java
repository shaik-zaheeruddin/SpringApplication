package com;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
   @PostMapping("/")
   public String posting(@RequestBody NewPerson newPerson){
		Person person = new Person();
		person.setName(newPerson.name());
		person.setAge(newPerson.age());
		person.setEmail(newPerson.email());
		personRepo.save(person);
		return "DONE";
   }
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		// Code to delete a user
		personRepo.deleteById(id);
		return "Done";
	}
	@PutMapping("/{id}")
	public Object updateUser(@PathVariable("id") Integer id,@RequestBody Map mp){
		Optional<Person> person = personRepo.findById(id);
	    if(person.isPresent()){
	    	if(mp.containsKey("name")){
              person.get().setName((String) mp.get("name"));
			}
	    	if(mp.containsKey("email")){
				person.get().setEmail((String) mp.get("email"));
			}
	    	if(mp.containsKey("age")){
				person.get().setAge((Integer) mp.get("age"));
			}
	        //so when we same the same object it will automatically get saved...
			//when we are saving it will look for the id and it will update i think
	    	return personRepo.save(person.get());
		}
	    return "Person does not exist";
	}

  public record NewPerson(String name,String email,Integer age){}

}
