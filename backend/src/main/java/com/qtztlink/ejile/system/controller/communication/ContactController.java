package com.qtztlink.ejile.system.controller.communication;

import com.qtztlink.ejile.common.bean.ResponseBean;
import com.qtztlink.ejile.common.exception.UnauthorizedException;
import com.qtztlink.ejile.system.model.communication.Contact;
import com.qtztlink.ejile.system.model.user.Consumer;
import com.qtztlink.ejile.system.model.user.Shop;
import com.qtztlink.ejile.system.service.communication.ContactService;
import com.qtztlink.ejile.system.service.user.ConsumerService;
import com.qtztlink.ejile.system.service.user.ShopService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Validated
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/contact/getInfoBySidCid")
    @ResponseBody
    public ResponseBean getContactInfoBySidCid(int sid, int cid) {
        List<Contact> contactList = this.contactService.queryContactBySIDCID(sid, cid);
        if (contactList == null) {
            contactList=new ArrayList<>();
            Shop shop = shopService.queryShopById(sid);
            Consumer consumer = consumerService.queryConsumerById(cid);
            Contact contact = new Contact();
            contact.setSid(shop.getId());
            contact.setContent("你正在与商家：" + shop.getUsername() + "交谈");
            contact.setSname(shop.getUsername());
            contact.setCid(consumer.getId());
            contact.setTime(new Date());
            contact.setCname(consumer.getUsername());
            contact.setState("0");
            contactList.add(contact);
        }
        return new ResponseBean().code(200)
                .message("SUCCESS")
                .data(contactList);
    }

    @RequestMapping("/contact/getInfoBySid")
    @ResponseBody
    public ResponseBean getContactInfoBySid(@RequestParam("uid") int sid) {
        List<Contact> contactList = this.contactService.queryContactBySID(sid);
        if (contactList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(contactList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not find Goods")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/contact/getInfoByCid")
    @ResponseBody
    public ResponseBean getContactInfoByCid(@RequestParam("uid") int cid) {
        List<Contact> contactList = this.contactService.queryContactByCID(cid);
        if (contactList != null) {
            return new ResponseBean().code(200)
                    .message("SUCCESS")
                    .data(contactList);
        } else {
            return new ResponseBean().code(200)
                    .message("Not find Goods")
                    .data(new ArrayList<>());
        }
    }

    @RequestMapping("/contact/add")
    @ResponseBody
    public ResponseBean addContact(int cid,
                                   int sid,
                                   Date ctime,
                                   @Pattern(regexp = "^[01]$", message = "{invalid}") String state, // 0为用户发，1为商家发
                                   @Length(min = 1, max = 1024, message = "{range}") String content
    ) throws Exception {
        Contact contact = new Contact();
        contact.setCid(cid);
        contact.setSid(sid);
        contact.setTime(ctime);
        contact.setState(state);
        contact.setContent(content);
        int res = this.contactService.add(contact);
        if (res == 1) {
            return new ResponseBean()
                    .code(200)
                    .message("Success")
                    .data(1);
        } else {
            throw new UnauthorizedException("发送失败，请联系管理员");
        }
    }
}
