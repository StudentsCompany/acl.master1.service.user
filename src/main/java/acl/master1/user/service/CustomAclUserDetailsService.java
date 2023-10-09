package acl.master1.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import acl.master1.user.config.CustomAclUserDetails;
import acl.master1.user.entity.AclUser;
import acl.master1.user.repository.AclUserRepository;

@Component
public class CustomAclUserDetailsService implements UserDetailsService {
	
	/**
	 * This class is used by the authentication manager because he can not
	 * access the database. So we create this class to do it. 
	 * This class help us to verify if a user exist in  our database before giving him a token
	 */
	
	@Autowired
	private AclUserRepository aclUserRepository;
	
	/**
	 * 
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AclUser user = aclUserRepository.findByUsername(username);
		
		 // Convertion 
		
		if(user == null)
			throw new UsernameNotFoundException("User with username = " + username + " is not found");
		
		CustomAclUserDetails res = new CustomAclUserDetails(user);
		
		return res;
	}

}
