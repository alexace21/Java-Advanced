package softuni.defense.project.model.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import softuni.defense.project.model.enums.UserRoleEnum;

@Entity
@Table(name = "roles")
public class UserRoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRoleEnum role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
