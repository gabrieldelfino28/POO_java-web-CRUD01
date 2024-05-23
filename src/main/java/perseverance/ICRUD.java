package perseverance;

import java.sql.SQLException;
import java.util.List;

public interface ICRUD<E> {
	void inserir (E e) throws SQLException, ClassNotFoundException;
	void atualizar (E e) throws SQLException, ClassNotFoundException;
	void excluir (E e) throws SQLException, ClassNotFoundException;
	E consultar (E e) throws SQLException, ClassNotFoundException;
	List<E> listar () throws SQLException, ClassNotFoundException;
}
