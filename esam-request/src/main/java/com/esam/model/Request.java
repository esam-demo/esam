package com.esam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Date;

import static com.esam.constant.DateConst.DATETIME_FORMAT;

/**
 * @author trutao
 * @create 2020-11-21 21:41
 */
@Data
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "requests", indexes = {
        @Index(name = "idx_requests_resource_id", columnList = "resource_id"),
        @Index(name = "idx_requests_created_by", columnList = "created_by"),
        @Index(name = "idx_requests_requested_for", columnList = "requested_for")
})
public class Request extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requested_for", nullable = false)
    private String requestedFor;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "justification", nullable = false)
    private String justification;

    @Column(name = "resource_id", nullable = false)
    private String resourceId;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "source")
    private String source;

    @Column(name = "comment")
    private String comment;


    /**
     * Builder mode
     */
    public Request withId(Long id) {
        this.setId(id);
        return this;
    }

    public Request withRequestedFor(String requestedFor) {
        this.setRequestedFor(requestedFor);
        return this;
    }

    public Request withResourceId(String resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public Request withResourceName(String resourceName) {
        this.setResourceName(resourceName);
        return this;
    }

    public Request withType(String type) {
        this.setType(type);
        return this;
    }

    public Request withStatus(String requestStatus) {
        this.setStatus(requestStatus);
        return this;
    }

    public Request withJustification(String justification) {
        this.setJustification(justification);
        return this;
    }

    public Request withResourceType(String resourceType) {
        this.setResourceType(resourceType);
        return this;
    }

    public Request withSource(String source) {
        this.setSource(source);
        return this;
    }

    public Request withComment(String comment) {
        this.setComment(comment);
        return this;
    }

}
