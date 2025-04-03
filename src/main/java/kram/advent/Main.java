package kram.advent;

import java.util.Map;

public class Main {

    private static Map<String, Double> prestigiousMap = Map.of(
            "Personal Tree", 2.05,
            "Firestone Research Tree 1", 0.2,
            "Firestone Research Tree 2", 0.4,
            "Exotic Upgrades Tree 1", 7.5e5,
            "Thunderclap", 12.786,
            "Solaine", 2.71,
            "Muriel", 3.83,
            "Valerius", 0.3
    );

    private static Double HOURS_IN_THIS_RUN = toMins("3:18");
    private static Double PRESTIGE_MULTIPLIER = 3353.4;

    // Bigger number is for the e of total gold and gold per hour, smaller number is for the e of firestones
    private static String eMultiplier = "e" + String.valueOf(399-160);
    private static Double FIRESTONES_WE_HAVE = 2.11;
    private static Double TOTAL_GOLD_THIS_RUN = Double.parseDouble("8.2" + eMultiplier);
    private static Double GOLD_PER_HOUR = Double.parseDouble("10.0" + eMultiplier);

    public static void main(String[] args) {
//        calculateCoefficient();
        wmLevelCost(500, 13);
//        personalTreeBC(50);
    }

    private static Double toMins(String s) {
        String[] hourMin = s.split(":");
        Double hour = Double.parseDouble(hourMin[0]);
        Double mins = Double.parseDouble(hourMin[1]);
        Double hoursInMins = mins / 60;
        return hour + hoursInMins;
    }

    private static void personalTreeBC(int treeLevel) {
        double pTree = 1.0;
        treeLevel /= 2;
        for (int i = 0; i < treeLevel; i++) {
            pTree *= 1.05;
        }
        System.out.println("PTree: " + (pTree - 1));
        // battle cry % = (1 + talent 1 %) × (1 + talent 2 %) × (1 + talent 3 %) × (1 + tree of life %) × (1 + campaign perk %) - 1
//        double bc = (1 + 0.15) * (pTree) * (1 + 0.30) - 1;
//        System.out.println("Overall BC: " + bc);
    }

    private static void calculateCoefficient() {
        PRESTIGE_MULTIPLIER /= 100;
        Double prestigiousBonus = 1.0;
        for (Map.Entry<String, Double> entry : prestigiousMap.entrySet()) {
            prestigiousBonus *= 1 + entry.getValue();
        }
        Double coefficientOfViability = Math.pow(PRESTIGE_MULTIPLIER + 1, 1 / (HOURS_IN_THIS_RUN + ((Math.pow((FIRESTONES_WE_HAVE * PRESTIGE_MULTIPLIER) / prestigiousBonus, (1 / (Math.log(2) / Math.log(6)))) * 18000) - TOTAL_GOLD_THIS_RUN) / GOLD_PER_HOUR));
        System.out.println(coefficientOfViability);
        FileHelper.writeStringToFile("last_coefficient.txt", String.valueOf(coefficientOfViability) + "\n");
    }

    /**
    The cost to get to level X
     **/
    private static void wmLevelCost(int level, int numberOfWM) {
        int screws = 20;
        int cogs = 12;
        int metal = 1;
        int expeditionTokens = 500;
        int xp = 100;

        for (int i = 2; i < level; i++) {
            xp += 110;
        }

        int cost = xp - 100;
        while (cost > 0) {
            cost -= 100;
            screws += 20;
            cogs += 12;
            metal += 1;
            expeditionTokens += 500;
        }

        double parts = cogs + screws + metal;

        parts *= numberOfWM;
        screws *= numberOfWM;
        cogs *= numberOfWM;
        metal *= numberOfWM;
        expeditionTokens *= numberOfWM;


        double wood = parts / 11;
        double iron = parts / 22;
        double gold = parts / 33;
        double diamond = parts / 44;
        double opal = parts / 55;
        double emerald = parts / 132;
        double platinum = parts / 264;

        System.out.println("Cost when using 1 type of chests, assuming all parts go to this/those WM/s:");
        System.out.println("Wood chests: " + wood + " ");
        System.out.println("Iron chests: " + iron + " ");
        System.out.println("Gold chests: " + gold + " ");
        System.out.println("Diamond chests: " + diamond + " ");
        System.out.println("Opal chests: " + opal + " ");
        System.out.println("Emerald chests: " + emerald + " ");
        System.out.println("Platinum chests: " + platinum);

        System.out.println();
        System.out.println("Screws: " + screws);
        System.out.println("Cogs: " + cogs);
        System.out.println("Metal: " + metal);
        System.out.println("Expedition tokens: " + expeditionTokens);
        System.out.println("XP: " + xp);
    }

}