package com.test.dao;

import com.test.HikariUtil;
import com.test.dto.MsgDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Create by Guolianxing on 2018/10/26.
 */
public class MsgDao {

    private static QueryRunner queryRunner;
    static {
        queryRunner = HikariUtil.getQueryRunner();
    }

    // 用Insert方法插入，返回id，BigInteger类型
    public static void insertMsg(String msg, String author) throws SQLException {
        String sql = "insert into tb_msg (msg, author) values (?, ?) ";
        Object[] params = new Object[]{msg, author};
        BigInteger res = queryRunner.insert(sql, new ScalarHandler<>(), params);
        System.out.println(res);
    }

    // 用update方法，可以执行insert, update, delete操作，返回受影响行数
    public static void saveMsg(String msg, String author) throws SQLException {
        String sql = "insert into tb_msg (msg, author) values (?, ?) ";
        Object[] params = new Object[]{msg, author};
        int res = queryRunner.update(sql, params);
        System.out.println(res);
    }

    public static void updateMsg(String msg, long id) throws SQLException {
        String sql = "update tb_msg set msg = ? where id = ? ";
        Object[] params = new Object[]{msg, id};
        int res = queryRunner.update(sql, params);
        System.out.println(res);
    }

    public static void deleteMsg(long id) throws SQLException {
        String sql = "delete from tb_msg where id = ? ";
        Object[] params = new Object[]{id};
        int res = queryRunner.update(sql, params);
        System.out.println(res);
    }

    // execute方法，可以执行insert, update, delete操作，返回受影响行数
    public static void executeUpdate(String msg) throws SQLException {
        String sql = "update tb_msg set msg = ? where id = 4 ";
        Object[] params = new Object[]{msg};
        int res = queryRunner.execute(sql, params);
        System.out.println(res);
    }

    // 执行QueyrRunner的public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)方法来检索结果集
    // 通过 ResultSetHandler<T>来控制结果集的封装

    // 返回单个对象，通过new BeanHandler<>(Class<?> clazz)来设置封装
    public static void selectSingleRes(long id) throws SQLException {
        String sql = "select * from tb_msg where id = ? ";
        Object[] params = new Object[]{id};
        MsgDto msgDto = queryRunner.query(sql, new BeanHandler<>(MsgDto.class), params);
        System.out.println(msgDto);
    }

    public static void selectSingleRes2(long id) throws SQLException {
        String sql = "select msg from tb_msg where id = ? ";
        Object[] params = new Object[]{id};
        MsgDto msgDto = queryRunner.query(sql, new BeanHandler<>(MsgDto.class), params);
        System.out.println(msgDto);
    }

    //返回列表，通过new BeanListHandler<>(Class<?> clazz)来设置List的泛型
    public static void selectList() throws SQLException {
        String sql = "select * from tb_msg";
        List<MsgDto> list = queryRunner.query(sql, new BeanListHandler<>(MsgDto.class));
        list.forEach(System.out::println);
    }

    // 返回单行单列结果集，通过 new ScalarHandler<T>()来设置返回的数据类型
    public static void selectMsg(long id) throws SQLException {
        String sql = "select msg from tb_msg where id = ? ";
        Object[] params = new Object[]{id};
        String res = queryRunner.query(sql, new ScalarHandler<String>(), params);
        System.out.println(res);
    }

    // 多行单列结果集，通过new ColumnListHandler<T>()
    public static void selectColumnList() throws SQLException {
        String sql = "select msg from tb_msg";
        List<String> list = queryRunner.query(sql, new ColumnListHandler<String>());
        list.forEach(System.out::println);
    }

    // 返回List<Map<String, Object>>
    // 把每一行结果封装为一个Map，在把多个行封装到List
    // 通过new MapListHandler()来设置
    public static void selectMapList() throws SQLException {
        String sql = "select * from tb_msg";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
        list.forEach(e -> e.forEach((k, v) -> System.out.println(k + ": " + v)));
    }

    // 批处理，一次性执行N条的INSERT,UPDATE,DELETE 语句
    // 执行修改，返回是一个int[]，表示每条SQL执行后受到影响的行数。
    public static void batchInsert() throws SQLException {
        String sql = "insert into tb_msg (msg, author) values (?, ? ) ";
        Object[][] params = new Object[][] {
                {"a;sjd", "tony"},
                {"ajf;adsfj", "jerry"}
        };
        int[] res = queryRunner.batch(sql, params);
        System.out.println(Arrays.toString(res));
    }

    // 事务处理
    // 一个事务中的多个操作，肯定都是在一个Connection中
    // QueryRunner的执行方法（CRUD）都有一个重载，可以设置Connection
    // 从而保证了多个操作都是用的同一个Connection，而事务由该Connection控制即可
    public static void tTransaction(long id) throws Exception {
        Connection connection = HikariUtil.getConnection();
        connection.setAutoCommit(false);
        try {
            String sql = "select author from tb_msg where id = ? ";
            String author = queryRunner.query(connection, sql, new ScalarHandler<>(), id);
            System.out.println(author);
            sql = "delete from tb_msg where author = ? ";
            int res = queryRunner.update(connection, sql, author);
            int a = 4 / 0;
            connection.commit();
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("rollback");
            connection.rollback();
            throw e;
        } finally {
            HikariUtil.close(connection);
        }
    }


}
