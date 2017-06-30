package net.blf2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by blf2 on 17-6-28.
 */
public class Consts {
    public static String userName = "root";
    public static String userPswd = "123456";
    public static String dbName="owl";
    public static String dbType="mysql";
    public static String owlFilePath="/home/blf2/workspace/1.owl";
    private static String osf = System.getProperties().getProperty("os.name").toLowerCase().startsWith("win") ? "\\" : "/";
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream(new File("").getAbsoluteFile()+osf+"config.properties"));
            if(properties.getProperty("testkey").equals("test"))
                System.out.println("配置文件打开成功");
            userName = properties.getProperty("mysql.userName");
            userPswd = properties.getProperty("mysql.userPswd");
            dbName = properties.getProperty("mysql.dbName");
            owlFilePath = properties.getProperty("owlFilePath");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("application.properties文件未找到");
        }
    }
}
