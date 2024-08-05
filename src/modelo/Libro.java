/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author DELL
 */
public class Libro {
    private int idLibro;
    private String titulo;
    private int anioPublicacion;
    private int idAutor;

    public Libro() {
    }

    public Libro(int idLibro, String titulo, int anioPublicacion, int idAutor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.idAutor = idAutor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
    
    
    @Override
    public String toString() {
        return "********DATOS DEL LIBRO********\n" +
               "Titulo del libro: " + getTitulo() + "\n" +
               "Año de publicación: " + getAnioPublicacion() + "\n" +
               "Id del Autor: " + getIdAutor();
    }

}
