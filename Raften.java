import java.util.Random;

public class Raften {
    public static void main(String[] args) {
        boolean Stroomversnelling = true;
        boolean roeien = true;
        try {
            if (Stroomversnelling) {
                Random rand = new Random();
                int upperbound = 10;
                int int_random = rand.nextInt(upperbound);
                if (int_random < 5) {
                    throw new ValtVanVlotException("je bent van het vlot gevallen, gebruik je roeispaan of het naar je toe gegooide touw om terug in het vlot te komen");
                }
            }
            if (roeien) {
                Random rand = new Random();
                int upperbound = 10;
                int int_random = rand.nextInt(upperbound);
                if (int_random < 2) {
                    throw new LaatRoeispaanVallen("je hebt je roeispaan laten vallen, raak niet in paniek en blijf zitten");
                }
            }
            System.out.println("Ik heb lekker geroeid");
        } catch (ValtVanVlotException e) {
            System.out.println(e);
        } catch (LaatRoeispaanVallen r) {
            System.out.println(r);
        } finally {
            System.out.println("Je moet betalen voor de raft");
        }

    }
}

class ValtVanVlotException extends Exception {
    public ValtVanVlotException(String errorMessage) {
        super(errorMessage);
    }
}

class LaatRoeispaanVallen extends Exception {
    public LaatRoeispaanVallen(String errorMessage) {
        super(errorMessage);
    }
}

