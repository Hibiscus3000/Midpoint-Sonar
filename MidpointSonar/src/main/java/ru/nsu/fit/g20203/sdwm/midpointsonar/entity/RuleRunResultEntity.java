package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "rule_run_result")
@Entity
@Data
public class RuleRunResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Integer id;

    @OneToOne
    @JoinColumn(name = "rule_load_result")
    private RuleLoadResultEntity ruleLoadResultEntity;

    @Column(name = "status")
    private String status;


    @ManyToOne
    private QPRunResultEntity qpRunResult;
}
