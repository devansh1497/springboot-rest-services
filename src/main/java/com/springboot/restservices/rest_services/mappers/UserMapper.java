package com.springboot.restservices.rest_services.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.springboot.restservices.rest_services.dtos.UserMsDto;
import com.springboot.restservices.rest_services.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDto
	@Mapping(source="id", target="userId")
	UserMsDto userToUserDto(User user);
	
	//List<User> to List<UserMsDto>
	List<UserMsDto> usersToUserDtos(List<User> users);
}
