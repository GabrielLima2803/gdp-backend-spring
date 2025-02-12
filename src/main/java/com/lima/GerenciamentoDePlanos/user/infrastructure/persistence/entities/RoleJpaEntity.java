package com.lima.GerenciamentoDePlanos.user.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String name;
//    @ManyToMany(mappedBy = "roles")
//    private Set<UserJpaEntity> users = new HashSet<>();

    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        long id;

        Values(long id) {
            this.id = id;
        }

        public long getRoleId() {
            return id;
        }
    }
    public  RoleJpaEntity() {}

    public RoleJpaEntity(Long id, String name) {
        this.id = id;
        this.name = name;
//        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<UserJpaEntity> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<UserJpaEntity> users) {
//        this.users = users;
//    }
}
