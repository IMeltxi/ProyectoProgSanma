package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import domain.Entradas.Localidad;
import domain.Entradas.TipoEntrada;

public class BD {
	public enum tipoSocio {
        SOCIOMENSUAL, SOCIO, VIP, GAZTEABONO
    }
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
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL,"
                    + "apellido TEXT NOT NULL,"
                    + "tlf TEXT,"
                    + "email TEXT UNIQUE NOT NULL,"
                    + "contrasena TEXT NOT NULL,"
                    + "numero_socio INTEGER NOT NULL,"
                    + "fechNac DATE NOT NULL,"
                    + "tiposocio TEXT NOT NULL"
                    + ")";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            logger.info("Tabla Usuarios creada correctamente");

            // Tabla CategoriasEntradas
            sql = "CREATE TABLE IF NOT EXISTS CategoriasEntradas ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre_categoria TEXT NOT NULL,"
                    + "precio REAL NOT NULL"
                    + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            logger.info("Tabla CategoriasEntradas creada correctamente");

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


    public static void insertarUsuario(String nombre, String apellido, String tlf, String email,
                                       String contrasena, int numeroSocio, String fechNac, tipoSocio tiposocio) {
        String sql = "INSERT INTO Usuarios (nombre, apellido, tlf, email, contrasena, numero_socio, fechNac, tiposocio) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, tlf);
            ps.setString(4, email);
            ps.setString(5, contrasena);
            ps.setInt(6, numeroSocio);
            ps.setString(7, fechNac); // Fecha en formato 'YYYY-MM-DD'
            ps.setString(8, tiposocio.name()); // Mapeamos el enum a su nombre en texto
            ps.execute();
            ps.close();
            logger.info("Usuario insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean existeID(int idU) {
        String sql = "SELECT 1 FROM Usuarios WHERE id = ?";
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

}