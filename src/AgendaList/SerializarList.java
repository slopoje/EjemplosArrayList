package AgendaList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializarList {

    public static void grabarAgenda(List<Contacto> contactos) {

        System.out.println("Grabando agenda... ");
        try ( FileOutputStream fos = new FileOutputStream("agenda.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(contactos);
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: Fichero no encontrado.");
        } catch (IOException e) {
            System.out.println("\nERROR: No se puede acceder al fichero.");
        }

    }

    public static List<Contacto> cargarAgenda() {

        List<Contacto> contactos = null;

        System.out.print("Cargando agenda... ");
        try ( FileInputStream fis = new FileInputStream("agenda.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

            contactos = (List<Contacto>) ois.readObject();
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
