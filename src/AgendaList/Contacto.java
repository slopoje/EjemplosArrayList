package AgendaList;

import java.io.Serializable;

public class Contacto implements Serializable {

    private String nombre;
    private String telefono;
    private static final long serialVersionUID = 1L;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre:" + this.nombre + " Teléfono:" + this.telefono;
    }
}
