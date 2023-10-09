package acl.master1.user.entity;

import java.util.Date;

import java.util.Set;

import acl.master1.user.enums.Role;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AclUser {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	Integer idAclUser;
	
	@Column
	String username;
	
	@Column
	String email;
	
	@Column
	String firstname;
	
	@Column
	String lastname;
	
	@Column
	Date birthdate;
	
	@Column
	Date registrationDate;
	
	@Column 
	String password;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	Set<Role> roles;
	
	
	
}
