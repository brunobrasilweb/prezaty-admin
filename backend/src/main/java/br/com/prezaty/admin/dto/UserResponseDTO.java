package br.com.prezaty.admin.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder(toBuilder = true)
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;

}
