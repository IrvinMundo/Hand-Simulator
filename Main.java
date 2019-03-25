import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main{

    public static void main (String [] args) {
        ArrayList <Card> deck = new ArrayList <Card> ();
        readDeck(deck);
        System.out.println("This deck has " + deck.size() + " cards");
        testHands(deck);
    }

    static ArrayList <Card> readDeck ( ArrayList <Card> deck ) {

        File file = new File("deck.txt");

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String [] cardRegister = sc.nextLine().split(",");
                Card card = new Card(cardRegister[0], Integer.parseInt(cardRegister[2]), Integer.parseInt(cardRegister[3]));
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
        int [] cardweights = { -5, 0, 3, 5, 7 };
        int [] historialcounter = {0, 0, 0, 0, 0};
        double points = 0;
        for ( int i=0; i < 100; i++) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Hand " + (i + 1));
            Collections.shuffle(deck);
            int handsPoint = 0;
            ArrayList <Card> hand = new ArrayList <Card> ();
            for ( int j = 0; j < 5 ; j++) {
                if ( hand.contains(deck.get(j)) ) {
                    if ( deck.get(j).getRestriction() == 1) {
                        handsPoint += cardweights[deck.get(j).getDesirable()];
                    }
                } else {
                    handsPoint += cardweights[deck.get(j).getDesirable()];
                    hand.add(deck.get(j));
                }
                if ( history.contains(deck.get(j)) ) {
                    frequency.set(history.indexOf(deck.get(j)), frequency.get(history.indexOf(deck.get(j))) + 1 );
                } else {
                    history.add(deck.get(j));
                    frequency.add(1);
                }
                System.out.println(deck.get(j).name);
            }
                System.out.println("This hand has: " + handsPoint + " points");
                if (handsPoint > 27) {
                    System.out.println("Excellent hand");
                    historialcounter[0]++;
                } else if (handsPoint > 20) {
                    System.out.println("Very good hand");
                    historialcounter[1]++;
                } else if (handsPoint > 13) {
                    System.out.println("Regular hand");
                    historialcounter[2]++;
                } else if (handsPoint > 6) {
                    System.out.println("Brick hand");
                    historialcounter[3]++;
                } else {
                    System.out.println("Turbo brick hand");
                    historialcounter[4]++;
                }
                points += handsPoint;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Card Statistics \n");
        for ( int i=0; i < history.size(); i++) {
            System.out.println("Card: " + history.get(i).name + " with " + frequency.get(i) + " times");
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("The average handpoints of this deck after 100 hands is: " + (points / 100.00) + " points");
        System.out.println("The number of excellent hands are: " + historialcounter[0]);
        System.out.println("The number of very good hands are: " + historialcounter[1]);
        System.out.println("The number of regular hands are: " + historialcounter[2]);
        System.out.println("The number of brick hands are: " + historialcounter[3]);
        System.out.println("The number of turbo brick hands are: " + historialcounter[4]);

        return deck;
    }

}
