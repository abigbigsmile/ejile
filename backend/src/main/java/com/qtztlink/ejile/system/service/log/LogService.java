package com.qtztlink.ejile.system.service.log;

import com.qtztlink.ejile.common.bean.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

/**
 * @author Tiger
 * @date 2019-12-05
 * @see com.qtztlink.ejile.system.service.log
 **/
public interface LogService {
    List<SysLog> findAll();
    Page<SysLog> findByCondition(String keyword,String[] choseTime, Pageable pageable) throws ParseException;
}
