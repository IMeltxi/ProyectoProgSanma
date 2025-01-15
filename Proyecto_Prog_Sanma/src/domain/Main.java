package domain;
import db.BD;
import domain.Entradas.TipoEntrada;
import domain.Usuario.tipoSocio;
import gui.VentanaInicio;

public class Main  {
	public static void main(String[] args) {
		
		// ejecucucion ventana de inicio
        new VentanaInicio(null);

		// Creamos una instancia de la clase Admin para gestionar usuarios y socios
		Admin a = new Admin();
		a.cargarUsuarios();
		a.visualizarSocios();
		//BD.initBD("resources/db/sanMames.db");
		//BD.borrarTablas();
		 // Creamos las tablas necesarias en la base de datos
		//BD.crearTablas();
		
		pruebas();
		
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
