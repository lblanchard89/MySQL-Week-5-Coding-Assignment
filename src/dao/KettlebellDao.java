package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Kettlebell;

public class KettlebellDao {
	
	private Connection connection; 
	private final String GET_KETTLEBELLS_QUERY = "SELECT * FROM kettlebells";
	private final String GET_KETTLEBELL_BY_SERIAL_NO_QUERY = "SELECT * FROM kettlebells WHERE serial_no = ?";
	private final String CREATE_KETTLEBELL_QUERY = "INSERT INTO kettlebells(weight, color) VALUES(?, ?)";
	private final String DELETE_KETTLEBELL_BY_SERIAL_NO_QUERY = "DELETE FROM kettlebells WHERE serial_no = ?";
	private final String UPDATE_KETTLEBELL_QUERY = "UPDATE kettlebells SET weight = ?, color = ? WHERE serial_no = ?";
	public KettlebellDao() {
		connection = DBConnection.getConnection();
		
	}
	
	public List<Kettlebell> getKettlebells() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_KETTLEBELLS_QUERY).executeQuery();
		List<Kettlebell> kettlebells = new ArrayList<Kettlebell>();
		
		while (rs.next()) {
			kettlebells.add(populateKettlebell(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		
		return kettlebells;
	}
	
	public Kettlebell getKettlebellBySerialNo(int serial_no) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_KETTLEBELL_BY_SERIAL_NO_QUERY);
		ps.setInt(1, serial_no);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateKettlebell(rs.getInt(1), rs.getString(2), rs.getNString(3));
	}
	
	public void createKettlebell(String kettlebellWeight, String kettlebellColor) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_KETTLEBELL_QUERY);
		ps.setString(1, kettlebellWeight);
		ps.setString(2, kettlebellColor);
		ps.executeUpdate();
	}
	
	public void deleteKettlebell(int serial_no) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_KETTLEBELL_BY_SERIAL_NO_QUERY);
		ps.setInt(1, serial_no);
		ps.executeUpdate();
	}
	
	public void updateKettlebell(String kettlebellWeight, String kettlebellColor, int serial_no) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_KETTLEBELL_QUERY);
		ps.setString(1, kettlebellWeight);
		ps.setString(2, kettlebellColor);
		ps.setInt(3, serial_no);
		ps.executeUpdate();
	}
	
	private Kettlebell populateKettlebell(int serial_no, String weight, String color) {
		return new Kettlebell(serial_no, weight, color);
	}

}
