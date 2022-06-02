
package com.mycompany.bookingroom.mapper;

import java.sql.ResultSet;
//lớp chuyển dữ liệu từ hàng trong bảng thành java object
public interface RowMapper<T> {
    T mapRow(ResultSet resultSet);
}
