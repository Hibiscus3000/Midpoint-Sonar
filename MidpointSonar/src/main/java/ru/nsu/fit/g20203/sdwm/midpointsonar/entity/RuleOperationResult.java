package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "rule_operation_result")
@Entity
@Data
public class RuleOperationResult {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rule_op_result")
    Integer RuleOpResult;

    @Basic
    @Column(name = "status")
    String status;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    RuleEntity ruleEntity;
}
