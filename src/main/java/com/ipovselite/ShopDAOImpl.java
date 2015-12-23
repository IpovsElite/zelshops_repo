package com.ipovselite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.ipovselite.Shop;

public class ShopDAOImpl implements ShopDAO {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public void setDataSource(DataSource src) {
		dataSource = src;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Shop get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM db.shop WHERE id="+id+";";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Shop>() {

			public Shop extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				Shop s = new Shop();
				if (rs.next()) {
					s.setId(rs.getInt("id"));
					s.setName(rs.getString("name"));					
					s.setAddress(rs.getString("address"));
					s.setSite(rs.getString("site"));
					s.setTelephone(rs.getString("telephone"));
					s.setSpec(rs.getString("spec"));
					s.setStatus(rs.getInt("status"));
					s.setDescription(rs.getString("description"));
					s.setRating(rs.getFloat("rating"));
					s.setVoters(rs.getInt("voters"));
					s.setLat(rs.getDouble("lat"));
					s.setLng(rs.getDouble("lng"));
					return s;
				}
				return null;
			}
			
		});
	}

	public List<Shop> search(SearchParameters sp) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM db.shop WHERE STATUS in (2,3) AND ";
		if (sp.getPattern()!=null && !sp.getPattern().equals("") && !sp.getPattern().equals(" ") ) {
			sql+=" (name like '%"+sp.getPattern()+"%' or address like '%"+sp.getPattern()+"%' or description like '%" + sp.getPattern()+"%')";
			if (sp.getSpec()!=null && !sp.getSpec().equals(""))
				sql+=" AND";
		}
		if (sp.getSpec()!=null && !sp.getSpec().equals(""))
			sql+=" spec='"+sp.getSpec()+"'";
		sql+=" ORDER BY ID DESC;";
		List<Shop> listShop=jdbcTemplate.query(sql, new RowMapper<Shop>() {

			public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Shop s = new Shop();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));					
				s.setAddress(rs.getString("address"));
				s.setSite(rs.getString("site"));
				s.setTelephone(rs.getString("telephone"));
				s.setSpec(rs.getString("spec"));
				s.setStatus(rs.getInt("status"));
				s.setDescription(rs.getString("description"));
				s.setRating(rs.getFloat("rating"));
				s.setVoters(rs.getInt("voters"));
				s.setLat(rs.getDouble("lat"));
				s.setLng(rs.getDouble("lng"));
				return s;	
			}
		});
		return listShop;
	}
	public List<Shop> getAllShops() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM db.shop;";
		List<Shop> listShop=jdbcTemplate.query(sql, new RowMapper<Shop>() {
			public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Shop s = new Shop();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setSite(rs.getString("site"));
				s.setAddress(rs.getString("address"));
				s.setTelephone(rs.getString("telephone"));
				s.setSpec(rs.getString("spec"));
				s.setStatus(rs.getInt("status"));
				s.setDescription(rs.getString("description"));
				s.setRating(rs.getFloat("rating"));
				s.setVoters(rs.getInt("voters"));
				s.setLat(rs.getDouble("lat"));
				s.setLng(rs.getDouble("lng"));
				return s;	
			}
		});
		return listShop;
	}
	public void addShop(Shop shop) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO db.shop (name,site,address,telephone,spec,status,description,lat,lng) VALUES (?,?,?,?,?,?,?,?,?);";
		jdbcTemplate.update(sql,shop.getName(),shop.getSite(),shop.getAddress(),shop.getTelephone(),shop.getSpec(),"0",shop.getDescription(),shop.getLat(),shop.getLng());
	}
	public void updateColumn(String col,Object val, int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE db.shop SET " + col + " = '" + val.toString() + "' WHERE id=" + id + ";";
		jdbcTemplate.update(sql);
	}
	public List<Shop> findByStatus(int status) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM db.shop WHERE STATUS = " + status + " ORDER BY ID DESC;";
		List<Shop> listShop=jdbcTemplate.query(sql, new RowMapper<Shop>() {
			public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Shop s = new Shop();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setSite(rs.getString("site"));
				s.setAddress(rs.getString("address"));
				s.setTelephone(rs.getString("telephone"));
				s.setSpec(rs.getString("spec"));
				s.setStatus(rs.getInt("status"));
				s.setDescription(rs.getString("description"));
				s.setRating(rs.getFloat("rating"));
				s.setVoters(rs.getInt("voters"));
				s.setLat(rs.getDouble("lat"));
				s.setLng(rs.getDouble("lng"));
				return s;	
			}
		});
		return listShop;
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM db.shop WHERE id = " + id + ";";
		jdbcTemplate.update(sql);
	}
	public void addComment(int shopid, String userid, String comment) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO db.comment (shopid,userid,comment) VALUES (?,?,?);";
		jdbcTemplate.update(sql,shopid, userid, comment);
	}
	public List<Comment> watchComments(int shopid) {
		// TODO Auto-generated method stub
		String sql = "SELECT comment FROM db.comment WHERE shopid = " + shopid + ";";
		List<Comment> listComment=jdbcTemplate.query(sql, new RowMapper<Comment>() {
			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Comment c = new Comment();
				c.setText(rs.getString("comment"));
				return c;
			}
		});
		return listComment;
	}
	

}
