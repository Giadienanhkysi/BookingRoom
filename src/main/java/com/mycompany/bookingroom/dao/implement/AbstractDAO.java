package com.mycompany.bookingroom.dao.implement;

import com.mycompany.bookingroom.dao.GenericDAO;
import com.mycompany.bookingroom.mapper.RowMapper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractDAO<T> implements GenericDAO<T>{

    public Connection getConnection(){        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databasename=BookingRoom;integratedSecurity=true;"
                    +"encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String pass = "123456";           
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        } 
    }
    @Override
    public <T> List<T> query(String sql, RowMapper<T> row, Object... parameters) {
        List<T> res = new ArrayList<>();        
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if(connection != null){
            try {
                statement = connection.prepareStatement(sql);
                setParameter(statement, parameters);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    T object = row.mapRow(resultSet);
                    res.add(object);
                }
                return res;
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                if(connection != null)
                    connection.close();
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }        
        return null;
    }
    private void setParameter(PreparedStatement statement, Object... parameters){
        try{
            int i = 1;
            for(Object param: parameters){
                if(param instanceof String){
                    statement.setString(i, (String)param);
                }else if(param instanceof Long){
                    statement.setLong(i, (Long)param);
                }else if(param instanceof Integer){
                    statement.setInt(i, (Integer)param);
                }else if(param instanceof Timestamp){
                    statement.setTimestamp(i, (Timestamp)param);                    
                }else if(param instanceof Date){
                    statement.setDate(i, (Date)param);
                }else if(param instanceof Double){
                    statement.setDouble(i, (Double)param);
                }else if(param == null){
                    statement.setNull(i, java.sql.Types.INTEGER);
                }
                i++;
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
            try {
                System.out.println("Transaction Rollback");
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                if(connection != null)
                    connection.close();
                if(statement != null)
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        ResultSet resultSet = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        Integer id = null;
        try {                      
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
            connection.commit();
            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{            
            try {
                if(connection!=null){
                    connection.close();                    
                }
                if(statement != null){
                    statement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

   
}
