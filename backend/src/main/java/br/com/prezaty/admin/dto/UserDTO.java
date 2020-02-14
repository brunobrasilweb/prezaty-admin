package br.com.prezaty.admin.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder(toBuilder = true)
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

}
