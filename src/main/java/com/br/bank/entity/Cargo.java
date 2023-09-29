package com.br.bank.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
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
public class Cargo implements Serializable, GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_cargo")
    private String name;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name;
    }
}
