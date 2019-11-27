package com.qtztlink.ejile.system.dao.communication;

import com.qtztlink.ejile.system.model.communication.MessageContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Tiger
 * @date 2019-11-26
 * @see com.qtztlink.ejile.system.dao.communication
 **/
public interface MessageDao extends JpaRepository<MessageContact, Integer>, JpaSpecificationExecutor<MessageContact> {
}
