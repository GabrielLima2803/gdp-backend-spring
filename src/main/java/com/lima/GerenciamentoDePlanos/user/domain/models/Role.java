package com.lima.GerenciamentoDePlanos.user.domain.models;

public class Role {
    private final Long id;
    private String name;

    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        long id;

        Values(long id) {
            this.id = Values.this.id;
        }

        public long getId() {
            return id;
        }
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
