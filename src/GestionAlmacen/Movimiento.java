package GestionAlmacen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Movimiento implements Serializable {

    private int numeroMovimiento = 0;
    private String tipoMovimiento;
    private String nombreArticulo;
    private int cantidad;
    private static final long serialVersionUID = 1L;

    /**
     * Método constructor de Movimiento. Se pasan por parámetro el tipo de
     * movimiento, el nombre del artículo y las unidades que entran o salen, el
     * número de movimiento se obtiene de la secuencia por el método
     * siguienteMovimiento().
     *
     * @param tipoMovimiento tipo de movimiento de entrada o de salida.
     * @param nombreArticulo nombre de artículo del movimiento.
     * @param cantidad unidades del artículo que entran o salen
     */
    public Movimiento(String tipoMovimiento, String nombreArticulo, int cantidad) {
        this.tipoMovimiento = tipoMovimiento;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;

        //Llama al método que va generando el número secuencial sin repetición
        this.numeroMovimiento = this.siguienteMovimiento();
    }

    /**
     * Gestiona que el número de movimiento se vaya generando de forma
     * secuencial sin repetirse, utilizando un fichero serializado para guardar
     * el último número que se generó. Si el fichero no existe (la primera vez)
     * lo crea y graba el número 1: este es el primer movimiento. El resto de
     * veces lee el último número de movimiento del fichero, le suma 1 y vuelve
     * a guardarlo.
     *
     * @return El siguiente número de movimiento en la secuencia.
     */
    private int siguienteMovimiento() {

        int siguienteMovimiento = 1;

        File f = new File("ultimoMovimiento.dat");
        if (f.exists()) {

            try ( FileInputStream fis = new FileInputStream("ultimoMovimiento.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

                siguienteMovimiento = (int) ois.readObject();
                siguienteMovimiento++;

            } catch (FileNotFoundException e) {
                System.out.println("\nERROR: Fichero no encontrado.");
            } catch (IOException e) {
                System.out.println("\nERROR: No se puede acceder al fichero.");
            } catch (ClassNotFoundException e) {
                System.out.println("\nERROR: No se encuentra la clase.");
            }
        }
        
        try ( FileOutputStream fos = new FileOutputStream("ultimoMovimiento.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(siguienteMovimiento);

        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: Fichero no encontrado.");
        } catch (IOException e) {
            System.out.println("\nERROR: No se puede acceder al fichero.");
        }

        return siguienteMovimiento;
    }

    /**
     * Método get del número de movimiento.
     *
     * @return el número de movimiento.
     */
    public int getNumeroMovimiento() {
        return this.numeroMovimiento;
    }

    /**
     * Método get del tipo de movimiento.
     *
     * @return el tipo de movimiento (entrada o salida)
     */
    public String getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    /**
     * Método get del nombre del artículo.
     *
     * @return el nombre del artículo.
     */
    public String getNombreArticulo() {
        return this.nombreArticulo;
    }

    /**
     * Método get de la cantidad de artículos.
     *
     * @return cantidad de unidades del movimiento.
     */
    public int getCantidad() {
        return this.cantidad;
    }

    /**
     * Método set del tipo de movimiento.
     *
     * @param el tipo de movimiento
     */
    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * Método set del nombre del artículo.
     *
     * @param nombreArticulo El nombre del artículo del movimiento.
     */
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    /**
     * Método set de la cantidad de unidades del movimiento
     *
     * @param cantidad La cantidad de unidades del movimiento.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método toString() sobrescrito.
     *
     * @return devuelve un string con los datos del movimiento.
     */
    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder();

        mensaje.append(this.getNumeroMovimiento() + " / ").
                append(this.getTipoMovimiento() + " / ").
                append(this.getCantidad() + " / ").
                append(this.getNombreArticulo());
        return mensaje.toString();
    }
}
