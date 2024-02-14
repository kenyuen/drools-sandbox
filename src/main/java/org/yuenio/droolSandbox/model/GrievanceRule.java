package org.yuenio.droolSandbox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grievance_rule_table")
@Getter
@Setter
public class GrievanceRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "category")
    private String category;
    @Column(name = "serviceType")
    private String serviceType;
    @Column(name = "version")
    private int version;
}
