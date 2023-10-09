package acl.master1.user.config;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import acl.master1.user.dto.AclUserDTO;
import acl.master1.user.entity.AclUser;

public class CustomAclUserDetails implements UserDetails {

	private String username;
	private String password; // Dois-je tout mapper dans le DTO ?
	
	public CustomAclUserDetails(AclUser aclUser) {
		this.username = aclUser.getUsername();
		this.password = aclUser.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
