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
    Integer ruleLoadResult;

    @Column(name = "status")
    String status;

    @OneToOne
    @JoinColumn(name = "rule_id")
    RuleEntity rule;
}
