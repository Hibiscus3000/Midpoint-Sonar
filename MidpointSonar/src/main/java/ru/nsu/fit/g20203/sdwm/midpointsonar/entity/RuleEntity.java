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
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "task_oid")
    private String task_oid;
}
