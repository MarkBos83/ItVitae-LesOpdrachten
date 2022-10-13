import java.util.*;

public class Ganzenbord {
    public static void main(String[] args) {
        new Spel().start();
    }
}

class Spel {
    static ArrayList<Gans> ganzen = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Dobbelsteen dob1 = new Dobbelsteen();
    Dobbelsteen dob2 = new Dobbelsteen();
    int aantalspelers;

    void start() {
        spelersmaken();
        beginnen(ganzen);

        boolean spelAf = false;
        do {
            for (int i = 0; i < aantalspelers; i++) {
                if (!ganzen.get(i).beurtOverslaan && !ganzen.get(i).gevangen && !ganzen.get(i).gevallen) {
                    beurt(ganzen.get(i));
                } else {
                    System.out.println("\n" + ganzen.get(i).getKleur() + " moet deze beurt overslaan\n");
                    ganzen.get(i).beurtOverslaan = false;
                }
                if (ganzen.get(i).gewonnen) {
                    spelAf = true;
                    break;
                }
            }
        } while (!spelAf);
    }

    void beginnen(ArrayList<Gans> ganz) {
        do {
            ganz = hoogstgegooid(ganz);
        } while (ganz.size() != 1);
        switch (Spel.ganzen.indexOf(ganz.get(0))) {
            case 0:
                beurt(ganzen.get(0));
            case 1:
                beurt(ganzen.get(1));
            case 2:
                if (aantalspelers > 2) {
                    beurt(ganzen.get(2));
                }
            case 3:
                if (aantalspelers > 3) {
                    beurt(ganzen.get(3));
                }
            case 4:
                if (aantalspelers > 4) {
                    beurt(ganzen.get(4));
                }
            case 5:
                if (aantalspelers > 5) {
                    beurt(ganzen.get(5));
                }
        }
    }

    private ArrayList<Gans> hoogstgegooid(ArrayList<Gans> ganzen) {
        int hoogste = 0;
        ArrayList<Gans> hoogsteGanzen = new ArrayList<>();

        for (Gans gans : ganzen) {
            System.out.print(gans.getKleur() + ", druk op enter om 1 dobbelsteen te gooien");
            scanner.nextLine();
            int dobbel1 = dob1.Gooien();
            System.out.println("Je hebt " + dobbel1 + " gegooid\n");
            if (dobbel1 > hoogste) {
                hoogsteGanzen.clear();
                hoogste = dobbel1;
                hoogsteGanzen.add(gans);
            } else if (dobbel1 == hoogste) {
                hoogsteGanzen.add(gans);
            }
        }
        if (hoogsteGanzen.size() > 1) {
            System.out.println("meerdere spelers hebben het hoogst gegooid, die spelers gooien nu opnieuw.");
        }
        return hoogsteGanzen;
    }

    /*
    vraagt om het aantal spelers en de namen/kleuren en maakt die aan.
    */
    void spelersmaken() {
        do {
            System.out.print("Met Hoeveel Spelers wil je spelen (tussen 2 en 6): ");
            aantalspelers = scanner.nextInt();
            scanner.nextLine();
        } while (aantalspelers < 2 || aantalspelers > 6);
        System.out.println("Vul de kleuren van de spelers in (zwart, wit, groen, rood, blauw, geel): ");
        for (int x = 0; x < aantalspelers; x++) {
            String kleur = scanner.nextLine();
            ganzen.add(x, new Gans(kleur));
        }
    }

    /*
    geeft aan wiens beurt het is, en die mag dan op enter drukken om zijn dobbelstenen te werpen, dan roept deze methode
    alle benodigde andere methodes aan om de hele beurt af te maken.
     */
    void beurt(Gans gans) {
        if (gans.terug) {
            gans.terug = false;
        }
        System.out.print(gans.getKleur() + "'s beurt, druk op enter om de dobbelstenen te werpen.");
        scanner.nextLine();
        gans.oudePositie = gans.positie;
        int dobbel1 = dob1.Gooien();
        int dobbel2 = dob2.Gooien();
        int gedobbeld = dobbel1 + dobbel2;
        int plek = gans.positie + dobbel1 + dobbel2;
        if (plek > 31) {
            if (SpeciaalVakjes.lager.contains(gans) || SpeciaalVakjes.lager2.contains(gans)) {
                gevangenisOfPut2(plek, gans);
            }
        }
        System.out.println("Je hebt " + dobbel1 + " en " + dobbel2 + " gegooid (samen " + gedobbeld + ")");
        if (plek > 63) {
            teHoog(gans, dobbel1, dobbel2);
        } else {
            if (nietBezet(gans, plek)) {
                gans.positie += gedobbeld;
                Vakjes.uitvoeren(gans, dobbel1, dobbel2);
            }
        }
        if (!gans.gewonnen) {
            System.out.println("de positie van " + gans.getKleur() + " is nu " + gans.positie + "\n");
        }
        if (gans.eerstebeurt) {
            gans.eerstebeurt = false;
        }
    }

    /*
    als de speler iets heeft gegooid waardoor zijn positie boven de 63 uitkomt, word er met deze methode uitgerekend waar
    hij komt te staan.
     */
    static void teHoog(Gans gans, int dobbel1, int dobbel2) {
        System.out.println("je hebt te hoog gegooid, je gaat de teveel gegooide ogen terug vanaf de finish");
        gans.terug = true;
        int plek = 63 - ((gans.positie + dobbel1 + dobbel2) - 63);
        if (nietBezet(gans, plek)) {
            gans.positie = plek;
            Vakjes.uitvoeren(gans, dobbel1, dobbel2);
        }
    }

    /*
    met deze methode word er gecheckt of het vakje al bezet is
     */
    static boolean nietBezet(Gans gans, int plek) {
        boolean bezet = false;
        for (Gans value : ganzen) {
            if (plek == value.positie) {
                if (gans.equals(value)) {
                    continue;
                }
                bezet = true;
                System.out.println("Dit vakje is al bezet, je gaat terug naar je oude plek");
                gans.positie = gans.oudePositie;
                break;
            }
        }
        return !bezet;
    }

    /*
    als iemand in de gevangenis of put zit, word met deze methode gecheckt of die speler er al uit mag of niet
    (of hij al is gepasseerd)
     */
    static void gevangenisOfPut2(int plek, Gans gans) {
        for (Gans gans1 : ganzen) {
            if (gans1.positie == 52) {
                if (plek > 52) {
                    gans1.gevangen = false;
                    System.out.println(gans.getKleur() + ", iemand heeft je gepasseerd, de volgende ronde mag je weer gooien");
                    SpeciaalVakjes.lager.clear();
                    break;
                }
            } else if (gans1.positie == 31) {
                if (plek > 31) {
                    gans1.gevallen = false;
                    System.out.println(gans1.getKleur() + ", iemand heeft je gepasseerd, de volgende ronde mag je weer gooien");
                    SpeciaalVakjes.lager2.clear();
                    break;
                }
            }
        }
    }
}

// dobbelsteen met een methode om ze te gooien
class Dobbelsteen {
    private static final Random random = new Random();
    final int aantalZijden = 6;

    int Gooien() {
        return 1 + random.nextInt(aantalZijden);
    }
}

// de spelers zijn allemaal ganzen, met een aantal eigenschappen die van belang zijn om bepaalde methodes uit te voeren.
class Gans {
    String kleur;
    int positie;
    int oudePositie;
    boolean eerstebeurt = true;
    boolean gewonnen = false;
    boolean beurtOverslaan = false;
    boolean gevangen = false;
    boolean gevallen = false;
    boolean terug = false;

    public Gans(String kleur) {
        this.kleur = kleur;
    }

    String getKleur() {
        return kleur;
    }
}

class Vakjes {
    static SpeciaalVakjes speciaal = new SpeciaalVakjes();

    //roept alle methodes aan voor de speciale vakjes als hij erop land
    static void uitvoeren(Gans gans, int dobbel1, int dobbel2) {
        switch (gans.positie) {
            case 9 -> speciaal.eersteWorpVakje9(gans, dobbel1, dobbel2);
            case 6 -> speciaal.brug(gans);
            case 19 -> speciaal.herberg(gans);
            case 31 -> speciaal.put(gans);
            case 42 -> speciaal.doornstruik(gans);
            case 52 -> speciaal.gevangenis(gans);
            case 58 -> speciaal.dood(gans);
            case 63 -> speciaal.Finish(gans);
            case 5, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59 -> speciaal.gans(gans, dobbel1, dobbel2);
            default -> {
            }
        }
    }
}

class SpeciaalVakjes {
    ArrayList<Integer> posities = new ArrayList<>();
    static ArrayList<Gans> lager = new ArrayList<>();
    static ArrayList<Gans> lager2 = new ArrayList<>();

    //als je op een gans land word deze methode aangeroepen zodat je het aantal geworpen ogen nog een keer mag zetten.
    //en als je te hoog uitkwam (boven 63) ga je het overig aantal ogen terug.
    void gans(Gans gans, int dobbel1, int dobbel2) {
        if (!gans.terug) {
            System.out.println(gans.positie + ", een gans, ga nogmaals het aantal gegooide ogen verder");
            int plek = gans.positie + dobbel1 + dobbel2;
            if (SpeciaalVakjes.lager.contains(gans) || SpeciaalVakjes.lager2.contains(gans)) {
                Spel.gevangenisOfPut2(plek, gans);
            }
            if (plek > 63) {
                Spel.teHoog(gans, dobbel1, dobbel2);
            } else {
                gans.positie += (dobbel1 + dobbel2);
                Vakjes.uitvoeren(gans, dobbel1, dobbel2);
            }
        } else {
            System.out.println(gans.positie + ", een gans, ga nogmaals het aantal gegooide ogen terug");
            int plek = gans.positie - dobbel1 - dobbel2;
            if (SpeciaalVakjes.lager.contains(gans) || SpeciaalVakjes.lager2.contains(gans)) {
                Spel.gevangenisOfPut2(plek, gans);
            }
            if (Spel.nietBezet(gans, plek)) {
                gans.positie -= (dobbel1 + dobbel2);
                Vakjes.uitvoeren(gans, dobbel1, dobbel2);
            }
        }
    }

    //ga naar 12
    void brug(Gans gans) {
        System.out.println(gans.positie + ", een brug! ga verder naar 12");
        int plek = 12;
        if (Spel.nietBezet(gans, plek)) {
            gans.positie = 12;
        }
    }

    //beurt overslaan
    void herberg(Gans gans) {
        System.out.println(gans.positie + ", Herberg, sla 1 beurt over");
        gans.beurtOverslaan = true;
    }

    //zelfde als put, je zit vast tot iemand je passeerd of als je laatste staat, 1 beurt overslaan.
    void gevangenis(Gans gans) {
        System.out.println(gans.positie + ", je zit in de gevangenis, wacht tot iemand je passeert om je eruit te helpen, als je laastste staat, sla 1 beurt over.");
        gevangenisOfPut(gans);
        for (int i = 0; i < Spel.ganzen.size(); i++) {
            if (Spel.ganzen.get(i).positie < gans.positie) {
                lager.add(Spel.ganzen.get(i));
            }
        }
        posities.clear();
    }

    //zelfde als gevangenis, je zit vast tot iemand je passeerd of als je laatste staat, 1 beurt overslaan.
    void put(Gans gans) {
        System.out.println(gans.positie + ", je bent in de put gevallen, wacht tot iemand je passeert om je eruit te helpen, als je laastste staat, sla 1 beurt over.");
        gevangenisOfPut(gans);
        for (int i = 0; i < Spel.ganzen.size(); i++) {
            if (Spel.ganzen.get(i).positie < gans.positie) {
                lager2.add(Spel.ganzen.get(i));
            }
        }
        posities.clear();
    }

    void gevangenisOfPut(Gans gans) {
        for (Gans g : Spel.ganzen) {
            posities.add(g.positie);
        }
        if (gans.positie == Collections.min(posities)) {
            gans.beurtOverslaan = true;
        } else if (gans.positie == 31) {
            gans.gevallen = true;
        } else {
            gans.gevangen = true;
        }
    }

    //terug naar 37
    void doornstruik(Gans gans) {
        System.out.println(gans.positie + ", AAAAHH!! doornstruik! ga terug naar 37");
        int plek = 37;
        if (Spel.nietBezet(gans, plek)) {
            gans.positie = 37;
        }
    }

    //terug naar start (0)
    void dood(Gans gans) {
        System.out.println(gans.positie + ", je bent dood, ga terug naar de start");
        gans.positie = 0;
    }

    //als je in je eerste worp 9 gooid, ga je met 4 en 5 naar 53 en met 3 en 6 naar 26
    void eersteWorpVakje9(Gans gans, int dobbel1, int dobbel2) {
        if (gans.eerstebeurt) {
            if (dobbel1 == 5 && dobbel2 == 4 || dobbel1 == 4 && dobbel2 == 5) {
                int plek = 53;
                if (Spel.nietBezet(gans, plek)) {
                    System.out.println("je hebt 5 en 4 gegooid, je mag direct door naar 53");
                    gans.positie = 53;
                }
            } else if (dobbel1 == 6 && dobbel2 == 3 || dobbel1 == 3 && dobbel2 == 6) {
                int plek = 26;
                if (Spel.nietBezet(gans, plek)) {
                    System.out.println("je hebt 6 en 3 gegooid, je mag direct door naar 26");
                    gans.positie = 26;
                }
            }
        } else {
            gans(gans, dobbel1, dobbel2);
        }
    }

    //je hebt gewonnen, het spel is af
    void Finish(Gans gans) {
        System.out.println("de positie van " + gans.getKleur() + " is nu " + gans.positie + "\n");
        System.out.println("\n\n\n" + gans.getKleur() + " heeft gewonnen!");
        gans.gewonnen = true;
    }
}

