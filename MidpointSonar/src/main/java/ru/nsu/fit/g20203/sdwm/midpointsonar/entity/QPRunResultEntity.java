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
    private Integer id;

    @OneToMany
    @JoinColumn(name = "qp_run_result_id")
    private List<RuleRunResultEntity> ruleRunResultEntityList;

    @OneToOne
    private QPOperationResultEntity qpOperationResultEntity;
}
