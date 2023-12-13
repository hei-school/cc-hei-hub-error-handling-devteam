package co.hei.utilities;

import co.hei.exceptions.ServerErrorException;

import java.util.Random;

public class RandomNumberGenerator {

    public static void generateAndCheck() throws ServerErrorException {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        if (randomNumber == 70) {
            throw new ServerErrorException("Internal server error");
        }
    }
}
