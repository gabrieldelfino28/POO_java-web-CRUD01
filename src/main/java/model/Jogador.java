package model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jogador {

	private int idJogador;
	private String nomeJogador;
	private LocalDate dataNasc;
	private float altura;
	private float peso;
	private Time time;

	public Jogador() {
		super();
	}
	
	@Override
	public String toString() {
		return "Jogador " + nomeJogador + "\nData de Nascimento=" + dataNasc + "\nAltura=" + altura + "\nPeso=" + peso;
	}

}
