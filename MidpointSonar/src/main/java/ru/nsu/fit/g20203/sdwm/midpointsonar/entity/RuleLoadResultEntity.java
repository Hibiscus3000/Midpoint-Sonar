package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "rule_load_result")
@Entity
@Data
public class RuleLoadResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rule_load_result_id")
    private Integer id;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "rule_id")
    private RuleEntity rule;
}
