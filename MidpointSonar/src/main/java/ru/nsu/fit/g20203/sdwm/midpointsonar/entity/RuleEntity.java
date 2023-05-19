package ru.nsu.fit.g20203.sdwm.midpointsonar.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "rule", schema = "public", catalog = "postgres")
@Data
public class RuleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rule_id")
    Integer RuleId;

    @Basic
    @Column(name = "name")
    String name;

    @Basic
    @Column(name = "task_oid")
    String task_oid;
}
