package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Libro;

public class ControladorLibro {

    private Libro libro;
    private ConexionBDD conexion = new ConexionBDD();
    private Connection connection = conexion.conectar();
    private PreparedStatement ejecutar;
    private ResultSet resultado;

    public void crearLibro(Libro li, int idAutor) {
        String consultaSQL = "INSERT INTO Libro (titulo, anioPublicacion, idAutor) VALUES (?, ?, ?)";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, li.getTitulo());
            ejecutar.setInt(2, li.getAnioPublicacion());
            ejecutar.setInt(3, idAutor);
            int resultado = ejecutar.executeUpdate();
            if (resultado > 0) {
                System.out.println("El libro fue creado con éxito");
            } else {
                System.out.println("Ingrese los datos de manera correcta");
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador del Sistema para que resuelva este error: " + e.getMessage());
        } finally {
            try {
                if (ejecutar != null) ejecutar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
    }

    public Libro buscarLibro(int id) {
        Libro li = null;
        String consultaSQL = "SELECT * FROM Libro WHERE id = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, id);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                li = new Libro(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getInt("anioPublicacion"),
                    resultado.getInt("idAutor")
                );
            } else {
                System.out.println("No se encontró el libro con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador del Sistema para que resuelva este error: " + e.getMessage());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (ejecutar != null) ejecutar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return li;
    }

    public void actualizarLibro(Libro li, int id) {
        String consultaSQL = "UPDATE Libro SET titulo = ?, anioPublicacion = ?, idAutor = ? WHERE id = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, li.getTitulo());
            ejecutar.setInt(2, li.getAnioPublicacion());
            ejecutar.setInt(3, li.getIdAutor());
            ejecutar.setInt(4, id);
            int resultado = ejecutar.executeUpdate();
            if (resultado > 0) {
                System.out.println("El libro fue actualizado con éxito");
            } else {
                System.out.println("No se encontró el libro con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador del Sistema para que resuelva este error: " + e.getMessage());
        } finally {
            try {
                if (ejecutar != null) ejecutar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
    }

    public ArrayList<Libro> listarLibros() {
        ArrayList<Libro> listaLibros = new ArrayList<>();
        String consultaSQL = "SELECT * FROM Libro";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                Libro li = new Libro(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getInt("anioPublicacion"),
                    resultado.getInt("idAutor")
                );
                listaLibros.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador del Sistema para que resuelva este error: " + e.getMessage());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (ejecutar != null) ejecutar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return listaLibros;
    }

    public void eliminarLibro(int id) {
        String consultaSQL = "DELETE FROM Libro WHERE id = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, id);
            int resultado = ejecutar.executeUpdate();
            if (resultado > 0) {
                System.out.println("El libro fue eliminado con éxito");
            } else {
                System.out.println("No se encontró el libro con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador del Sistema para que resuelva este error: " + e.getMessage());
        } finally {
            try {
                if (ejecutar != null) ejecutar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
    }

    public ArrayList<Libro> buscarLibrosPorAutor(int idAutor) {
        ArrayList<Libro> listaLibros = new ArrayList<>();
        String consultaSQL = "SELECT * FROM Libro WHERE idAutor = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, idAutor);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                Libro li = new Libro(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getInt("anioPublicacion"),
                    resultado.getInt("idAutor")
                );
                listaLibros.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el Administrador del Sistema para que resuelva este error: " + e.getMessage());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (ejecutar != null) ejecutar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return listaLibros;
    }
}
