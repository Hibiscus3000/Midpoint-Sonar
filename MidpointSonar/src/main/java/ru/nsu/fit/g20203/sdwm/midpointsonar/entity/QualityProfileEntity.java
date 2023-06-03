package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "quality_profile", schema = "public", catalog = "postgres")
@Entity
@Data
public class QualityProfileEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "qp_id")
    private Integer id;

    @Basic
    @Column(name = "qp_name")
    private String name;

    @ManyToMany/*(fetch = FetchType.LAZY, cascade = CascadeType.ALL)*/
    private List<RuleEntity> rules;
}
