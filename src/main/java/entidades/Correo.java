package entidades;

import java.io.Serializable;

public class Correo implements Serializable {

    private int id;

    private String direccion;

    private String proveedor;

    private Profesor profesor;

    public Correo(String direccion, String proveedor) {
        this.direccion = direccion;
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Correo{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }

    public Correo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}

