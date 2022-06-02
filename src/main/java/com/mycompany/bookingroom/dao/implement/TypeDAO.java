package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.ITypeDAO;
import com.mycompany.bookingroom.mapper.TypeMapper;
import com.mycompany.bookingroom.model.Type;

public class TypeDAO extends AbstractDAO<Type> implements ITypeDAO{

	@Override
	public List<Type> findAll() {
		String sql = "SELECT * FROM dbo.Types";
        return query(sql, new TypeMapper());
	}

	@Override
	public Type findById(Integer id) {
		String sql = "SELECT * FROM dbo.Types WHERE id = ?";        
        List<Type> types = query(sql, new TypeMapper(), id);
        return types.size() > 0 ? types.get(0) : null;
	}

	@Override
	public Integer save(Type type) {
		String sql = "INSERT dbo.Types(NAME) VALUES(?)";            
        return insert(sql, type.getName());
	}

	@Override
	public void update(Type type) {
		String sql = "UPDATE dbo.Types SET Name = ? WHERE id = ?";        
        update(sql, type.getName(), type.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM dbo.Types WHERE id = ?";
        update(sql, id);
	}
}
