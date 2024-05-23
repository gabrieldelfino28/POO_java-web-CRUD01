package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Time {
	private int codTime;
	private String nomeTime;
	private String cidade;

	public Time() {
		super();
	}

	@Override
	public String toString() {
		return "Time " + nomeTime + '(' + cidade + ')';
	}

}
