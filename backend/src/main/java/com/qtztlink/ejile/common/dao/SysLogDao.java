package com.qtztlink.ejile.common.dao;

import com.qtztlink.ejile.common.bean.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Map;

public interface SysLogDao extends JpaRepository<SysLog, Integer>, JpaSpecificationExecutor<SysLog> {
}
