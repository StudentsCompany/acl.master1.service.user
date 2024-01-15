package acl.master1.user.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acl.master1.user.dto.AclUserDTO;
import acl.master1.user.entity.AclUser;

@Repository
public interface AclUserRepository extends CrudRepository<AclUser, Integer> {

	AclUser findByIdAclUser(Integer id);
	
	List<AclUser> findAll();

	AclUser findByUsername(String username);
}
