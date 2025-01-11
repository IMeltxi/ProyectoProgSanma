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
		BD.initBD("resources/db/sanMames.db");
		 // Creamos las tablas necesarias en la base de datos
		BD.crearTablas();
		
		//pruebas();//
		
		//Funcion recursiva
		Partido[] partidos = {
			    new Partido("Athletic vs Getafe CF","2024-08-12","21:30",1),
			    new Partido("Athletic vs Valencia","2024-08-27","21:30",3               ),
			    new Partido("Athletic vs Atletico de Madrid","2024-09-16","18:30",5     ),
			    new Partido("Athletic vs RC Celta de Vigo","2024-09-27","19:00",7       ),
			    new Partido("Athletic vs Sevilla FC","2024-10-08","16:15",9             ),
			    new Partido("Athletic vs RCD Espanyol","2024-10-29","16:15",11          ),
			    new Partido("Athletic vs Betis","2024-11-05","18:30",12                 ),
			    new Partido("Athletic vs Real Sociedad","2024-12-03","21:00",15         ),
			    new Partido("Athletic vs Real Madrid","2024-12-17","16:15",17           ),
			    new Partido("Athletic vs Villareal","2025-01-07","16:15",19             ),
			    new Partido("Athletic vs Leganés","2025-01-28","18:30",21               ),
			    new Partido("Athletic vs Girona FC","2025-02-11","16:15",23             ),
			    new Partido("Athletic vs Real Valladolid CF","2025-02-25","18:30",25    ),
			    new Partido("Athletic vs RCD Mallorca","2025-03-10","16:15",27          ),
			    new Partido("Athletic vs CA Osasuna","2025-03-31","18:30",29            ),
			    new Partido("Athletic vs Rayo Vallecano","2025-04-14","16:15",31        ),
			    new Partido("Athletic vs UD Las Palmas","2025-04-28","18:30",33         ),
			    new Partido("Athletic vs Deportivo Alavés","2025-05-19","18:30",36      ),
			    new Partido("Athletic vs FC Barcelona","2025-05-26","18:30",38          )
			};

		Partido masCercano = Admin.buscarPartidoMasCercanoRecursividad(partidos, 0, "2024-03-30");
		System.out.println("Partido más cercano: " + masCercano);
	}
	
	/*public static void pruebas() {
		try {
		    // Inicializar base de datos y crear tablas
		    BD.initBD("resources/db/sanMames.db");
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
	} */
}
