package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jogador;
import model.Time;
import perseverance.GenericDAO;
import perseverance.JogadorDAO;
import perseverance.TimeDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jogador")
public class JogadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JogadorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err = "";
		List<Time> times = new ArrayList<Time>();
		
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);

		try {
			times = tDAO.listar();
		} catch (ClassNotFoundException | SQLException e) {
			err = e.getMessage();
		} finally {
			request.setAttribute("err", err);
			request.setAttribute("times", times);
			
			RequestDispatcher rd = request.getRequestDispatcher("jogador.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Jogador> jogadores = new ArrayList<>();
		List<Time> times = new ArrayList<Time>();
		Jogador j = new Jogador();

		// Entrada de Dados

		String cmd = request.getParameter("enviar");
		
		String idJogador = request.getParameter("idJogador");
		String nomeJogador = request.getParameter("nomeJogador");
		String dataNasc = request.getParameter("dataNasc");
		String altura = request.getParameter("altura");
		String peso = request.getParameter("peso");
		String codTime = request.getParameter("codTime");

		// Saida de dados

		String saida = "";
		String err = "";

		if (!cmd.contains("Listar")) {
			j.setIdJogador(Integer.parseInt(idJogador));
		}

		try {
			times = listarTime();
			if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				Time t = new Time();
				t.setCodTime(Integer.parseInt(codTime));
				t = buscarTime(t);

				j.setNomeJogador(nomeJogador);
				j.setDataNasc(LocalDate.parse(dataNasc));
				j.setAltura(Float.parseFloat(altura));
				j.setPeso(Float.parseFloat(peso));
				j.setTime(t);
			}

			if (cmd.contains("Cadastrar")) {
				cadastrarJogador(j);
				saida = "Jogador cadastrado com sucesso!";
				j = null;
			}
			if (cmd.contains("Alterar")) {
				alterarJogador(j);
				saida = "Jogador alterado com sucesso!";
				j = null;
			}
			if (cmd.contains("Excluir")) {
				excluirJogador(j);
				saida = "Jogador excluído com sucesso!";
				j = null;
			}
			if (cmd.contains("Buscar")) {
				j = buscarJogador(j);
			}
			if (cmd.contains("Listar")) {
				jogadores = listarJogadores();
			}
		} catch (SQLException | ClassNotFoundException e) {
			err = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("err", err);
			request.setAttribute("jogador", j);
			request.setAttribute("jogadores", jogadores);
			request.setAttribute("times", times);

			RequestDispatcher rd = request.getRequestDispatcher("jogador.jsp");
			rd.forward(request, response);
		}
	}

	private void cadastrarJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		JogadorDAO jDAO = new JogadorDAO(gDAO);
		jDAO.inserir(j);
	}

	private void alterarJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		JogadorDAO jDAO = new JogadorDAO(gDAO);
		jDAO.atualizar(j);
	}

	private void excluirJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		JogadorDAO jDAO = new JogadorDAO(gDAO);
		jDAO.excluir(j);
	}

	private Jogador buscarJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		JogadorDAO jDAO = new JogadorDAO(gDAO);
		j = jDAO.consultar(j);

		return j;
	}

	private List<Jogador> listarJogadores() throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		JogadorDAO jDAO = new JogadorDAO(gDAO);
		List<Jogador> jogadores = jDAO.listar();

		return jogadores;
	}

	private Time buscarTime(Time t) throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		t = tDAO.consultar(t);
		return t;
	}

	private List<Time> listarTime() throws SQLException, ClassNotFoundException {
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		List<Time> times = tDAO.listar();

		return times;
	}

}
