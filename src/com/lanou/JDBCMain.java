package com.lanou;

import java.sql.*;

/**
 * Created by dllo on 17/11/23.
 */
public class JDBCMain {
    public static void main(String[] args) throws SQLException {
        /*
        JDBC使用分为五大步骤



         */
        //1.驱动数据库
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());//匿名对象
        //2.建立连接
        //getConnection参数 url 数据库的地址  user 用户 password 密码
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lanou","root","123456");
        //3.创建执行数据库语言的对象
        Statement statement=connection.createStatement();
        //4.将结果返回给Result对象
        ResultSet resultSet=statement.executeQuery("SELECT * FROM EMP");
        //   10.for 表示for(int i=0,i<10,i++)
        while (resultSet.next()) {
            System.out.print(resultSet.getObject("ename")+"  ");
            System.out.print(resultSet.getObject("sal")+"   ");
            System.out.print(resultSet.getObject("job")+"    ");
            System.out.println();
        }
        //5.释放资源   !!!!!!!!!!!
        resultSet.close();
        statement.close();
        connection.close();

        /*
        封装





         */








    }
}
