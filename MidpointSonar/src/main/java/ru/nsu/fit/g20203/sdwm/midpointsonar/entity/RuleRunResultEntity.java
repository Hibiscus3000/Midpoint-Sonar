package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;

import jakarta.persistence.*;
import lombok.Data;

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

//    @ManyToMany
//    private List<MidPointSonarObject> midPointSonarObjects;
}
