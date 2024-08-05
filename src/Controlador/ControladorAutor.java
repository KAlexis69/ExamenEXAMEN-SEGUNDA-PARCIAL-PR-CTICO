package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Autor;

public class ControladorAutor {

    private ConexionBDD conexion = new ConexionBDD();
    private Connection connection = conexion.conectar();
    private PreparedStatement ejecutar;
    private ResultSet resultado;

    public void crearAutor(Autor autor) {
        String consultaSQL = "INSERT INTO Autor (nombre, nacionalidad) VALUES (?, ?)";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, autor.getNombre());
            ejecutar.setString(2, autor.getNacionalidad());
            int resultado = ejecutar.executeUpdate();
            if (resultado > 0) {
                System.out.println("El autor fue creado con éxito");
            } else {
                System.out.println("No se pudieron insertar los datos del autor");
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

    public Autor buscarAutor(int id) {
        Autor autor = null;
        String consultaSQL = "SELECT * FROM Autor WHERE idAutor = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, id);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                autor = new Autor(
                    resultado.getInt("idAutor"),
                    resultado.getString("nombre"),
                    resultado.getString("nacionalidad")
                );
            } else {
                System.out.println("No se encontró el autor con ID: " + id);
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
        return autor;
    }

    public void actualizarAutor(Autor autor) {
        String consultaSQL = "UPDATE Autor SET nombre = ?, nacionalidad = ? WHERE idAutor = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, autor.getNombre());
            ejecutar.setString(2, autor.getNacionalidad());
            ejecutar.setInt(3, autor.getIdAutor());
            int resultado = ejecutar.executeUpdate();
            if (resultado > 0) {
                System.out.println("El autor fue actualizado con éxito");
            } else {
                System.out.println("No se encontró el autor con ID: " + autor.getIdAutor());
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

    public ArrayList<Autor> listarAutores() {
        ArrayList<Autor> listaAutores = new ArrayList<>();
        String consultaSQL = "SELECT * FROM Autor";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                Autor autor = new Autor(
                    resultado.getInt("idAutor"),
                    resultado.getString("nombre"),
                    resultado.getString("nacionalidad")
                );
                listaAutores.add(autor);
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
        return listaAutores;
    }

    public void eliminarAutor(int id) {
        String consultaSQL = "DELETE FROM Autor WHERE idAutor = ?";
        try {
            ejecutar = connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, id);
            int resultado = ejecutar.executeUpdate();
            if (resultado > 0) {
                System.out.println("El autor fue eliminado con éxito");
            } else {
                System.out.println("No se encontró el autor con ID: " + id);
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
}