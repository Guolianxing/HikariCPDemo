# HikariCPDemo
HikariCP DBUtils demo


HikariCP链接MySQL的一些配置建议：[https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration](https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration)

----

DbUtils给我们提供了10个ResultSetHandler实现类，分别是：
1. `ArrayHandler` 将查询结果的第一行数据，保存到Object数组中
2. `ArrayListHandler` 将查询的结果，每一行先封装到Object数组中，然后将数据存入List集合
3. `BeanHandler` 将查询结果的第一行数据，封装成对象
4. `BeanListHandler` 将查询结果的每一行封装成对象，然后再存入List集合
5. `ColumnListHandler` 将查询结果的指定列的数据封装到List集合中
6. `MapHandler` 将查询结果的第一行数据封装到map结合（key==列名，value==列值）
7. `MapListHandler` 将查询结果的每一行封装到map集合（key==列名，value==列值），再将map集合存入List集合
8. `BeanMapHandler` 将查询结果的每一行数据，封装到User对象，再存入mao集合中（key==列名，value==列值）
9. `KeyedHandler` 将查询的结果的每一行数据，封装到map1（key==列名，value==列值 ），然后将map1集合（有多个）存入map2集合（只有一个）
10. `ScalarHandler` 单行单列结果集，只查询一个字段，或者聚合函数返回的结果
