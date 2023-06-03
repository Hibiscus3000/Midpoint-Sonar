package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "qp_operation_result")
@Entity
@Data
public class QPOperationResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "qp_op_result_id")
    private Integer id;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "qp_id")
    private QualityProfileEntity qualityProfileEntity;
}
