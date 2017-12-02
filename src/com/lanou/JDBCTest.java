package com.lanou;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

/**
 * Created by dllo on 17/11/23.
 */
public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //驱动数据库
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanou","root","123456");
        //创建Statement对象
        Statement statement = connection.createStatement();
        //将结果返回给Result对象
        ResultSet resultSet = statement.executeQuery("SELECT * from lanou.EMP");
        List<EmpBean> data = new ArrayList<>();//创建集合用来存取对象

        while(resultSet.next()) {
            String ename = resultSet.getString("ename");
            int sal = resultSet.getInt("sal");
            Date hiredate = resultSet.getDate("hiredate");
            String job = resultSet.getString("job");

            //每循环一次创建一次对象
            EmpBean empBean = new EmpBean();
            empBean.setEname(ename);
            empBean.setJob(job);
            empBean.setSal(sal);
            empBean.setHiredate(hiredate);

            data.add(empBean);//将对象添加到集合中
        }
        //释放资源
        resultSet.close();
        statement.close();
        connection.close();
        for (EmpBean bean : data) {
            System.out.println(bean.getEname() + " " + bean.getJob() + " " +
                    bean.getSal() + " " + bean.getHiredate());

        }

    }
}








