package e2p.icotp.util.custom;

import java.util.Random;

public class RandomIDGenerator {
    private static final int UPPERBOUND = 9;

    public static int getRandomNumber() {
        Random rand = new Random();

        int val = rand.nextInt(UPPERBOUND);

        return val;
    }
}
