package acl.master1.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import acl.master1.user.dto.AclUserDTO;
import acl.master1.user.entity.AclUser;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AclUser.class})
public interface AclUserMapper {

	AclUserDTO convert(AclUser aclUser);
	
	List<AclUserDTO> convert(List<AclUser> aclUserS);
	
	AclUser convert(AclUserDTO aclUserDTO);
}
