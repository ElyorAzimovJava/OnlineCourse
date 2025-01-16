package org.example.onlinecourse.mapper;

import org.example.onlinecourse.dto.authDto.RegisterUserDto;
import org.example.onlinecourse.dto.authDto.UserResponseDto;
import org.example.onlinecourse.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
   User toUser(RegisterUserDto registerUserDto);
   UserResponseDto toResponse(User user);
}
