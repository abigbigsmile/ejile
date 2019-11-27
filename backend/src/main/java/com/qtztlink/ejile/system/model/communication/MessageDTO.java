package com.qtztlink.ejile.system.model.communication;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Tiger
 * @date 2019-11-26
 * @see com.qtztlink.ejile.system.model.communication
 **/
@Data
public class MessageDTO {
    @NotNull(message = "消息内容不能为空")
    String message;

    @NotNull(message = "消息发送者不能为空")
    String fromUser;

    @NotNull(message = "消息接受者不能为空")
    String toUser;

    @NotNull(message = "消息类型不能为空")
    Integer messageType;
}
