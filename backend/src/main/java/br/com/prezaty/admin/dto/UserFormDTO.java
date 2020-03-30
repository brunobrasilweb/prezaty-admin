package br.com.prezaty.admin.dto;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder(toBuilder = true)
public class UserFormDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @Size(min = 6, max = 30)
    private String password;

}
