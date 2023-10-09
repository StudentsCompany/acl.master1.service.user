package acl.master1.user.dto;

import java.util.Date;
import java.util.Set;

import acl.master1.user.enums.Role;
import lombok.Data;

@Data
public class AclUserDTO {


	Integer idAclUser;
	
	String username;
	
	String email;
	
	String firstname;
	
	String lastname;
	
	Date birthdate;
	
	Date registrationDate;
	
	String password;
	
	Set<Role> roles;
}
