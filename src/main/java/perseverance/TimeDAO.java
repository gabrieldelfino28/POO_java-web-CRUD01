package perseverance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Time;

public class TimeDAO implements ICRUD<Time> {
	private GenericDAO gDAO;

	public TimeDAO(GenericDAO gDAO) {
		this.gDAO = gDAO;
	}

	@Override
	public void inserir(Time t) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();	
		String sql = "INSERT INTO Team VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setInt(1, t.getCodTime());
		ps.setString(2, t.getNomeTime());
		ps.setString(3, t.getCidade());
		
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Time t) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();	
		String sql = "UPDATE Team SET nomeTime = ?, cidade = ? WHERE codTime = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setString(1, t.getNomeTime());
		ps.setString(2, t.getCidade());
		ps.setInt(3, t.getCodTime());
		
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Time t) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();	
		String sql = "DELETE FROM Team WHERE codTime = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setInt(3, t.getCodTime());
		
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Time consultar(Time t) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();	
		String sql = "SELECT codTime, nomeTime, cidade FROM Team WHERE codTime = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodTime());
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			t.setCodTime(rs.getInt("codTime"));
			t.setNomeTime(rs.getString("nomeTime"));
			t.setCidade(rs.getString("cidade"));
		}
		
		ps.execute();
		ps.close();
		c.close();
		return t;
	}

	@Override
	public List<Time> listar() throws SQLException, ClassNotFoundException {
		List<Time> times = new ArrayList<Time>();
		
		Connection c = gDAO.getConnection();	
		String sql = "SELECT codTime, nomeTime, cidade FROM Team";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Time t = new Time();
			t.setCodTime(rs.getInt("codTime"));
			t.setNomeTime(rs.getString("nomeTime"));
			t.setCidade(rs.getString("cidade"));
			times.add(t);
		}
		
		ps.execute();
		ps.close();
		c.close();
		
		return times;
	}

}
