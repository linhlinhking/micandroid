package ningbo.media.util;

import java.util.ArrayList;
import java.util.List;

import ningbo.media.domain.SystemUser;
import ningbo.media.response.UserDto;

import org.springframework.data.domain.Page;

public class UserMapper {

	public static UserDto map(SystemUser user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setUsername(user.getUsername());
		dto.setRole(user.getRole().getRole());
		return dto;
	}

	public static List<UserDto> map(Page<SystemUser> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (SystemUser user : users) {
			dtos.add(map(user));
		}
		return dtos;
	}
}
