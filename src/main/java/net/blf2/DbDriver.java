package net.blf2;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;

import java.sql.SQLException;

/**
 * Created by blf2 on 17-6-28.
 */
public class DbDriver {
    private static String url = "jdbc:mysql://localhost:3306/"+Consts.dbName+"?user="+Consts.userName+
            "&password="+Consts.userPswd+ "&useUnicode=true&characterEncoding=UTF-8";
    public static IDBConnection idbConnection;
    static {
        idbConnection = new DBConnection(url,null,null,Consts.dbType);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("导入数据库驱动成功");
            if(idbConnection.getConnection() != null) {
                System.out.println("数据库连接成功");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("导入驱动包失败。");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接错误");
        }
    }
    public static void closeConnection(){
        try {
            idbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("关闭数据库连接出错");
        }
        System.out.println("数据库连接关闭完成");
    }
}
