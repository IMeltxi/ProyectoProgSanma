package domain;

import domain.Admin;
public class Main {
	public static void main(String[] args) {
		Admin a = new Admin();
		a.cargarUsuarios();
		System.out.println(a.getUsuarios());
	}
}
