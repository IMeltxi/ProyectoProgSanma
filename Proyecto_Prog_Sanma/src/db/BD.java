package db;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                    + "Tiposocio TEXT NOT NULL,"
                    + "Nombre TEXT NOT NULL,"
                    + "Apellido TEXT NOT NULL,"
                    + "Telefono TEXT,"
                    + "FechaNacimiento DATE NOT NULL,"
                    + "Email TEXT UNIQUE NOT NULL,"
                    + "Contrasena TEXT NOT NULL"
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
            ps.setString(7, contrasena);
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
    public static Usuario buscarUsuario(Connection con, String correoElectronico) {
        String sql = "SELECT * FROM Usuarios WHERE Email = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correoElectronico);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtenemos los valores de la base de datos
                    tipoSocio tiposocio = tipoSocio.valueOf(rs.getString("Tiposocio").toUpperCase());;
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    String fNac = rs.getString("FechaNacimiento"); 
                    String tlf = rs.getString("Telefono");
                    String email = rs.getString("Email");
                    String contrasena = rs.getString("Contrasena");

                    // Construimos el objeto Usuario
                    usuario = new Usuario(tiposocio, nombre, apellido, tlf, fNac, email, contrasena);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

	
    public static void actualizarUsuario(int id, tipoSocio tiposocio, String nombre, String apellido, String telefono, String fechaNacimiento, String email, String contrasena) {
        String sql = "UPDATE Usuarios SET Tiposocio = ?, Nombre = ?, Apellido = ?, Telefono = ?, FechaNacimiento = ?, Email = ?, Contrasena = ? " +
                     "WHERE Numero_socio = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Asignamos los valores a los parámetros
            ps.setString(1, tiposocio.name()); // Enum tipoSocio
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, telefono);
            ps.setString(5, fechaNacimiento);
            ps.setString(6, email);
            ps.setString(7, contrasena);
            ps.setInt(8, id); // ID del usuario que queramos modificar

            // Ejecutamos la actualizacion
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Usuario con ID " + id + " actualizado correctamente.");
            } else {
                logger.warning("No se encontró un usuario con el ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     //funcion para eliminar usuarios por id
    public static void eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuarios WHERE Numero_socio = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Usuario eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
   	 * Inserta usuario en la base de datos
   	 * @param con Conexion de la base de datos
   	 * @param usuario Usuario que queremos insertar en la base de datos
   	 */
    public static void insertarUsuario(Connection con, Usuario usuario) {
        if (buscarUsuario(con, usuario.getEmail()) == null) {
            String sql = "INSERT INTO Usuarios (Tiposocio, Nombre, Apellido, Telefono, FechaNacimiento, Email, Contrasena) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, usuario.getTiposocio().name());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getApellido());
                ps.setString(4, usuario.getTlf());
                ps.setString(5, usuario.getFechNacStr()); // Asegúrate de que el formato sea compatible con DATE en tu BD.
                ps.setString(6, usuario.getEmail());
                ps.setString(7, usuario.getContrasena());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
         // Muestra las entradas junto con los datos de los usuarios que las compraron.
    public static void mostrarEntradasConUsuarios() {
        // Consulta SQL que combina datos de las tablas Entradas y Usuarios
        String sql = "SELECT e.id, u.Nombre, u.Apellido, e.fecha_compra " +
                     "FROM Entradas e " +
                     "JOIN Usuarios u ON e.usuario_id = u.Numero_socio";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            // Iteramos sobre el resultado de la consulta
            while (rs.next()) {
                // Obtenemos y mostramos los datos de cada fila
                System.out.println("ID Entrada: " + rs.getInt("id") + 
                                   ", Nombre: " + rs.getString("Nombre") + 
                                   ", Apellido: " + rs.getString("Apellido") + 
                                   ", Fecha Compra: " + rs.getString("fecha_compra"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra un error en caso de fallo en la consulta
        }
    }

    /**
     * Muestra las entradas filtradas por una categoría específica.
     *
     * @param categoria Nombre de la categoría a filtrar.
     */
    public static void mostrarEntradasPorCategoria(String categoria) {
        // Consulta SQL que combina Entradas, Usuarios y CategoriasEntradas
        String sql = "SELECT e.id, u.Nombre, e.fecha_compra, c.nombre_categoria " +
                     "FROM Entradas e " +
                     "JOIN Usuarios u ON e.usuario_id = u.Numero_socio " +
                     "JOIN CategoriasEntradas c ON e.categoria_id = c.id " +
                     "WHERE c.nombre_categoria = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria); // Asigna el nombre de la categoría al parámetro de la consulta
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta

            // Iteramos sobre el resultado de la consulta
            while (rs.next()) {
                // Obtenemos y mostramos los datos de cada fila
                System.out.println("ID Entrada: " + rs.getInt("id") +
                                   ", Usuario: " + rs.getString("Nombre") +
                                   ", Categoría: " + rs.getString("nombre_categoria") +
                                   ", Fecha: " + rs.getString("fecha_compra"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra un error en caso de fallo en la consulta
        }
    }

    
    
    public static List<Usuario> obtenerListaUsario(Connection con){
		String sql = "SELECT * FROM Usuario";
		List<Usuario> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
                // Obtenemos los valores de la base de datos
                tipoSocio tiposocio = tipoSocio.valueOf(rs.getString("Tiposocio").toUpperCase());;
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String fNac = rs.getString("FechaNacimiento"); // Asegúrate de que este formato sea válido
                String tlf = rs.getString("Telefono");
                String email = rs.getString("Email");
                String contrasena = rs.getString("Contrasena");

                // Construimos el objeto Usuario
                Usuario usuario = new Usuario(tiposocio, nombre, apellido, tlf, fNac, email, contrasena);
               	l.add(usuario);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

}