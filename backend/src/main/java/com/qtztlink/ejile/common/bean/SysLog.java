package com.qtztlink.ejile.common.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "sys_log")
@EntityListeners(AuditingEntityListener.class)
public class SysLog implements Serializable {
    private static final long serialVersionUID = 6719491607778976407L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "operation")
    private String operation;
    @Column(name = "use_time")
    private Integer time;
    @Column(name = "method")
    private String method;
    @Column(name = "params")
    private String params;
    @Column(name = "ip")
    private String ip;
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;
}
