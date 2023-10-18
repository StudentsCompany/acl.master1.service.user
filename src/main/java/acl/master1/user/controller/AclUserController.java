package acl.master1.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import acl.master1.user.dto.AclUserDTO;
import acl.master1.user.dto.TokenDTO;
import acl.master1.user.service.AclUserService;

/**
 * @CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:8081", "http://localhost:4200", "http://localhost:8888"},
        methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
 */
 
@RequestMapping({"/user"})
@RestController
public class AclUserController {
	
	@Autowired
	private AclUserService aclUserService;
	
	@Autowired
	private AuthenticationManager authenticationManager; // We need this because if we want to create a token, we have to check if the user is in our database before.
	
	
	@GetMapping({"/{id}"})
	public @ResponseBody
	AclUserDTO findById(@PathVariable("id") Integer id) {
		return this.aclUserService.findById(id);
	}
	
	@GetMapping({"", "/"})
	public @ResponseBody 
	List<AclUserDTO> findAll(){
		return this.aclUserService.findAll();
	}
	
	@PostMapping("/register")
	@PutMapping("/register")
	public @ResponseBody
	AclUserDTO save(@RequestBody AclUserDTO uclUserDTO) {
		System.out.println("I recieve a save request");
		return aclUserService.save(uclUserDTO);
	}

	@PostMapping("/token")
	public @ResponseBody
	TokenDTO getToken(@RequestBody AclUserDTO aclUserDTO) {
		System.out.println("I recieve a post token request");
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(aclUserDTO.getUsername(), aclUserDTO.getPassword()));
		if(auth.isAuthenticated())
			return new TokenDTO(aclUserService.generateToken(aclUserDTO.getUsername()));
		else
			throw new IllegalArgumentException("Token Error");
	}
	
	@GetMapping("/validateToken")
	public String validateToken(@RequestParam("token") String token) {
		aclUserService.validateToken(token);
		
		return "Token is validate";
	}
}
