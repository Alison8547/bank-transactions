package com.br.bank.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "active")
    private Integer active;

    @Column(name = "id_client", updatable = false, insertable = false)
    private Integer idClient;

    @Column(name = "id_agency", updatable = false, insertable = false)
    private Integer idAgency;

    @ManyToOne
    @JoinColumn(name = "id_agency")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();

}
