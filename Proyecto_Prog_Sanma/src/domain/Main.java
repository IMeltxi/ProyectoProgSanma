package domain;
import db.BD;
import db.BD.tipoSocio;
import domain.Entradas.Localidad;
import domain.Entradas.TipoEntrada;
public class Main {
	public static void main(String[] args) {
		Admin a = new Admin();
		a.cargarUsuarios();
		a.visualizarSocios();
		//List<Usuario> usuarios = a.cargarUsuarios(); Da error ya que el metodo no devuelve nada
		BD.initBD("resources/db/sanMames.db");
		BD.crearTablas();
		pruebas();
	}
	
	public static void pruebas() {
		try {
		    // Inicializar base de datos y crear tablas
		    BD.initBD("resources/db/sanMames.db");
		    BD.borrarTablas();
		    BD.crearTablas();

		    // Pruebas para insertar datos y verificar métodos
		    System.out.println("=== Pruebas de inserción y comprobación ===");

		    // Insertar categorías de entradas
		    System.out.println("Insertando categorías de entradas...");
		    BD.insertarEntradas(TipoEntrada.GENERAL, 60);
		    BD.insertarEntradas(TipoEntrada.SOCIOS, 50);
		    BD.insertarEntradas(TipoEntrada.GAZTEABONO, 30);
		    BD.insertarEntradas(TipoEntrada.VIP, 150);
		    System.out.println("Categorías insertadas correctamente.");

		    // Insertar localizaciones
		    System.out.println("Insertando localizaciones...");
		    BD.insertarLocalizaciones(Localidad.NORTE);
		    BD.insertarLocalizaciones(Localidad.SUR);
		    BD.insertarLocalizaciones(Localidad.ESTE);
		    BD.insertarLocalizaciones(Localidad.PRINCIPAL);
		    System.out.println("Localizaciones insertadas correctamente.");

		    // Insertar usuarios
		    System.out.println("Insertando usuarios...");
		    BD.insertarUsuario("Juan", "Perez", "123456789", "juan.perez@example.com",
		            "password123", 1, "1985-04-15", tipoSocio.SOCIO);
		    BD.insertarUsuario("Maria", "Lopez", "987654321", "maria.lopez@example.com",
		            "passMaria", 2, "1990-11-23", tipoSocio.VIP);
		    BD.insertarUsuario("Carlos", "Garcia", "555555555", "carlos.garcia@example.com",
		            "carlosPass", 3, "1978-06-10", tipoSocio.GAZTEABONO);
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
