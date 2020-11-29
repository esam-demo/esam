package com.esam.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author trutao
 * @create 2020-11-26 16:59
 */
@Data
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "resources", indexes = {
        @Index(name = "idx_resources_name", columnList = "name"),
        @Index(name = "idx_resources_created_by", columnList = "created_by"),
        @Index(name = "idx_resources_source", columnList = "source")
})
public class Resource extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "source", nullable = false)
    private String source;


}
