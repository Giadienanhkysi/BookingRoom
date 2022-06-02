

import com.mycompany.bookingroom.dao.implement.*;
import com.mycompany.bookingroom.dao.IHotelDAO;
import com.mycompany.bookingroom.mapper.HotelMapper;
import com.mycompany.bookingroom.mapper.RowMapper;
import com.mycompany.bookingroom.model.Hotel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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

/**
 *
 * @author hensh
 */
public class HotelDAO implements IHotelDAO{
    
    public Connection getConnection(){        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databasename=Testbk;integratedSecurity=true;"
                    +"encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String pass = "123456";
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
                }
                i++;
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
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
            System.out.println(e);
            try {
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
            System.out.println(ex);
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }
//    public Integer save(Hotel hotel) {
//        ResultSet resultSet = null;
//        Connection connection = getConnection();
//        PreparedStatement statement = null;
//        Integer id = null;
//        try {
//            String sql = "INSERT INTO dbo.Hotels(NAME,ADDRESS,DESCRIPTION,STAR)VALUES(?, ?, ?, ?)";            
//            connection.setAutoCommit(false);
//            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, hotel.getName());
//            statement.setString(2, hotel.getAddress());
//            statement.setString(3, hotel.getDescription());
//            statement.setInt(4, hotel.getStar());
//            System.out.println(hotel.getName());
//            statement.executeUpdate();
//            resultSet = statement.getGeneratedKeys();
//            if(resultSet.next()){
//                System.out.println("Có tao nè");
//                id = resultSet.getInt(1);
//            }
//            System.out.println("Con cặc");
//            connection.commit();
//            return id;
//        } catch (SQLException ex) {
//            if(connection != null){
//                try {
//                    System.out.println("lỗi");
//                    connection.rollback();
//                } catch (SQLException ex1) {
//                    Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex1);
//                }
//            }
//            Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{            
//            try {
//                if(connection!=null){
//                    System.out.println("Đóng kết nối");
//                    connection.close();                    
//                }
//                if(statement != null){
//                    System.out.println("Đóng statemen");
//                    statement.close();
//                }
//                if(resultSet != null){
//                    System.out.println("Đóng reusult set");
//                    resultSet.close();
//                }
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        return null;
//    }
    
    @Override
    public Hotel findById(Integer id) {
        String sql = "SELECT * FROM dbo.Hotels WHERE id = ?";        
        List<Hotel> hotels = query(sql, new HotelMapper(), id);
        return hotels.size() > 0 ? hotels.get(0) : null;
    }

    @Override
    public Integer save(Hotel hotel) {        
        String sql = "INSERT dbo.Hotels(NAME,ADDRESS,DESCRIPTION,STAR)VALUES(?, ?, ?, ?)";            
        return insert(sql, hotel.getName(), hotel.getAddress(), hotel.getDescription(), hotel.getStar());
    }
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
                Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                if(connection != null)
                    connection.close();
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(com.mycompany.bookingroom.dao.implement.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }        
        return null;
    }
//    @Override
    public void update(Hotel hotel) {
        String sql = "UPDATE dbo.Hotels SET Name = ? , Address = ?, Description = ?, Star = ? "
                + " WHERE id = ?";        
        update(sql, hotel.getName(), hotel.getAddress(), hotel.getDescription(), hotel.getStar(), hotel.getId());
    }

//    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM dbo.Hotels WHERE id = ?";
        update(sql, id);
    }
//    @Override
    public List<Hotel> findAll() {
        List<Hotel> HotelModels = new ArrayList<>();
        String sql = "SELECT * FROM Hotels";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if(connection != null){
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    Hotel hotel = new Hotel();
                    hotel.setId(resultSet.getInt("ID"));
                    hotel.setName(resultSet.getString("NAME"));
                    hotel.setAddress(resultSet.getString("ADDRESS"));
                    hotel.setDescription(resultSet.getString("DESCRIPTION"));
                    hotel.setStar(resultSet.getInt("STAR"));
                    HotelModels.add(hotel);
                }
                return HotelModels;
            } catch (SQLException ex) {
                Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                if(connection != null)
                    connection.close();
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }
        return null;
    }
      public static void main(String[] args) {
        HotelDAO t = new HotelDAO();
//        StringBuffer ans = new StringBuffer("[");
//        int i = 0;
//        for(Hotel h : t.findAll()){
//            ans.append("{\"id\":" + h.getId() + ",");                  
//                  ans.append("\"name\": \"" + h.getName() + "\",");
//                  ans.append("\"address\": \"" + h.getAddress()+ "\",");
//                  ans.append("\"description\": \"" + h.getName() + "\",");
//                  ans.append("\"star\": " + h.getStar()+ "}");
//                  i++;
//                  if(i<t.findAll().size())
//                      ans.append(",");
//        }           
//              ans.append("]");
//              System.out.println(ans.toString());
//        String name = "Hanoi Daewoo Update";
//        String address = "Số 360, Phố Kim Mã, Quận Ba Đình, 13232312";
//        String description = "Hãy để chuyến đi của quý khách có một khởi đầu tuyệt vời khi ở lại khách sạn này, nơi có Wi-Fi miễn phí trong tất cả các phòng. Nằm ở vị trí trung tâm tại Quận Ba Đình của Hà Nội, chỗ nghỉ này đặt quý khách ở gần các điểm thu hút và tùy chọn ăn uống thú vị. Đừng rời đi trước khi ghé thăm Pho Co nổi tiếng. Được xếp hạng 5 sao, chỗ nghỉ chất lượng cao này cho phép khách nghỉ sử dụng mát-xa, bể bơi ngoài trời và bồn tắm nước nóng ngay trong khuôn viên.";
//        int star = 5;
//        Hotel hotel = new Hotel(8, name, address, description, star);
          System.out.println(t.findById(5).getName());
        
//          System.out.println(t.save(hotel));
    }

    @Override
    public List<Hotel> findAllByLocation(String location) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
