package acl.master1.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import acl.master1.user.dto.AclUserDTO;
import acl.master1.user.entity.AclUser;
import acl.master1.user.mapper.AclUserMapper;
import acl.master1.user.repository.AclUserRepository;

@Service
public class AclUserService {

	@Autowired
	private AclUserRepository aclUserRepository;
	
	@Autowired
	private AclUserMapper aclUserMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	
	
	public AclUserDTO findById(Integer id) {		
		return this.aclUserMapper.convert(this.aclUserRepository.findByIdAclUser(id));
	}
	
	public List<AclUserDTO> findAll(){
		return this.aclUserMapper.convert(this.aclUserRepository.findAll());
	} 
	 
	/*
	 * These 3 following methods are very important 
	 * 
	 */
	
	public AclUserDTO save(AclUserDTO uclUserDTO) {
		AclUser aux = aclUserMapper.convert(uclUserDTO);
		aux.setPassword(passwordEncoder.encode(aux.getPassword()));
		return aclUserMapper.convert(aclUserRepository.save(aux));
	}
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}
