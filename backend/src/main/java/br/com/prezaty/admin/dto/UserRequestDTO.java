package br.com.prezaty.admin.dto;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder(toBuilder = true)
public class UserRequestDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @Size(min = 4, max = 30)
    private String password;
    @Size(min = 4, max = 30)
    private String newPassword;

}
