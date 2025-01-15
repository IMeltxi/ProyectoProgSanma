package domain;
import db.BD;
import gui.VentanaInicio;

public class Main  {
	public static void main(String[] args) {
		
		// ejecucucion ventana de inicio
        new VentanaInicio(null);

		// Creamos una instancia de la clase Admin para gestionar usuarios y socios
		Admin a = new Admin();
		a.cargarUsuarios();
		a.visualizarSocios();
		
		pruebas();
	
	}
	
	public static void pruebas() {
	    try {
	        // Inicializar base de datos y crear tablas
	        BD.initBD("resources/db/sanMames.db");
	        //BD.borrarTablas(); // Borrar tablas para evitar conflictos
	        BD.crearTablas();

	        // Cerrar base de datos
	        BD.closeBD();
	        System.out.println("Pruebas completadas y base de datos cerrada.");

	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

}
