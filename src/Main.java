
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String name, breed;
        int age, count = 0;

        Dog[] dogPound = new Dog[10];
        Scanner keyboard = new Scanner(System.in);

        // Reading from the binary file
           File binaryFile = new File("Dogs.dat");
        // Check to see if the file exist and non-zero size
        System.out.println("Previously saved Dogs from binary file");

        if(binaryFile.exists() && binaryFile.length()>1L)
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // Read the entire array into dogPound
                // readObject returns Object
                // Dog[] = Object
                dogPound = (Dog[]) fileReader.readObject();
                // Loop through the array and print out all objects
                while (dogPound[count]!=null)
                    System.out.println(dogPound[count++]);

                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: "+e.getMessage());

            }
            //TODO read from binary file
        }
        else
            System.out.println("[None, please enter new dog data]");


        do {
            System.out.println("Please enter dog's name(or quit to exit): ");
            name = keyboard.nextLine();
            if (name.equalsIgnoreCase("quit"))
                break;
            System.out.println("Please enter dog's name(or quit to exit): ");
            breed = keyboard.nextLine();
            System.out.println("Please enter dog's name(or quit to exit): ");
            age = keyboard.nextInt();
            keyboard.nextLine();

            // TODO list 1.) create a new Dog object 2.) Add to array 3.) Incrementing a count

            dogPound[count++]/*postfix operator*/ = new Dog(name, breed, age);
        }
        while (true);
        // After the user enters quit, write the dogPound array to binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
