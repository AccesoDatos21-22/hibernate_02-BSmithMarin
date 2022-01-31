package entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Profesor implements Serializable {

    private int id;

    private String nombre;

    private String ape1;

    private String ape2;

    private Direccion direccion;

    private Set<Modulo> modulos = new HashSet<>();

    private List<Correo> correos;

    public Profesor(){}

    public Profesor(String nombre, String ape1, String ape2, Direccion direccion) {
        //this.id = id;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ape1='" + ape1 + '\'' +
                ", ape2='" + ape2 + '\'' +
                ", direccion=" + direccion +
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

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Set<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(Set<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }
}
