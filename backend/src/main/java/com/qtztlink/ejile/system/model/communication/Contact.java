package com.qtztlink.ejile.system.model.communication;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 8825018844858153935L;

    public static final String CONSUMER_SEND = "0";
    public static final String SHOP_SEND = "1";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cid")
    private Integer cid;
    @Column(name = "sid")
    private Integer sid;
    @Column(name = "c_name")
    private String cname;
    @Column(name = "s_name")
    private String sname;

    @Column(name = "c_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    @Column(name = "content")
    private String content;

    @Column(name = "c_state")
    private String state;

    @Column(name = "is_read")
    private String isRead;
}
