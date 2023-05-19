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
    Integer ruleRunResultId;

    @OneToOne
    @JoinColumn(name = "rule_load_result")
    RuleLoadResultEntity ruleLoadResultEntity;

    @Column(name = "status")
    String status;
    
    
    @ManyToOne
    QPRunResultEntity qpRunResult;
}
