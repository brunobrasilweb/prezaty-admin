package br.com.prezaty.admin.service.mapper;

import br.com.prezaty.admin.dto.UserDTO;
import br.com.prezaty.admin.dto.UserRequestDTO;
import br.com.prezaty.admin.dto.UserResponseDTO;
import br.com.prezaty.admin.entity.User;
import br.com.prezaty.admin.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

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

    public User userRequestDTOToUser(UserRequestDTO userRequestDTO) {
        UserRequestDTO.UserRequestDTOBuilder userRequestDTOBuilder = userRequestDTO.toBuilder();
        UserRequestDTO userRequestDTONew = userRequestDTOBuilder.build();
        if (userRequestDTO.getPassword() != null && userRequestDTO.getId() == null) {
            userRequestDTONew = userRequestDTOBuilder.password(passwordEncoder.encode(userRequestDTO.getPassword())).build();
        } else if (userRequestDTO.getId() != null && userRequestDTO.getNewPassword() != null)  {
            userRequestDTONew = userRequestDTOBuilder.password(passwordEncoder.encode(userRequestDTO.getNewPassword())).build();
        } else if (userRequestDTO.getId() != null) {
            User user = userRepository.getOne(userRequestDTO.getId());
            userRequestDTONew = userRequestDTOBuilder.password(user.getPassword()).build();
        }

        return modelMapper.map(userRequestDTONew, User.class);
    }

    public UserRequestDTO userToUserFormDTO(User user) {
        User.UserBuilder userBuilder = user.toBuilder();
        User userNew = userBuilder.password(null).build();
        return modelMapper.map(userNew, UserRequestDTO.class);
    }

    public UserResponseDTO userToUserResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
