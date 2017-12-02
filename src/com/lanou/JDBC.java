package com.lanou;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by dllo on 17/11/23.
 */
public class JDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //驱动数据库
        //第一种驱动数据库的方式
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //第二种方式(主流,常用方式)
        //反射
        //java运行时
        Class.forName("com.mysql.jdbc.Driver");
        //第二步 建立连接
        //方式1
        Connection connection=DriverManager.getConnection("jdbc:mysql://" +
                "localhost:3306/lanou","root","123456");

        //方式二
        Properties properties=new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123456");
        properties.setProperty("characterEncoding","utf8");
       //参数1 地址 参数2 配置对象
        Connection connection1=DriverManager.getConnection("jdbc:mysql:" +
                "//localhost:3306/lanou",properties);
        //方式3
        //参数携带配置信息的url

        Connection connection2=DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/lanou?user=root" +
                        "&password=123456&characterEncoding=utf8");

        //3.创建Statement对象
        Statement statement=connection2.createStatement();
        //返回值为更改的数据条数
       int a  =statement.executeUpdate("UPDATE lanou.DEPT SET LOC='瓦房店'where DEPTNO=20");
        System.out.println(a);

        int b=statement.executeUpdate("DELETE FROM lanou.DEPT WHERE deptno = 50 ");
        System.out.println(b);
        //execute支持所有的数据库语句,返回值类型是boolean值
        //statement.execute()

        ResultSet resultSet=statement.executeQuery("SELECT * from lanou.EMP");
        List<EmpBean> data=new ArrayList<>();


        while (resultSet.next()){
            //resultSet.getObject("ename");
            //已知类型的时候
            String ename =resultSet.getString("ename");
            int sal = resultSet.getInt("sal");
            Date hiredate=resultSet.getDate("hiredate");
            String job =resultSet.getString("job");
            int empno=resultSet.getInt("empno");
            //创建存放数据的对象
            EmpBean empBean=new EmpBean();//每循环一次创建一次对象
            empBean.setEname(ename);
            empBean.setSal(sal);
            empBean.setJob(job);
            empBean.setHiredate(hiredate);
            //链式.此时将empbean类的setter /getter方法改变.使用builder
//            EmpBean empBean=new EmpBean();
//            empBean.setEmpno(empno).setEname(ename).setJob(job).setSal(sal);

            data.add(empBean);//将数据添加到集合中

//            System.out.println(ename+"  "+sal + " "+ date );



        }

        //释放资源
        resultSet.close();
        statement.close();
        connection2.close();

        for (EmpBean datum:data){
            System.out.println(datum.getEname()+" "+datum.getJob()+" "+
                    datum.getSal()+" "+datum.getHiredate());
        }

        //data 集合名 每循环一次 创建一次datum
        //for(数据类型 变量名 :遍历的目标









    }
}
