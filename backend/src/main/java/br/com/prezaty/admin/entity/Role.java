package br.com.prezaty.admin.entity;

import br.com.prezaty.admin.enums.RoleNameEnum;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder(toBuilder = true)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column
    private RoleNameEnum name;

}
