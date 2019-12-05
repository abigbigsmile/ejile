package com.qtztlink.ejile.system.model.communication;

import lombok.Data;
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

/**
 * @author Tiger
 * @date 2019-10-29
 * @see com.qtztlink.ejile.system.model.communication
 **/
@Data
@Entity
@Table(name = "message_contact")
@EntityListeners(AuditingEntityListener.class)
public class MessageContact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * 消息内容
     **/
    @Column(name = "message", columnDefinition = "text")
    String message;
    /**
     * 谁发送的
     **/
    @Column(name = "from_user", columnDefinition = "int(10)")
    String fromUser;

    @Column(name = "from_id")
    Long fromId;
    /**
     * 发给谁
     **/
    @Column(name = "to_user", columnDefinition = "int(10)")
    String toUser;

    @Column(name = "to_id")
    Long toId;
    /**
     * 消息类型
     * 1代表商家发送给用户 2代表用户发给商家 3代表新订单信息
     **/
    @Column(name = "message_type", columnDefinition = "int(1)")
    Integer messageType;

    /**
     * 状态
     * 1代表未被消费 0代表已被消费
     **/
    @Column(name = "status", columnDefinition = "int(1)")
    Integer status;

    @CreatedDate
    @Column(name = "create_time")
    Date time;
}
