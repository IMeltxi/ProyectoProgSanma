package db;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import domain.Usuario;
import domain.Entradas.Localidad;
import domain.Entradas.TipoEntrada;
import domain.Usuario.tipoSocio;

public class BD {

    //Añadimos un logger para que nos muestre los errores
    private static final Logger logger = Logger.getLogger(BD.class.getName());
    private static Connection con;

    public static void initBD(String nombreBD)  {
        con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
            logger.info("Conexión establecida con la base de datos");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeBD() {
        if (con != null) {
            try {
                con.close();
                logger.info("Conexión cerrada con la base de datos");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void crearTablas() {
        try {
            // Tabla Usuarios
        	String sql = "CREATE TABLE IF NOT EXISTS Usuarios ("
                    + "Numero_socio INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "Tiposocio TEXT NOT NULL"
                    + "Nombre TEXT NOT NULL,"
                    + "Apellido TEXT NOT NULL,"
                    + "Telefono TEXT,"
                    + "FechaNacimiento DATE NOT NULL,"
                    + "Email TEXT UNIQUE NOT NULL,"
                    + "Contrasena TEXT NOT NULL,"
                    + ")";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            logger.info("Tabla Usuarios creada correctamente");

            // Tabla Localizaciones
            sql = "CREATE TABLE IF NOT EXISTS Localizaciones ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre_tribuna TEXT NOT NULL"
                    + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            logger.info("Tabla Localizaciones creada correctamente");

            // Tabla Entradas
            sql = "CREATE TABLE IF NOT EXISTS Entradas ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "usuario_id INTEGER,"
                    + "categoria_id INTEGER,"
                    + "localizacion_id INTEGER,"
                    + "fecha_compra DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),"
                    + "FOREIGN KEY (categoria_id) REFERENCES CategoriasEntradas(id),"
                    + "FOREIGN KEY (localizacion_id) REFERENCES Localizaciones(id)"
                    + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            logger.info("Tabla Entradas creada correctamente");

            // Tabla Partidos
            sql = "CREATE TABLE IF NOT EXISTS Partidos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombrePart TEXT NOT NULL,"
                    + "fecha TEXT NOT NULL,"
                    + "hora TEXT NOT NULL,"
                    + "competicion TEXT NOT NULL,"
                    + "descripcion TEXT,"
                    + "jornada INTEGER"
                    + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            logger.info("Tabla Partidos creada correctamente");

            logger.info("Todas las tablas creadas correctamente");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    


    public static void insertarEntradas(TipoEntrada tipoEntrada, float precio) {
        String sql = "INSERT OR IGNORE INTO CategoriasEntradas (nombre_categoria, precio) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipoEntrada.name()); // Mapeamos el enum a su nombre en texto
            ps.setFloat(2, precio); // Precio de la entrada
            ps.execute();
            ps.close();
            logger.info("Entrada insertada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertarLocalizaciones(Localidad localidad) {
        String sql = "INSERT OR IGNORE INTO Localizaciones (nombre_tribuna) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, localidad.name()); // Mapeamos el enum a su nombre en texto
            ps.execute();
            ps.close();
            logger.info("Localización insertada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertarUsuario(tipoSocio tiposocio,String nombre, String apellido, String tlf, String fechNacStr, String email, String contrasena) {
        String sql = "INSERT INTO Usuarios (TipoSocio,Nombre, Apellido, Tlf,FechNac, Email, Contrasena) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tiposocio.name());
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, tlf);
            ps.setString(5, fechNacStr);
            ps.setString(6, email);
            ps.setString(7, contrasena); // Fecha en formato 'YYYY-MM-DD'
            ps.execute();
            ps.close();
            logger.info("Usuario insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean existeID(int idU) {
        String sql = "SELECT 1 FROM Usuarios WHERE Numero_Socio = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idU);
            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next(); // Devuelve true si hay un registro
            rs.close();
            ps.close();
            return existe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Usuario buscarUsario(Connection con, String CorreoElectronico) {
		String sql = String.format("SELECT * FROM Usuario WHERE email = '%s'", CorreoElectronico);
		Usuario usuario= null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			if(rs.next()) {
				tipoSocio tiposocio = tipoSocio.valueOf(rs.getString("Tiposocio").toUpperCase());
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				String fNac = rs.getString("FechaNacimiento");
				String tlf = rs.getString("Telefono");
				String CorreoEle = rs.getString("Email");
				String contrasenia = rs.getString("Contrasena");

				usuario = new Usuario(tiposocio, nombre, apellido, tlf, fNac, CorreoEle, contrasenia);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	

}