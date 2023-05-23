package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "qp_run_result")
@Entity
@Data
public class QPRunResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "qp_run_result")
    Integer qpRunResult;

    @OneToMany
    @JoinColumn(name = "qp_run_result_id")
    List<RuleRunResultEntity> ruleRunResultEntityList;

    @OneToOne
    QPOperationResultEntity qpOperationResultEntity;
}
