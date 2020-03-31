package br.com.prezaty.admin.service;

import br.com.prezaty.admin.dto.UserDTO;
import br.com.prezaty.admin.dto.UserRequestDTO;
import br.com.prezaty.admin.dto.UserResponseDTO;
import br.com.prezaty.admin.entity.User;
import br.com.prezaty.admin.repository.UserRepository;
import br.com.prezaty.admin.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public Page<UserDTO> list(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        int totalElements = (int) userPage.getTotalElements();

        return new PageImpl<UserDTO>(userPage.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList()), pageable, totalElements);
    }

    @Transactional
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        User user = userMapper.userRequestDTOToUser(userRequestDTO);
        user = userRepository.save(user);

        return userMapper.userToUserResponseDTO(user);
    }

    public UserResponseDTO byId(Long id) {
        User user = userRepository.getOne(id);
        return userMapper.userToUserResponseDTO(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
