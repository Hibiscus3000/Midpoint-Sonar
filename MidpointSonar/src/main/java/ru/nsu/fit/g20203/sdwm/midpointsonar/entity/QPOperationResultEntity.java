package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "qp_operation_result")
@Entity
@Data
public class QPOperationResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "qp_op_result_id")
    Integer QPOpResultId;

    @Column(name = "status")
    String status;

    @ManyToOne
    @JoinColumn(name = "qp_id")
    QualityProfileEntity qualityProfileEntity;
}
