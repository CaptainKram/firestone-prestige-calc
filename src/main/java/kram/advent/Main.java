package kram.advent;

import java.time.LocalDateTime;
import java.util.Map;

public class Main {

    private static Map<String, Double> prestigiousMap = Map.of(
            "Personal Tree", 2.05,
            "Firestone Research Tree 1", 0.2,
//            "Firestone Research Tree 2", 0.4,
            "Exotic Upgrades Tree 1", 8.6e3,
            "Thunderclap", 1.856,
            "Cloudfist", 1.197,
            "Solaine", 1.2,
            "Muriel", 1.2
//            "Valerius", 0.3
    );

    private static String START_OF_THIS_RUN = "9:10";
    private static String FIRESTONES_WE_HAVE = "2.09e83";

    private static Double PRESTIGE_MULTIPLIER = 3353.4;
    private static String TOTAL_GOLD = "2.3e210";
    private static String GOLD_PER_HOUR = "4.4e210";

    public static void main(String[] args) {
        calculateCoefficient();
//        wmLevelCost(500, 13);
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
        String timeNow = LocalDateTime.now().toString();
        timeNow = timeNow.substring(timeNow.indexOf('T') + 1, timeNow.lastIndexOf(':'));
        double timeNowAsDouble = toMins(timeNow);
        double startOfThisRun = toMins(START_OF_THIS_RUN);
        if (timeNowAsDouble < startOfThisRun) {
            timeNowAsDouble += 24.0;
        }
        double hoursInThisRun = timeNowAsDouble - startOfThisRun;

        int goldPerHourE = Integer.parseInt(GOLD_PER_HOUR.substring(GOLD_PER_HOUR.indexOf('e') + 1));
        int totalGoldE = Integer.parseInt(TOTAL_GOLD.substring(TOTAL_GOLD.indexOf('e') + 1));
        int eMultiplier = totalGoldE - Integer.parseInt(FIRESTONES_WE_HAVE.substring(FIRESTONES_WE_HAVE.indexOf('e') + 1));
        int offset = goldPerHourE - totalGoldE + eMultiplier;
        Double firestonesWeHave = Double.valueOf(FIRESTONES_WE_HAVE.substring(0, FIRESTONES_WE_HAVE.indexOf('e')));
        Double totalGold = Double.valueOf(TOTAL_GOLD.substring(0, TOTAL_GOLD.indexOf('e')) + "e" + eMultiplier);
        Double goldPerHour = Double.valueOf(GOLD_PER_HOUR.substring(0, GOLD_PER_HOUR.indexOf('e')) + "e" + offset);

        PRESTIGE_MULTIPLIER /= 100;
        Double prestigiousBonus = 1.0;
        for (Map.Entry<String, Double> entry : prestigiousMap.entrySet()) {
            prestigiousBonus *= 1 + entry.getValue();
        }
        Double coefficientOfViability = Math.pow(PRESTIGE_MULTIPLIER + 1, 1 / (hoursInThisRun + ((Math.pow((firestonesWeHave * PRESTIGE_MULTIPLIER) / prestigiousBonus, (1 / (Math.log(2) / Math.log(6)))) * 18000) - totalGold) / goldPerHour));
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
        int xp = 0;
        int cost = 100;

        for (int i = 1; i < level; i++) {
            xp += cost;
            cost += 10;
        }

        int temp = xp;
        while (temp >= 100) {
            temp -= 100;
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

        double timeInDays = expeditionTokens / tokensWithAmulets();
        System.out.println("Time in days: " + timeInDays);
        System.out.println("Time in years: " + (timeInDays / 365));
    }

    private static double tokensWithAmulets() {
        double dailyTokens = 4002;
        dailyTokens = dailyTokens * 1.05;
        double tokensPerMission = dailyTokens / 20;
        return tokensPerMission + dailyTokens;
    }

}