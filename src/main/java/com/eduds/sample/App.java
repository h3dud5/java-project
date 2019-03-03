package com.eduds.sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {

        Card cardI1 = new Card();
        cardI1.cardNumber = "1";
        cardI1.blockCode = "1";
        cardI1.status = "1";

        Card cardI2 = new Card();
        cardI2.cardNumber = "2";
        cardI2.blockCode = "2";
        cardI2.status = "2";


        Path path = Paths.get("src/main/resources/MyInput.txt");
        Path newPath = Paths.get("src/main/resources/MyOutput2.txt");
        Path tmpFile = Files.createTempFile(null, null );
        Path tmpFile2 = Files.createTempFile(Paths.get("src/main/resources/"), null, ".txt" );
        List<String> fileContent = Files.lines(path).map(l -> {
            if(l.contains("2")) {
                l = l.replace("file", "data");
                //l += "\n";
                //Files.write( tmpFile, l.getBytes(), WRITE, APPEND);
            }
            return l;
        }).collect(Collectors.toList());


        //Serialize card object
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tmpFile2));
        oos.writeObject(cardI1);
        oos.writeObject(cardI2);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(tmpFile2));
        Card card1 = (Card) ois.readObject();
        Card card2 = (Card) ois.readObject();
        System.out.println(card1);
        System.out.println(card2);

        Files.write(tmpFile, fileContent, WRITE, APPEND);
        //Files.write(tmpFile2, card, WRITE, APPEND);


        Files.copy(tmpFile, newPath, REPLACE_EXISTING);
        Files.deleteIfExists(tmpFile);


    }
}
