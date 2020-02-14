package br.com.prezaty.admin.service;

import br.com.prezaty.admin.dto.UserDTO;
import br.com.prezaty.admin.dto.UserFormDTO;
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
    public UserFormDTO save(UserFormDTO userFormDTO) {
        User user = userMapper.userFormDTOToUser(userFormDTO);
        user = userRepository.save(user);

        return userMapper.userToUserFormDTO(user);
    }

    public UserFormDTO byId(Long id) {
        User user = userRepository.getOne(id);
        return userMapper.userToUserFormDTO(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
