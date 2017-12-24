/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author thread009
 */
public class Database {

    private Connection database_connection;
    
    public static String HOST_ID;
    
    public static String PASSWORD;
    
    public static String DATABASE;

    protected void  setConnection(String host, String password, String database) {

        try {
            MysqlDataSource mysql = new MysqlDataSource();
            mysql.setDatabaseName(database);
            this.database_connection = mysql.getConnection(host, password);
        } catch (SQLException e) {
            getLogger().warning("Cannot connect to database: "+e);
            try {
                getLogger().info("Try Connect with default settings");
                MysqlDataSource mysql = new MysqlDataSource();
                mysql.setDatabaseName(database);
                this.database_connection = mysql.getConnection("root", "");
                getLogger().info("SUCCESS CONNECT !!!");
            } catch (SQLException eg) {
                System.out.println("asd"+eg);
            }
        }

    }
    
    protected Connection getConnection(){
        return this.database_connection;
    }

    protected Logger getLogger() {
        Logger logger = null;
        try{
            boolean append = true;
            FileHandler handler = new FileHandler("default.log", append);

            logger = Logger.getLogger("org.prediksikeuntungan.database");
            
        }catch(Exception e){}
        
        return logger;

    }
    

}
