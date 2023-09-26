package com.br.bank.entity;

import com.br.bank.enums.TypeOperation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "operations")
public class Operation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_operation")
    @Enumerated(EnumType.ORDINAL)
    private TypeOperation typeOperation;

    @Column(name = "agency")
    private Integer agency;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "value_operation")
    private BigDecimal valueOperation;

    @Column(name = "time_operation")
    private LocalDateTime timeOperation;

    @Column(name = "id_account", updatable = false, insertable = false)
    private Integer idAccount;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

}
