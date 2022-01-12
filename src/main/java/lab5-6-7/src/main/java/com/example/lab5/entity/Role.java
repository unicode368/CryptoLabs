package com.example.lab5.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @Getter
    private Long id;
    @Column(name = "role", unique = true)
    @NotNull
    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Collection<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return name;
    }

}
