package com.qtztlink.ejile.system.mapper.communication;

import com.qtztlink.ejile.common.utils.DateUtil;
import com.qtztlink.ejile.system.model.communication.ShopReply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopReplyMapper implements RowMapper<ShopReply> {

    @Override
    public ShopReply mapRow(ResultSet resultSet, int i) throws SQLException {
        ShopReply shopReply = new ShopReply();
        shopReply.setSid(resultSet.getInt("sid"));
        shopReply.setCoid(resultSet.getInt("coid"));
        shopReply.setTime(DateUtil.formatTimeStamp(resultSet.getTimestamp("cotime")));
        shopReply.setContent(resultSet.getString("content"));
        shopReply.setSname(resultSet.getString("sname"));
        return shopReply;
    }
}
