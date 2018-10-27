package com.test;

import com.test.dao.MsgDao;

/**
 * Create by Guolianxing on 2018/10/26.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        HikariUtil.init();

        MsgDao.insertMsg("adfasdfasdfs", "tony");
        MsgDao.saveMsg("asdfmk;adf", "jony");
        MsgDao.updateMsg("haha", 2);
        MsgDao.deleteMsg(5);
        MsgDao.executeUpdate("abcde");
        MsgDao.selectSingleRes(3);
        MsgDao.selectSingleRes2(3);
        MsgDao.selectList();
        MsgDao.selectMsg(6);
        MsgDao.selectMapList();
        MsgDao.selectColumnList();
        MsgDao.tTransaction(2);
    }
}
