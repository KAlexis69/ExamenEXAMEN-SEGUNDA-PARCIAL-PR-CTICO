package vista;

import controlador.ConexionBDD;
import modelo.Autor;
import modelo.Libro;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        ConexionBDD conexionBDD = new ConexionBDD();

        while (option != 0) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear libro");
            System.out.println("2. Buscar libro por ID");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Listar todos los libros");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Buscar libros por autor");
            System.out.println("7. Crear autor");
            System.out.println("8. Buscar autor por ID");
            System.out.println("9. Actualizar autor");
            System.out.println("10. Listar todos los autores");
            System.out.println("11. Eliminar autor");
            System.out.println("0. Salir");

            option = scanner.nextInt();
            scanner.nextLine();

            try (Connection conn = conexionBDD.conectar()) {
                if (conn == null) {
                    System.out.println("No se pudo conectar a la base de datos.");
                    continue;
                }

                if (option == 1) {
                    System.out.println("Ingrese los datos del libro:");
                    System.out.print("Título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("ID del autor (debe existir en la base de datos): ");
                    int autorId = scanner.nextInt();
                    scanner.nextLine();

                    String sql = "INSERT INTO Libro (titulo, anioPublicacion, idAutor) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, titulo);
                        pstmt.setInt(2, 2023); // Ajusta el año según sea necesario
                        pstmt.setInt(3, autorId);
                        pstmt.executeUpdate();
                        System.out.println("Libro creado con éxito.");
                    }
                } else if (option == 2) {
                    System.out.println("Ingrese el ID del libro:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    String sql = "SELECT * FROM Libro WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            System.out.println("Libro encontrado: Título: " + rs.getString("titulo"));
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                    }
                } else if (option == 3) {
                    System.out.println("Ingrese el ID del libro a actualizar:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nuevo título del libro: ");
                    String nuevoTitulo = scanner.nextLine();

                    String sql = "UPDATE Libro SET titulo = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nuevoTitulo);
                        pstmt.setInt(2, id);
                        int rowsUpdated = pstmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Libro actualizado con éxito.");
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                    }
                } else if (option == 4) {
                    System.out.println("Listado de todos los libros:");
                    String sql = "SELECT * FROM Libro";
                    try (Statement stmt = conn.createStatement()) {
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id") + ", Título: " + rs.getString("titulo"));
                        }
                    }
                } else if (option == 5) {
                    System.out.println("Ingrese el ID del libro a eliminar:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    String sql = "DELETE FROM Libro WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        int rowsDeleted = pstmt.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Libro eliminado con éxito.");
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                    }
                } else if (option == 6) {
                    System.out.println("Ingrese el nombre del autor:");
                    String nombreAutor = scanner.nextLine();

                    String sql = "SELECT * FROM Libro WHERE idAutor IN (SELECT id FROM Autor WHERE nombre = ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nombreAutor);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {
                            System.out.println("Libro encontrado: ID: " + rs.getInt("id") + ", Título: " + rs.getString("titulo"));
                        }
                    }
                } else if (option == 7) {
    System.out.println("Ingrese los datos del autor:");
    System.out.print("Nombre del autor: ");
    String nombre = scanner.nextLine();
    
    System.out.print("Nacionalidad del autor: ");
    String nacionalidad = scanner.nextLine();

    String sql = "INSERT INTO Autor (nombre, nacionalidad) VALUES (?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nombre);
        pstmt.setString(2, nacionalidad);
        pstmt.executeUpdate();
        System.out.println("Autor creado con éxito.");
    } catch (SQLException e) {
        System.out.println("Error al crear el autor: " + e.getMessage());
    }


                } else if (option == 8) {
                    System.out.println("Ingrese el ID del autor:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    String sql = "SELECT * FROM Autor WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            System.out.println("Autor encontrado: ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
                        } else {
                            System.out.println("Autor no encontrado.");
                        }
                    }
                } else if (option == 9) {
                    System.out.println("Ingrese el ID del autor a actualizar:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nuevo nombre del autor: ");
                    String nuevoNombre = scanner.nextLine();

                    String sql = "UPDATE Autor SET nombre = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nuevoNombre);
                        pstmt.setInt(2, id);
                        int rowsUpdated = pstmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Autor actualizado con éxito.");
                        } else {
                            System.out.println("Autor no encontrado.");
                        }
                    }
                } else if (option == 10) {
                    System.out.println("Listado de todos los autores:");
                    String sql = "SELECT * FROM Autor";
                    try (Statement stmt = conn.createStatement()) {
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
                        }
                    }
                } else if (option == 11) {
                    System.out.println("Ingrese el ID del autor a eliminar:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    String sql = "DELETE FROM Autor WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        int rowsDeleted = pstmt.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Autor eliminado con éxito.");
                        } else {
                            System.out.println("Autor no encontrado.");
                        }
                    }
                } else if (option == 0) {
                    System.out.println("Saliendo...");
                } else {
                    System.out.println("Opción no válida, intente de nuevo.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error de base de datos.");
            }
        }

        scanner.close();
    }
}
