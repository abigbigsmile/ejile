package com.qtztlink.ejile.system.service.communication.impl;

import com.qtztlink.ejile.system.dao.communication.MessageDao;
import com.qtztlink.ejile.system.model.communication.MessageContact;
import com.qtztlink.ejile.system.service.communication.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2019-11-26
 * @see com.qtztlink.ejile.system.service.communication.impl
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageDao messageDao;
    @Override
    public void addMessage(MessageContact messageContact) {
        messageDao.save(messageContact);
    }
}
