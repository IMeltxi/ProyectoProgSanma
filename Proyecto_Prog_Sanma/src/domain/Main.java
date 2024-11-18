package domain;

import java.util.List;

import domain.Admin;
public class Main {
	public static void main(String[] args) {
		Admin a = new Admin();
		a.cargarUsuarios();
		a.visualizarSocios();
		//List<Usuario> usuarios = a.cargarUsuarios(); Da error ya que el metodo no devuelve nada
	}
}
