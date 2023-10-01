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
@Table(name = "agency")
public class Agency {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_agency")
    private Integer numberAgency;

    @Column(name = "name_agency")
    private String nameAgency;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private List<Operation> operationList = new ArrayList<>();
}
