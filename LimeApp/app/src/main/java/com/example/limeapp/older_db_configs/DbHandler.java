package com.example.limeapp.older_db_configs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import  java.sql.SQLException;
public class DbHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
            String connectionString =  "jdbc:mysql://" + dbHost +  ":" + dbPort + "/" + dbName;

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/log_work_schema",dbUser,dbPass);

        return dbConnection;
    }
    public void SingUpMember(String phone_of_member, String age_of_member, String psw_of_member) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String insert = "INSERT INTO " + Const.MEMBERS_TABLE + "(" + Const.USERS_PHONE + "," + Const.USERS_AGE + "," + Const.USERS_PASS + ")" + "VALUES(?,?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1,phone_of_member);
        prSt.setString(2,age_of_member);
        prSt.setString(3,psw_of_member);
        prSt.executeUpdate();
    }
}
