package com.br.bank.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_cargo")
    private String name;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

}
