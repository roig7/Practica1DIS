//Carpeta en la que guardamos los archivos
package com.company;
//Librerias que necesitamos
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.InputStream;

import javax.xml.stream.JsonXMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


//Clase que realiza el parseo
class Main{
    //Guardamos estos strings con los que se realizan las comparaciones
    private static final String NOMBRE="Videoteca Peter";
    private static final String SALARIO="salario";

    public static void main(String[] args) throws FileNotFoundException,XMLStreamException{

        //Creacion de streamreader
        InputStream inputStream = new FileInputStream("videoteca.json");
        JsonXMLInputFactory factory=new JsonXMLInputFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(inputstream));


        //Creacion array para guardar los nombres
        ArrayList<String> nombres= new ArrayList<String>();

        //Creacion de variables
        int evento;
        String tag=null,nombre=null,salario= null;

        //Imprime 'iniciando el documento' al inicio del documento
        System.out.println("Iniciando el documento");
        //Entrara al bucle siempre que existan elementos a parsear
        while (reader.hasNext()){
            evento=reader.next();

            if (evento== reader.START_ELEMENT){
                tag= reader.getLocalName();
                //En el caso de que se trate de un nombre se guarda por si se cumple la condicion
                if (tag==NOMBRE){
                    nombre=reader.getElementText();
                    nombres.add(nombre);
                }

            }else if (evento==reader.END_DOCUMENT){
                //Imprime 'fin el documento' al final del documento
                System.out.println("Fin del documento");
            }
        }
        //Impresion de los empleados cuyo salario es superior a 30000
        System.out.println("Empleados con salario mayor a 30000: "+ nombres);
    }

}
