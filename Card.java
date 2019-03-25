public class Card {

    String name;
    //4 for indispensable, 3 for preferable, 2 for considerable, 1 por possible brick, 0 for brick
    int desirable;
    // 1 for not once per turn, 0 for once per turn and -1 for once per duel
    int restriction;

    public Card (String name, int desirable, int restriction) {
        this.name = name;
        this.desirable = desirable;
        this.restriction = restriction;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getDesirable () {
        return desirable;
    }

    public void setDesirable (int desirable) {
        this.desirable = desirable;
    }

    public int getRestriction () {
        return restriction;
    }

    public void setRestriction (int restriction) {
        this.restriction = restriction;
    }
}
