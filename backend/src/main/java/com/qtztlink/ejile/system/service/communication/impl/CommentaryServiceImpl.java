package com.qtztlink.ejile.system.service.communication.impl;

import com.qtztlink.ejile.system.dao.communication.CommentaryDao;
import com.qtztlink.ejile.system.model.communication.Commentary;
import com.qtztlink.ejile.system.service.communication.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("commentaryService")
public class CommentaryServiceImpl implements CommentaryService {

    @Autowired
    private CommentaryDao commentaryDao;

    @Override
    public int add(Commentary commentary) {
        return this.commentaryDao.add(commentary);
    }

    @Override
    public int deleteCommentaryById(Integer coid) {
        return this.commentaryDao.deleteCommentaryById(coid);
    }

    @Override
    public int update(Commentary commentary) {
        return this.commentaryDao.update(commentary);
    }

    @Override
    public List<Map<String, Object>> queryAllCommentary() {
        return this.commentaryDao.queryAllCommentary();
    }

    @Override
    public List<Commentary> queryCommentaryBySID(Integer SID) {
        return this.commentaryDao.queryCommentaryBySID(SID);
    }

    @Override
    public Commentary queryCommentaryByCoID(Integer CoID) {
        return this.commentaryDao.queryCommentaryByCoID(CoID);
    }

    @Override
    public List<Commentary> queryCommentaryByUID(Integer uid) {
        return this.commentaryDao.queryCommentaryByUID(uid);
    }
}
