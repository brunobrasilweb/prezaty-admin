package br.com.prezaty.admin.controller;

import br.com.prezaty.admin.dto.UserDTO;
import br.com.prezaty.admin.dto.UserFormDTO;
import br.com.prezaty.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<UserDTO> lista(Pageable pageable) {
        return userService.list(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserFormDTO get(@PathVariable("id") Long id) {
        return userService.byId(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UserFormDTO add(@RequestBody UserFormDTO userFormDTO) {
        return userService.save(userFormDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserFormDTO update(@RequestBody UserFormDTO userFormDTO, @PathVariable("id") Long id) {
        UserFormDTO.UserFormDTOBuilder userFormDTOBuilder = userFormDTO.toBuilder();
        UserFormDTO userFormDTONew = userFormDTOBuilder
                .id(id)
                .build();

        return userService.save(userFormDTONew);
    }

}
