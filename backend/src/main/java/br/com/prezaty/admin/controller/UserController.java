package br.com.prezaty.admin.controller;

import br.com.prezaty.admin.dto.UserDTO;
import br.com.prezaty.admin.dto.UserRequestDTO;
import br.com.prezaty.admin.dto.UserResponseDTO;
import br.com.prezaty.admin.security.UserPrincipal;
import br.com.prezaty.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<UserDTO> list(Pageable pageable) {
        return userService.list(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserResponseDTO get(@PathVariable("id") Long id) {
        return userService.byId(id);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public UserPrincipal me(Authentication authentication) {
        return (UserPrincipal) authentication.getPrincipal();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UserResponseDTO add(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {
        return userService.save(userRequestDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserResponseDTO update(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable("id") Long id) throws Exception {
        UserRequestDTO.UserRequestDTOBuilder userRequestDTOBuilder = userRequestDTO.toBuilder();
        UserRequestDTO userRequestDTONew = userRequestDTOBuilder
                .id(id)
                .build();

        return userService.save(userRequestDTONew);
    }

}
