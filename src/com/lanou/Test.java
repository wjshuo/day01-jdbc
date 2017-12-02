package com.lanou;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/11/23.
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //驱动数据库
        Class.forName("com.mysql.jdbc.Driver");
        //建立连接
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lanou","root","123456");

        //创建Statement对象
        Statement statement=connection.createStatement();
        //将结果获取到ResultSet对象中
        ResultSet resultSet=statement.executeQuery("SELECT * FROM lanou.EMP");
        List<EmpBean> data =new ArrayList<>();
        while (resultSet.next()){
            String ename = resultSet.getString("ename");
            String job=resultSet.getString("job");
            int sal=resultSet.getInt("sal");
            Date hiredate=resultSet.getDate("hiredate");
            //创建一个对象将数据存储到对象中
            EmpBean empBean=new EmpBean();
            empBean.setEname(ename);
            empBean.setJob(job);
            empBean.setSal(sal);
            empBean.setHiredate(hiredate);
            data.add(empBean);


        }
        //增强for循环遍历list
        for (EmpBean bean : data) {
            System.out.println(bean.getEname()+" "+bean.getJob()+" " + bean.getSal()+" "+bean.getHiredate());

        }
        //释放内存
        resultSet.close();
        statement.close();
        connection.close();







    }
}
