package com.qtztlink.ejile.system.dao.communication.impl;

import com.qtztlink.ejile.system.dao.communication.ContactDao;
import com.qtztlink.ejile.system.mapper.communication.ContactMapper;
import com.qtztlink.ejile.system.model.communication.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository("contactDaoImpl")
public class ContactDaoImpl  {
//
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryAllContact() { //补充后台查询所有的聊天记录
        String sql = "select * from contact";
        return this.jdbcTemplate.queryForList(sql);
    }
//
//    @Override
//    public int add(Contact contact) {
//        String sql = "insert into contact(cid, sid, ctime, content, cstate) " +
//                "values (?, ?, ?, ?, ?);";
//        Object[] args = {
//                contact.getCid(),
//                contact.getSid(),
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contact.getTime()),
//                contact.getContent(),
//                contact.getState()
//        };
//        int[] argTypes = {
//                Types.INTEGER,
//                Types.INTEGER,
//                Types.VARCHAR,
//                Types.VARCHAR,
//                Types.VARCHAR
//        };
//        return this.jdbcTemplate.update(sql, args, argTypes);
//    }
//
//    @Override
//    public List<Contact> queryContactBySIDCID(Integer SID, Integer CID) {
//        String sql = "select * from contact_view where sid = ? and cid = ?";
//        Object[] args = {
//                SID,
//                CID
//        };
//        int[] argTypes = {
//                Types.INTEGER,
//                Types.INTEGER
//        };
//        List<Contact> contactList = this.jdbcTemplate.query(sql, args, argTypes,
//                new ContactMapper());
//        if(contactList != null && contactList.size() > 0){
//            return contactList;
//        }
//        else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Contact> queryContactBySID(Integer SID) {
//        String sql = "select * from contact_view where sid = ? order by ctime";
//        Object[] args = {
//                SID
//        };
//        int[] argTypes = {
//                Types.INTEGER
//        };
//        List<Contact> contactList = this.jdbcTemplate.query(sql, args, argTypes,
//                new ContactMapper());
//        if(contactList != null && contactList.size() > 0){
//            return contactList;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Contact> queryContactByCID(Integer CID) {
//        String sql = "select * from contact_view where cid = ? order by ctime";
//        Object[] args = { CID };
//        int[] argTypes = { Types.INTEGER };
//        List<Contact> contactList = this.jdbcTemplate.query(sql, args, argTypes,
//                new ContactMapper());
//        if(contactList != null && contactList.size() > 0){
//            return contactList;
//        } else {
//            return null;
//        }
//    }
}
