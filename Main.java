import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main{

    public static void main (String [] args) {
        ArrayList <Card> deck = new ArrayList <Card> ();
        readDeck(deck);
        System.out.println(deck.size());
        testHands(deck);
    }

    static ArrayList <Card> readDeck ( ArrayList <Card> deck ) {

        File file = new File("deck.txt");

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String [] cardRegister = sc.nextLine().split(",");
                Card card = new Card(cardRegister[0]);
                for ( int i = 0; i < Integer.parseInt(cardRegister[1]); i++) {
                    deck.add(card);
                }
            }
            sc.close();
        }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }

        return deck;
    }

    static ArrayList <Card> testHands ( ArrayList <Card> deck ) {
        ArrayList <Card> history = new ArrayList <Card> ();
        ArrayList <Integer> frequency = new ArrayList <Integer> ();
        for ( int i=0; i < 100; i++) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Hand " + (i + 1));
            Collections.shuffle(deck);
            for ( int j = 0; j < 5 ; j++) {
                if ( history.contains(deck.get(j)) ) {
                    frequency.set(history.indexOf(deck.get(j)), frequency.get(history.indexOf(deck.get(j))) + 1 );
                } else {
                    history.add(deck.get(j));
                    frequency.add(1);
                }
                System.out.println(deck.get(j).name);
            }
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Card Statistics \n");
        for ( int i=0; i < history.size(); i++) {
            System.out.println("Card: " + history.get(i).name + " with " + frequency.get(i) + " times");
        }
        return deck;
    }

}
