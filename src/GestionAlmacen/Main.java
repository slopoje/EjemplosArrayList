package GestionAlmacen;

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

        //Variables para manejar los movimientos
        int numeroMovimiento;
        String tipoMovimiento;
        String nombreArticulo;
        int cantidad;

        Movimiento movimiento;

        //Lista para ir almacenando los movimientos
        List<Movimiento> movimientos = new ArrayList<Movimiento>();

        do {
            System.out.println("(CARGAR) movimientos");
            System.out.println("(GRABAR) movimientos");
            System.out.println("(A)ñadir movimiento");
            System.out.println("(B)orrar movimiento");
            System.out.println("(M)odificar movimiento");
            System.out.println("(C)onsultar movimiento");
            System.out.println("(L)istar todos los movimientos");
            System.out.println("(LON) Listar movimientos ordenador por número");
            System.out.println("(LOA) Listar movimientos ordenados por nombre de articulo");
            System.out.println("(S)tock disponible de un artículo");
            System.out.println("(F)in");

            //Se lee opción hasta que coincide con el patrón de la expresión regular
            do {
                error = false;
                System.out.print("Opción>>");
                opcion = teclado.nextLine().toUpperCase();
                if (!opcion.matches("CARGAR|GRABAR|A|B|M|C|L|LON|LOA|S|F")) {
                    error = true;
                    System.out.println("No existe esa opción");
                }
            } while (error);

            System.out.println("");

            // En función de la opción se lanza la operación correspondiente
            switch (opcion) {
                case "CARGAR": //Carga los movimientos
                    movimientos = SerializarList.cargarMovimientos();
                    break;

                case "GRABAR": //Graba los movimientos
                    SerializarList.grabarMovimientos(movimientos);
                    break;

                case "A": //Añade movimiento
                    do {
                        error = false;
                        System.out.print("Tipo de movimiento (E)ntrada/(S)alida:");
                        tipoMovimiento = teclado.nextLine().toUpperCase();
                        if (!tipoMovimiento.matches("E|S")) {
                            error = true;
                            System.out.println("Debe introducir E o S");
                        }
                    } while (error);

                    System.out.print("Artículo:");
                    nombreArticulo = teclado.nextLine();
                    System.out.print("Cantidad:");
                    cantidad = teclado.nextInt();
                    teclado.nextLine();
                    movimiento = new Movimiento(tipoMovimiento, nombreArticulo, cantidad);
                    movimientos.add(movimiento);
                    break;

                case "C": //Consulta movimiento
                    System.out.print("Número de movimiento:");
                    numeroMovimiento = teclado.nextInt();
                    teclado.nextLine();
                    i = 0;
                    encontrado = false;
                    do {
                        if (movimientos.get(i).getNumeroMovimiento() == numeroMovimiento) {
                            encontrado = true;
                        } else {
                            i++;
                        }
                    } while (i < movimientos.size() && !encontrado);

                    if (encontrado) {
                        System.out.println("El movimiento actual es:" + movimientos.get(i).toString());
                    } else {
                        System.out.printf("El movimiento %d no existe.\n", numeroMovimiento);
                    }

                    break;

                case "M": //Modifica movimiento
                    System.out.print("Número de movimiento:");
                    numeroMovimiento = teclado.nextInt();
                    teclado.nextLine();
                    i = 0;
                    encontrado = false;
                    do {
                        if (movimientos.get(i).getNumeroMovimiento() == numeroMovimiento) {
                            encontrado = true;
                        } else {
                            i++;
                        }
                    } while (i < movimientos.size() && !encontrado);

                    if (encontrado) {
                        System.out.println("El movimiento actual es:" + movimientos.get(i).toString());
                        System.out.print("Tipo:");
                        tipoMovimiento = teclado.nextLine();
                        System.out.print("Artículo:");
                        nombreArticulo = teclado.nextLine();
                        System.out.print("Cantidad:");
                        cantidad = teclado.nextInt();
                        teclado.nextLine();

                        movimientos.get(i).setTipoMovimiento(tipoMovimiento);
                        movimientos.get(i).setNombreArticulo(nombreArticulo);
                        movimientos.get(i).setCantidad(cantidad);

                    } else {
                        System.out.printf("El movimiento %d no existe.\n", numeroMovimiento);
                    }

                    break;

                case "B": //Borra movimiento
                    System.out.print("Número de movimiento:");
                    numeroMovimiento = teclado.nextInt();
                    teclado.nextLine();
                    i = 0;
                    encontrado = false;
                    do {
                        if (movimientos.get(i).getNumeroMovimiento() == numeroMovimiento) {
                            encontrado = true;
                        } else {
                            i++;
                        }
                    } while (i < movimientos.size() && !encontrado);

                    if (encontrado) {
                        System.out.println("Borrando el movimiento:" + movimientos.get(i).toString());
                        movimientos.remove(i);
                    } else {
                        System.out.printf("El movimiento %d no existe.\n", numeroMovimiento);
                    }

                    break;

                case "L": //Listar todos los movimientos
                    System.out.println("NUM/TIPO/CANTIDAD/ARTICULO");
                    for (i = 0; i < movimientos.size(); ++i) {
                        System.out.println(movimientos.get(i).toString());
                    }

                    //Otra forma de hacerlo es con un iterador
                    //Iterator<Contacto> iterador = contactos.iterator();
                    //while (iterador.hasNext()) {
                    //    System.out.println(iterador.next().toString());
                    //}
                    break;

                case "LON": //Ordenar por número de movimiento ascendente
                    Collections.sort(movimientos, new ComparadorMovimientosPorNumero());
                    for (Movimiento m : movimientos) {
                        System.out.println(m.toString());
                    }
                    break;

                case "LOA": //Ordenar por nombre de artículo ascendente
                    Collections.sort(movimientos, new ComparadorMovimientosPorArticulo());
                    for (Movimiento m : movimientos) {
                        System.out.println(m.toString());
                    }
                    break;

                case "S":
                    System.out.print("Nombre del articulo: ");
                    nombreArticulo = teclado.nextLine();
                    int cantidadStock = 0;
                    for (i = 0; i < movimientos.size(); ++i) {

                        if (movimientos.get(i).getTipoMovimiento().equals("E") && 
                                movimientos.get(i).getNombreArticulo().equals(nombreArticulo)) {
                            
                            cantidadStock += movimientos.get(i).getCantidad();

                        } else if (movimientos.get(i).getTipoMovimiento().equals("S") &&
                                movimientos.get(i).getNombreArticulo().equals(nombreArticulo)) {
                            
                            cantidadStock -= movimientos.get(i).getCantidad();
                        }
                    }
                    System.out.printf("Stock de %s: %d", nombreArticulo, cantidadStock);
                    break;
            }

            System.out.println("");

        } while (!opcion.equals("F"));
    }
}


/* Explicación de la interfaz Comparator:
La clase que implementa Comparator<objeto> debe implementar un método llamado compare(obj1, obj2)
Este método debe comparar dos objetos que se quieren ordenar (obj1 y obj2) y devolver un valor entero, 

int compare(Objeto obj1, Objeto obj2);

obj1 y obj2 son los objetos a ser comparados:
- en caso de ser iguales se debe devolver 0,
- en caso de que obj1 sea mayor a obj2 devolverá un valor positivo,
- en caso de que obj2 sea mayor que obj1 devolverá un valor negativo

https://tinchicus.com/2019/06/26/java-comparator/
https://guru99.es/string-compareto-method-java/
 */
//Clase para comparar por numero de movimiento
class ComparadorMovimientosPorNumero implements Comparator<Movimiento> {

    @Override
    public int compare(Movimiento c1, Movimiento c2) {
        int comparacion;
        //Como el número de movimiento no se repite no hace falta comprobar si son iguales
        if (c1.getNumeroMovimiento() < c2.getNumeroMovimiento()) {
            comparacion = -1;
        } else {
            comparacion = 1;
        }
        return comparacion;
    }
}

//Clase para comparar por nombre descendente
class ComparadorMovimientosPorArticulo implements Comparator<Movimiento> {

    @Override
    public int compare(Movimiento c1, Movimiento c2) {
        return c1.getNombreArticulo().compareTo(c2.getNombreArticulo());
    }
}
