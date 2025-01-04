package domain;
import db.BD;
import db.BD.tipoSocio;
import domain.Entradas.Localidad;
import domain.Entradas.TipoEntrada;
public class Main  {
	public static void main(String[] args) {
		Admin a = new Admin();
		a.cargarUsuarios();
		a.visualizarSocios();
		BD.initBD("resources/db/sanMames.db");
		BD.crearTablas();
		pruebas();
		//Funcion recursiva
		Partido[] partidos = {
			    new Partido("Athletic vs Real Madrid", "2023-08-12", "21:30", "LaLiga", "Jornada 1 - Inicio de temporada en San Mamés"),
			    new Partido("Athletic vs Betis", "2023-08-27", "21:30", "LaLiga", "Jornada 3 - Partido importante contra Betis"),
			    new Partido("Athletic vs Cádiz", "2023-09-16", "18:30", "LaLiga", "Jornada 5 - Partido accesible"),
			    new Partido("Athletic vs Getafe", "2023-09-27", "19:00", "LaLiga", "Jornada 7 - Rival incómodo"),
			    new Partido("Athletic vs Almería", "2023-10-08", "16:15", "LaLiga", "Jornada 9 - Partido clave por puntos"),
			    new Partido("Athletic vs Valencia", "2023-10-29", "16:15", "LaLiga", "Jornada 11 - Duelo histórico"),
			    new Partido("Athletic vs Celta", "2023-11-05", "18:30", "LaLiga", "Jornada 12 - Importante para consolidarse en casa"),
			    new Partido("Athletic vs Rayo Vallecano", "2023-12-03", "21:00", "LaLiga", "Jornada 15 - Partido exigente"),
			    new Partido("Athletic vs Real Sociedad", "2023-12-17", "16:15", "LaLiga", "Jornada 17 - Derbi vasco"),
			    new Partido("Athletic vs Mallorca", "2024-01-07", "16:15", "LaLiga", "Jornada 19 - Inicio del año en San Mamés"),
			    new Partido("Athletic vs Villarreal", "2024-01-28", "18:30", "LaLiga", "Jornada 21 - Rival europeo"),
			    new Partido("Athletic vs Las Palmas", "2024-02-11", "16:15", "LaLiga", "Jornada 23 - Partido asequible en casa"),
			    new Partido("Athletic vs Girona", "2024-02-25", "18:30", "LaLiga", "Jornada 25 - Rival en buena forma"),
			    new Partido("Athletic vs Sevilla", "2024-03-10", "16:15", "LaLiga", "Jornada 27 - Duelo histórico"),
			    new Partido("Athletic vs Granada", "2024-03-31", "18:30", "LaLiga", "Jornada 29 - Partido para sumar puntos importantes"),
			    new Partido("Athletic vs Osasuna", "2024-04-14", "16:15", "LaLiga", "Jornada 31 - Rival directo"),
			    new Partido("Athletic vs Atlético de Madrid", "2024-04-28", "18:30", "LaLiga", "Jornada 33 - Partido de alto nivel"),
			    new Partido("Athletic vs Barcelona", "2024-05-19", "18:30", "LaLiga", "Jornada 36 - Uno de los partidos más esperados"),
			    new Partido("Athletic vs Espanyol", "2024-05-26", "18:30", "LaLiga", "Jornada 38 - Cierre de temporada en San Mamés")
			};

		Partido masCercano = Admin.buscarPartidoMasCercanoRecursividad(partidos, 0, "2024-03-05");
		System.out.println("Partido más cercano: " + masCercano);
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
