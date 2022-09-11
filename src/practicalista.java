

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class practicalista {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        if (args.length == 0) {
            System.out.println("Falta el nombre de archivo");
            System.exit(0);
        }

        int pr = 0;
        String palabra;
        System.out.print("Ingresa la palabra que deseas encontrar");
        palabra = sc.next();

        FileReader FR = null;
        try {
            FR = new FileReader(args[0]);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

        //leer linea x linea el archivo
        BufferedReader inputFile = new BufferedReader(FR);

        String lectorlinea = null;

        int lineCount = 0;
        int wordCount = 0;
        int numberCount = 0;

        String delimiters = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*";


        // Lista con todas las palabras diferentes
        ArrayList<String> list = new ArrayList<String>();

        try {
            while ((lectorlinea = inputFile.readLine()) != null) {
                lineCount++;

                if (lectorlinea.trim().length() == 0) {
                    continue; // la linea esta vacia, continuar
                }

                // separar las palabras en cada linea
                String words[] = lectorlinea.split( delimiters );

                wordCount += words.length;

                for (String theWord : words) {

                    theWord = theWord.toLowerCase().trim();

                    boolean isNumeric = true;

                    // verificar si el token es un numero
                    try {
                        Double num = Double.parseDouble(theWord);
                    } catch (NumberFormatException e) {
                        isNumeric = false;
                    }

                    // SKIPEAR los numeros
                    if (isNumeric) {
                        numberCount++;
                        continue;
                    }

                    // si la palabra no esta en la lista, agregar a la lista
                    if ( !list.contains(theWord) ) {
                        list.add(theWord);
                    } else {
                        for(int i = 0; i < list.size();i++){
                            String recuperador = list.get(i);
                            if(recuperador.equals(palabra)){
                                pr++;
                            }
                        }
                    }
                }
            }
            System.out.println("El numero de palabras dentro del archivo son " + wordCount);
            System.out.println("EL numero de palabras distintas dentro del archivo son " + list.size());
            System.out.println("El numero de veces que la palabra mencioanda por el usuario se repite es " + pr + " veces");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
