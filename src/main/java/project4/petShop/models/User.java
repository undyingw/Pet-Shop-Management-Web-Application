package project4.petShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project4.petShop.util.UserRoles;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    @NotEmpty(message = "login must be filled")
    @Size(min = 3, max = 20 , message = "login must be from 3 to 20 characters")
    private String login;

    @NotEmpty(message = "password must be filled")
    @Size(min = 3, max = 20 , message = "password must be from 3 to 20 characters")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "please fill your full name in")
    @Size(min = 5, max = 100 , message = "full name must be from 6 to 100 characters")
    @Column(name = "full_name")
    private String fullName;

    @Email
    @Column(name = "email")
    @NotEmpty(message = "please fill your email in")
    @Size(min = 5, max = 30 , message = "email must be from 5 to 30 characters")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoles role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    @Builder
    public User(String login, String password, String fullName, String email, UserRoles role) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}
