package com.br.bank.entity;

import com.br.bank.enums.TypeOperation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Column(name = "value_operation")
    private BigDecimal valueOperation;

    @Column(name = "time_operation")
    private LocalDateTime timeOperation;

    @Column(name = "date_operation")
    private LocalDate dateOperation;

    @Column(name = "id_client_operation", updatable = false, insertable = false)
    private Integer idClientOperation;

    @ManyToOne
    @JoinColumn(name = "id_client_operation")
    private Client client;

    @Column(name = "id_account_destiny", updatable = false, insertable = false)
    private Integer idAccountDestiny;

    @ManyToOne
    @JoinColumn(name = "id_account_destiny")
    private Account account;

}
