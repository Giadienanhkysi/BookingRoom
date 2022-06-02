
package com.mycompany.bookingroom.dao;

import com.mycompany.bookingroom.mapper.RowMapper;
import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> row, Object... parameters);
    void update(String sql, Object... parameters);
    Integer insert(String sql, Object... parameters);
}
