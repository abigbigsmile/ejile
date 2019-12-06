package com.qtztlink.ejile.system.service.communication.impl;

import com.qtztlink.ejile.system.dao.communication.ContactDao;
import com.qtztlink.ejile.system.model.communication.Contact;
import com.qtztlink.ejile.system.service.communication.ContactService;
import com.qtztlink.ejile.system.service.user.ConsumerService;
import com.qtztlink.ejile.system.service.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    @Resource
    private ContactDao contactDao;
    @Resource
    private ConsumerService consumerService;
    @Resource
    private ShopService shopService;

    @Override
    public Contact add(Contact contact) {
        contact.setCname(consumerService.queryConsumerById(contact.getCid()).getUsername());
        contact.setSname(shopService.queryShopById(contact.getSid()).getUsername());
        contact.setIsRead("0");
        return contactDao.save(contact);
    }

    @Override
    public List<Contact> queryContactBySIDCID(Integer SID, Integer CID) {
        return contactDao.findAllBySidEqualsAndCidEquals(SID, CID);
    }

    @Override
    public List<Contact> queryContactBySID(Integer SID) {
        return contactDao.findAllBySidEquals(SID);
    }

    @Override
    public List<Contact> queryContactByCID(Integer CID) {
        return contactDao.findAllByCidEquals(CID);
    }
}
