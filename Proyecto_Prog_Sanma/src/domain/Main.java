package domain;
import db.BD;
import domain.Entradas.TipoEntrada;
import domain.Usuario.tipoSocio;
import gui.VentanaCargando;

public class Main  {
	public static void main(String[] args) {
		
		// ejecucucion ventana de inicio
        new VentanaCargando();

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
	        BD.borrarTablas(); // Borrar tablas para evitar conflictos
	        BD.crearTablas();

	        System.out.println(" Pruebas de inserción y comprobación ");

	        // Insertar categorías de entradas
	        System.out.println("Insertando categorías de entradas...");
	        BD.insertarEntradas(TipoEntrada.GENERAL, 60);
	        BD.insertarEntradas(TipoEntrada.SOCIOS, 50);
	        BD.insertarEntradas(TipoEntrada.GAZTEABONO, 30);
	        BD.insertarEntradas(TipoEntrada.VIP, 150);
	        System.out.println("Categorías de entradas insertadas correctamente.");
	        
	        // Insertar compras
	        System.out.println("Insertando compras...");
	        BD.insertarCompras(1, 1, "A1", "Bloque 1", 60, "GENERAL");
	        BD.insertarCompras(2, 2, "B2", "Bloque 2", 50, "SOCIOS");
	        BD.insertarCompras(3, 3, "C3", "Bloque 3", 150, "VIP");
	        BD.insertarCompras(4, 4, "D4", "Bloque 4", 30, "GAZTEABONO");
	        System.out.println("Compras insertadas correctamente.");


	        // Insertar usuarios
	        System.out.println("Insertando usuarios...");
	        BD.insertarUsuario(tipoSocio.SOCIO, "Juan", "Perez", "123456789", "1985-04-15", "juan.perez@example.com", "password123");
	        BD.insertarUsuario(tipoSocio.VIP, "Maria", "Lopez", "987654321", "1990-11-23", "maria.lopez@example.com", "passMaria");
	        BD.insertarUsuario(tipoSocio.GAZTEABONO, "Carlos", "Garcia", "555555555", "1978-06-10", "carlos.garcia@example.com", "carlosPass");
	        BD.insertarUsuario(tipoSocio.SOCIO, "Ana", "Martinez", "666777888", "1992-07-19", "ana.martinez@example.com", "anaPass");
	        System.out.println("Usuarios insertados correctamente.");

	        // Verificar existencia de usuarios
	        System.out.println("Comprobando si existen usuarios...");
	        System.out.println("Usuario con ID 1 existe: " + BD.existeID(1));
	        System.out.println("Usuario con ID 4 existe: " + BD.existeID(4));

	        // Cerrar base de datos
	        BD.closeBD();
	        System.out.println("Pruebas completadas y base de datos cerrada.");

	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

}
