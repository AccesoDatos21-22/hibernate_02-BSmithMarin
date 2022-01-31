package entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Modulo implements Serializable {

    private int id;

    private String nombre;

    private int curso;

    private float creditos;

    private Set<Profesor> profesores = new HashSet<>();

    public Modulo(String nombre, int curso, float creditos) {
        this.nombre = nombre;
        this.curso = curso;
        this.creditos = creditos;
    }

    public Modulo() {
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", curso=" + curso +
                ", creditos=" + creditos +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }
}

