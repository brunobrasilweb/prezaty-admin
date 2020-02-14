package br.com.prezaty.admin.service.mapper;

import br.com.prezaty.admin.dto.UserDTO;
import br.com.prezaty.admin.dto.UserFormDTO;
import br.com.prezaty.admin.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO userToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User userDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public List<UserDTO> userListToUserDTOList(List<User> userList) {
        return userList.stream()
                .filter(Objects::nonNull)
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<User> userDTOListToUserList(List<UserDTO> userDTOList) {
        return userDTOList.stream()
                .filter(Objects::nonNull)
                .map(this::userDTOToUser)
                .collect(Collectors.toList());
    }

    public User userFormDTOToUser(UserFormDTO userFormDTO) {
        return modelMapper.map(userFormDTO, User.class);
    }

    public UserFormDTO userToUserFormDTO(User user) {
        return modelMapper.map(user, UserFormDTO.class);
    }
}
