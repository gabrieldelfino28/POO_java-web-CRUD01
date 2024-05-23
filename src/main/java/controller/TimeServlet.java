package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Time;
import perseverance.GenericDAO;
import perseverance.TimeDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TimeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("time.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Time> times = new LinkedList<Time>();
		Time t = new Time();
		
		//Entrada de dados
		String cmd = request.getParameter("enviar");
		String codTime = request.getParameter("codTime");
		String nomeTime = request.getParameter("nomeTime");
		String cidade = request.getParameter("cidade");
		
		//Saida de dados;
		
		String saida = "";
		String err = "";
		
		if (!cmd.contains("Listar")) {
			t.setCodTime(Integer.parseInt(codTime));
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			t.setNomeTime(nomeTime);
			t.setCidade(cidade);
		}
		
		try {
			if (cmd.contains("Cadastrar")) {
				cadastrarTime(t);
				saida = "Time cadastrado com sucesso!";
				t = null;
			}
			
			if (cmd.contains("Alterar")) {
				alterarTime(t);
				saida = "Time alterado com sucesso!";
				t = null;
			}
			
			if (cmd.contains("Excluir")) {
				excluirTime(t);
				saida = "Time excluído com sucesso!";
				t = null;
			}
			
			if (cmd.contains("Buscar")) {
				t = buscarTime(t);
			}
			
			if (cmd.contains("Listar")) {
				times = listarTime();
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			err = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("err", err);
			request.setAttribute("time", t);
			request.setAttribute("times", times);
			
			RequestDispatcher rd = request.getRequestDispatcher("time.jsp");
			rd.forward(request, response);
		}
		
	}

	private void cadastrarTime(Time t) throws SQLException, ClassNotFoundException{
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		tDAO.inserir(t);
	}

	private void alterarTime(Time t) throws SQLException, ClassNotFoundException{
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		tDAO.atualizar(t);
	}

	private void excluirTime(Time t) throws SQLException, ClassNotFoundException{
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		tDAO.excluir(t);
	}

	private Time buscarTime(Time t) throws SQLException, ClassNotFoundException{
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		t = tDAO.consultar(t);
		return t;
	}

	private List<Time> listarTime() throws SQLException, ClassNotFoundException{
		GenericDAO gDAO = new GenericDAO();
		TimeDAO tDAO = new TimeDAO(gDAO);
		List<Time> times = tDAO.listar();
		
 		return times;
	}

}
