package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Direccion implements Serializable {

    private String calle;

    private String poblacion;

    private String provincia;

    private int numero;

    private int id;

    public Direccion(){}

    public Direccion(String calle, String poblacion, String provincia, int numero) {
        //this.id = id;
        this.calle = calle;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return numero == direccion.numero && Objects.equals(calle, direccion.calle) && Objects.equals(poblacion, direccion.poblacion) && Objects.equals(provincia, direccion.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calle, poblacion, provincia, numero);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", provincia='" + provincia + '\'' +
                ", numero=" + numero +
                ", id=" + id +
                '}';
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

