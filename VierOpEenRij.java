import java.util.ArrayList;
import java.util.Scanner;

public class VierOpEenRij {
    public static void main(String[] args) {
        CancelIndicator cancel = new CancelIndicator();
        ArrayList<String> spelbord = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int turns = 42;
        Spelbord(spelbord);
        int min = 0;
        int max = 1;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        if(random_int==1){
            spelbord = Geel(input, SpelbordPrint(spelbord), cancel);
            turns--;
        }
        do {
            spelbord = Rood(input, SpelbordPrint(spelbord), cancel);
            turns--;
            if (turns == 0 || cancel.shouldCancel) {
                break;
            }
            spelbord = Geel(input, SpelbordPrint(spelbord), cancel);
            turns--;
        } while (turns != 0 && !cancel.shouldCancel);
        if(turns==0){
            SpelbordPrint(spelbord);
            System.out.println("GELIJKSPEL!");
        }
    }

    static ArrayList<String> Geel(Scanner input, ArrayList<String> spelbord, CancelIndicator cancel) {
        String beurt = "";
        char beurt2 = 'p';
        do {
            System.out.print("Kan speler GEEL aangeven in welke kolom hij zijn steen wilt gooien: ");
            beurt = input.nextLine().toLowerCase();
            if (beurt.length() != 1) {
                System.out.println("Verkeerde input, probeer het opnieuw.");
                continue;
            }
            char[] beurt3 = beurt.toCharArray();
            beurt2 = beurt3[0];
            if (beurt2 != 'a' && beurt2 != 'b' && beurt2 != 'c' && beurt2 != 'd' && beurt2 != 'e' && beurt2 != 'f' && beurt2 != 'g') {
                System.out.println("Verkeerde input, probeer het opnieuw.");
            }
            int i = switch (beurt2) {
                case 'a' -> 1;
                case 'b' -> 3;
                case 'c' -> 5;
                case 'd' -> 7;
                case 'e' -> 9;
                case 'f' -> 11;
                case 'g' -> 13;
                default -> 0;
            };
            if(spelbord.get(i).equals("G")||spelbord.get(i).equals("R")){
                System.out.println("Kolom is al vol, probeer opnieuw.");
                beurt2 = 'p';
            }
        } while (beurt2 != 'a' && beurt2 != 'b' && beurt2 != 'c' && beurt2 != 'd' && beurt2 != 'e' && beurt2 != 'f' && beurt2 != 'g');
        return Beurt("G", spelbord, beurt, cancel);
    }

    static ArrayList<String> Rood(Scanner input, ArrayList<String> spelbord, CancelIndicator cancel) {
        String beurt = "";
        char beurt2 = 'p';
        do {
            System.out.print("Kan speler ROOD aangeven in welke kolom hij zijn steen wilt gooien: ");
            beurt = input.nextLine().toLowerCase();
            if (beurt.length() != 1) {
                System.out.println("Verkeerde input, probeer het opnieuw.");
                continue;
            }
            char[] beurt3 = beurt.toCharArray();
            beurt2 = beurt3[0];
            if (beurt2 != 'a' && beurt2 != 'b' && beurt2 != 'c' && beurt2 != 'd' && beurt2 != 'e' && beurt2 != 'f' && beurt2 != 'g') {
                System.out.println("Verkeerde input, probeer het opnieuw.");
            }
            int i = switch (beurt2) {
                case 'a' -> 1;
                case 'b' -> 3;
                case 'c' -> 5;
                case 'd' -> 7;
                case 'e' -> 9;
                case 'f' -> 11;
                case 'g' -> 13;
                default -> 0;
            };
            if(spelbord.get(i).equals("G")||spelbord.get(i).equals("R")){
                System.out.println("Kolom is al vol, probeer opnieuw.");
                beurt2 = 'p';
            }
        } while (beurt2 != 'a' && beurt2 != 'b' && beurt2 != 'c' && beurt2 != 'd' && beurt2 != 'e' && beurt2 != 'f' && beurt2 != 'g');
        return Beurt("R", spelbord, beurt, cancel);
    }

    static ArrayList<String> Beurt(String kleur, ArrayList<String> spelbord, String beurt, CancelIndicator cancel) {
        int i = switch (beurt) {
            case "a" -> 86;
            case "b" -> 88;
            case "c" -> 90;
            case "d" -> 92;
            case "e" -> 94;
            case "f" -> 96;
            case "g" -> 98;
            default -> 0;
        };
        for (int j = i; j >= 1; j -= 17) {
            if (!spelbord.get(j).equals("G") && !spelbord.get(j).equals("R")) {
                spelbord.set(j, kleur);
                cancel.shouldCancel = (VierOpRij(j, spelbord));
                break;
            }
        }
        return spelbord;
    }

    static void Spelbord(ArrayList<String> spelbord) {
        for (int i = 6; i > 0; i--) {
            for (int ii = 0; ii < 8; ii++) {
                spelbord.add("|");
                spelbord.add(" ");
            }
            spelbord.add(i + "\n");
        }
    }

    static ArrayList<String> SpelbordPrint(ArrayList<String> spelbord) {
        System.out.println(" a b c d e f g ");
        for (String spel : spelbord) {
            System.out.print(spel);
        }
        System.out.println(" - - - - - - - ");
        return spelbord;
    }

    static boolean VierOpRij(int laatstGelegdeSteen, ArrayList<String> spelbord) {
        if (Horizontaal(laatstGelegdeSteen, spelbord) || Verticaal(laatstGelegdeSteen, spelbord) || Diagonaal(laatstGelegdeSteen, spelbord)) {
            Winnaar(laatstGelegdeSteen, spelbord);
            return true;
        }
        return false;
    }

    static boolean Horizontaal(int laatstGelegdeSteen, ArrayList<String> spelbord) {
        int OpRij = 0;
        int num = 0;
        int num2 = 0;
        if (laatstGelegdeSteen < 14) {
            //rij = 6;
            num2 = 14;
        } else if ((laatstGelegdeSteen < 31)) {
            //rij = 5;
            num = 14;
            num2 = 31;
        } else if ((laatstGelegdeSteen < 48)) {
            //rij = 4;
            num = 31;
            num2 = 48;
        } else if ((laatstGelegdeSteen < 65)) {
            //rij = 3;
            num = 48;
            num2 = 65;
        } else if ((laatstGelegdeSteen < 82)) {
            //rij = 2;
            num = 65;
            num2 = 82;
        } else if ((laatstGelegdeSteen < 99)) {
            //rij = 1;
            num = 82;
            num2 = 99;
        }
        for (int i = laatstGelegdeSteen - 2; i > num; i -= 2) {
            if (spelbord.get(laatstGelegdeSteen).equals(spelbord.get(i))) {
                OpRij += 1;
            } else {
                break;
            }
        }
        for (int i = laatstGelegdeSteen + 2; i < num2; i += 2) {
            if (spelbord.get(laatstGelegdeSteen).equals(spelbord.get(i))) {
                OpRij += 1;
            } else {
                break;
            }
        }
        return OpRij > 2;
    }

    static boolean Verticaal(int laatstGelegdeSteen, ArrayList<String> spelbord) {
        if (laatstGelegdeSteen < 48) {
            if (spelbord.get(laatstGelegdeSteen + 17).equals(spelbord.get(laatstGelegdeSteen))) {
                if (spelbord.get(laatstGelegdeSteen + 34).equals(spelbord.get(laatstGelegdeSteen + 17))) {
                    return spelbord.get(laatstGelegdeSteen + 51).equals(spelbord.get(laatstGelegdeSteen + 34));
                }
            }
        }
        return false;
    }

    static boolean Diagonaal(int laatstGelegdeSteen, ArrayList<String> spelbord) {
        int OpRij = 0;

        for (int i = laatstGelegdeSteen - 19; i > 0; i -= 19) {
            if (laatstGelegdeSteen % 17 == 1) {
                break;
            }
            if (spelbord.get(laatstGelegdeSteen).equals(spelbord.get(i))) {
                OpRij += 1;
                if (i % 17 == 1) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = laatstGelegdeSteen + 19; i < 99; i += 19) {
            if (laatstGelegdeSteen % 17 == 13) {
                break;
            }
            if (spelbord.get(laatstGelegdeSteen).equals(spelbord.get(i))) {
                OpRij += 1;
                if (i % 17 == 13) {
                    break;
                }
            } else {
                break;
            }
        }
        if (OpRij > 2) {
            return true;
        } else {
            OpRij = 0;
        }
        for (int i = laatstGelegdeSteen - 15; i > 0; i -= 15) {
            if (laatstGelegdeSteen % 17 == 13) {
                break;
            }
            if (spelbord.get(laatstGelegdeSteen).equals(spelbord.get(i))) {
                OpRij += 1;
                if (i % 17 == 13) {
                    break;
                }
            } else {
                break;
            }
        }
        for (int i = laatstGelegdeSteen + 15; i < 99; i += 15) {
            if (laatstGelegdeSteen % 17 == 1) {
                break;
            }
            if (spelbord.get(laatstGelegdeSteen).equals(spelbord.get(i))) {
                OpRij += 1;
                if (i % 17 == 1) {
                    break;
                }
            } else {
                break;
            }
        }
        if (OpRij > 2) {
            return true;
        } else {
            OpRij = 0;
            return false;
        }
    }

    static void Winnaar(int laatstGelegdeSteen, ArrayList<String> spelbord) {
        if (spelbord.get(laatstGelegdeSteen).equals("R")) {
            SpelbordPrint(spelbord);
            System.out.println("ROOD WINT!");
        } else {
            SpelbordPrint(spelbord);
            System.out.println("GEEL WINT!");
        }
    }
}

class CancelIndicator {
    public boolean shouldCancel;
}