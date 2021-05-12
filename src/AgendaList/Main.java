package AgendaList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {

        boolean error;
        boolean encontrado;
        String opcion; //opción de menú
        int i;

        Scanner teclado = new Scanner(System.in);

        //Variables para manejar los contactos
        String nombre = "", telefono = ""; //variables para leer datos de teclado
        Contacto contacto;

        //Lista de contactos
        List<Contacto> contactos = new ArrayList<Contacto>();

        do {
            System.out.println(String.format("[En la agenda hay %d contactos]",contactos.size()));
            System.out.println("(CARGAR) agenda");
            System.out.println("(GRABAR) agenda");
            System.out.println("(A)ñadir contacto a la agenda");
            System.out.println("(B)orrar contacto");
            System.out.println("(M)odificar contacto");
            System.out.println("(C)onsultar contacto");
            System.out.println("(L)istar todos los contactos");
            System.out.println("(OA) Ordenar por nombre ascendente");
            System.out.println("(OD) Ordenar por nombre descendente");
            System.out.println("(F)in");

            //Se lee opción hasta que coincide con el patrón de la expresión regular
            do {
                error = false;
                System.out.print("Opción>>");
                opcion = teclado.nextLine().toUpperCase();
                if (!opcion.matches("CARGAR|GRABAR|A|B|M|C|L|N|OA|OD|F")) {
                    error = true;
                    System.out.println("No existe esa opción");
                }
            } while (error);

            System.out.println("");
            
            // En función de la opción se lanza la operación correspondiente
            switch (opcion) {
                case "CARGAR": //Carga la agenda
                    contactos = SerializarList.cargarAgenda();
                    break;

                case "GRABAR": //Graba la agenda
                    SerializarList.grabarAgenda(contactos);
                    break;

                case "A": //Añade contacto
                    System.out.print("Nombre:");
                    nombre = teclado.nextLine();
                    System.out.print("Teléfono:");
                    telefono = teclado.nextLine();
                    contacto = new Contacto(nombre, telefono);
                    contactos.add(contacto);
                    break;

                case "C": //Consulta contacto
                    System.out.print("Nombre:");
                    nombre = teclado.nextLine();
                    i = 0;
                    encontrado = false;
                    do {
                        if (contactos.get(i).getNombre().equals(nombre)) {
                            encontrado = true;
                            telefono = contactos.get(i).getTelefono();
                        } else {
                            i++;
                        }
                    } while (i < contactos.size() && !encontrado);

                    if (encontrado) {
                        System.out.println("Teléfono:" + telefono);
                    } else {
                        System.out.printf("El contacto %s no existe.\n", nombre);
                    }

                    break;

                case "M": //Modifica contacto
                    System.out.print("Nombre:");
                    nombre = teclado.nextLine();
                    i = 0;
                    encontrado = false;
                    do {
                        if (contactos.get(i).getNombre().equals(nombre)) {
                            encontrado = true;
                            telefono = contactos.get(i).getTelefono();
                        } else {
                            i++;
                        }
                    } while (i < contactos.size() && !encontrado);

                    if (encontrado) {
                        System.out.println("El teléfono actual es:" + telefono);
                        System.out.print("Introduce el nuevo teléfono:");
                        contactos.get(i).setTelefono(teclado.nextLine());
                    } else {
                        System.out.printf("El contacto %s no existe.\n", nombre);
                    }

                    break;

                case "B": //Borra contacto
                    System.out.print("Nombre:");
                    nombre = teclado.nextLine();
                    i = 0;
                    encontrado = false;
                    do {
                        if (contactos.get(i).getNombre().equals(nombre)) {
                            encontrado = true;
                            nombre = contactos.get(i).getNombre();
                        } else {
                            i++;
                        }
                    } while (i < contactos.size() && !encontrado);

                    if (encontrado) {
                        contactos.remove(i);
                        System.out.println("Se ha borrado al contacto:" + nombre);
                    } else {
                        System.out.printf("El contacto %s no existe.\n", nombre);
                    }

                    break;

                case "L": //Listar todos los contactos
                    for (i = 0; i < contactos.size(); ++i) {
                        System.out.println(contactos.get(i).toString());
                    }

                    //Otra forma de hacerlo es con un iterador
                    //Iterator<Contacto> iterador = contactos.iterator();
                    //while (iterador.hasNext()) {
                    //    System.out.println(iterador.next().toString());
                    //}
                    break;

                case "OA": //Ordenar por nombre ascendente
                    Collections.sort(contactos, new ComparadorContactosPorNombreAsc());
                    for (Contacto c : contactos) {
                        System.out.println(c.toString());
                    }
                    break;

                case "OD": //Ordenar por nombre descendente
                    Collections.sort(contactos, new ComparadorContactosPorNombreDesc());
                    for (Contacto c : contactos) {
                        System.out.println(c.toString());
                    }
                    break;
            }
            
            System.out.println("");
            
        } while (!opcion.equals("F"));
    }
}


//Clase para comparar por nombre ascendente
class ComparadorContactosPorNombreAsc implements Comparator<Contacto> {

    @Override
    public int compare(Contacto c1, Contacto c2) {
        return c1.getNombre().compareTo(c2.getNombre());
    }
}

//Calse para comparar por nombre descendente
class ComparadorContactosPorNombreDesc implements Comparator<Contacto> {

    @Override
    public int compare(Contacto c1, Contacto c2) {
        return c2.getNombre().compareTo(c1.getNombre());
    }
}