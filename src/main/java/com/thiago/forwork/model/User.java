package com.thiago.forwork.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
@Table(name = "fwk_user")
public class User extends BaseModel {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "asignee", fetch = FetchType.LAZY)
    @Builder.Default
    Set<Task> tasks = new HashSet<>();

}
