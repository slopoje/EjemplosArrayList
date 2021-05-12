package GestionAlmacen;

import AgendaList.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializarList {

    public static void grabarMovimientos(List<Movimiento> movimientos) {

        System.out.println("Grabando movimientos... ");
        try ( FileOutputStream fos = new FileOutputStream("movimientos.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(movimientos);
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: Fichero no encontrado.");
        } catch (IOException e) {
            System.out.println("\nERROR: No se puede acceder al fichero.");
        }

    }

    public static List<Movimiento> cargarMovimientos() {

        List<Movimiento> contactos = null;

        System.out.print("Cargando movimientos... ");
        try ( FileInputStream fis = new FileInputStream("movimientos.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

            contactos = (List<Movimiento>) ois.readObject();
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: Fichero no encontrado.");
        } catch (IOException e) {
            System.out.println("\nERROR: No se puede acceder al fichero.");
        } catch (ClassNotFoundException e) {
            System.out.println("\nERROR: No se encuentra la clase.");
        }

        return contactos;

    }

}
