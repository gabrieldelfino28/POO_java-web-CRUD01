package perseverance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jogador;
import model.Time;

public class JogadorDAO implements ICRUD<Jogador> {

	private GenericDAO gDAO;

	public JogadorDAO(GenericDAO gDAO) {
		this.gDAO = gDAO;
	}

	@Override
	public void inserir(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "INSERT INTO Jogador VALUES (?,?,?,?,?,?)"; //Prepared Statement formatar o SQL de acordo com os parâmetros
		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, j.getIdJogador());
		ps.setString(2, j.getNomeJogador());
		ps.setString(3, (j.getDataNasc().toString()));
		ps.setFloat(4, j.getAltura());
		ps.setFloat(5, j.getPeso());
		ps.setInt(6, j.getTime().getCodTime());

		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "UPDATE Jogador SET nomeJogador = ?, dataNasc = ?, altura = ?, peso = ?, TimeCodigo = ? WHERE idJogador = ?";
		PreparedStatement ps = c.prepareStatement(sql); 

		ps.setString(1, j.getNomeJogador());
		ps.setDate(2, java.sql.Date.valueOf(j.getDataNasc()));
		ps.setFloat(3, j.getAltura());
		ps.setFloat(4, j.getPeso());
		ps.setInt(5, j.getTime().getCodTime());
		ps.setInt(6, j.getIdJogador());

		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "DELETE FROM jogador WHERE idJogador = ?";
		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, j.getIdJogador());

		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Jogador consultar(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT j.idJogador, j.nomeJogador, j.dataNasc, ");
		sql.append("j.altura, j.peso, ");
		sql.append("t.codTime, t.nomeTime, t.cidade ");
		sql.append("FROM Team t, Jogador j ");
		sql.append("WHERE t.codTime = j.TimeCodigo "); 
		sql.append("AND j.idJogador = ?");
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, j.getIdJogador());
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Time t = new Time();
			
			t.setCodTime(rs.getInt("codTime"));
			t.setNomeTime(rs.getString("nomeTime"));
			t.setCidade(rs.getString("cidade"));
			
			j.setIdJogador(rs.getInt("idJogador"));
			j.setNomeJogador(rs.getString("nomeJogador"));
			j.setDataNasc(rs.getDate("dataNasc").toLocalDate());
			j.setAltura(rs.getFloat("altura"));
			j.setPeso(rs.getFloat("peso"));
			j.setTime(t);

		}
		ps.execute();
		ps.close();
		c.close();

		return j;
	}

	@Override
	public List<Jogador> listar() throws SQLException, ClassNotFoundException {
		List<Jogador> jogadores = new ArrayList<Jogador>();
		Connection c = gDAO.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT j.idJogador, j.nomeJogador, j.dataNasc, ");
		sql.append("j.altura, j.peso, ");
		sql.append("t.codTime, t.nomeTime, t.cidade ");
		sql.append("FROM Team t, Jogador j ");
		sql.append("WHERE t.codTime = j.TimeCodigo"); 
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Time t = new Time();
			
			t.setCodTime(rs.getInt("codTime"));
			t.setNomeTime(rs.getString("nomeTime"));
			t.setCidade(rs.getString("cidade"));
			
			Jogador j = new Jogador();
			j.setIdJogador(rs.getInt("idJogador"));
			j.setNomeJogador(rs.getString("nomeJogador"));
			j.setDataNasc(rs.getDate("dataNasc").toLocalDate());
			j.setAltura(rs.getFloat("altura"));
			j.setPeso(rs.getFloat("peso"));
			j.setTime(t);
			
			jogadores.add(j);
		}
		ps.execute();
		ps.close();
		c.close();
		return jogadores;
	}

}
