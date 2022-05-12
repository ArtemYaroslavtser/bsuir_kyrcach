package com.example.Kyrcach_java_Yaroslavtser.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class First_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public First_Entity(Long id) {
        this.id =id;
    }

    public First_Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("First_Entity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
