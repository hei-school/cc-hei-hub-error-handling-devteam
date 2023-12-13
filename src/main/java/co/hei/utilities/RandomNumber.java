package co.hei.utilities;

import co.hei.exceptions.RequestTimeOutException;
import co.hei.exceptions.ServerDownException;
import co.hei.exceptions.TooManyRequestsException;
import java.util.Random;

public class RandomNumber {
    public static void generateAndCheck() throws TooManyRequestsException, RequestTimeOutException, ServerDownException {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        if (randomNumber == 50) {
            throw new TooManyRequestsException("Too many requests in a short period of time");
        }

        if (randomNumber == 99) {
            throw new RequestTimeOutException("The request has expired");
        }

        if (randomNumber == 88) {
            throw new ServerDownException("The server is unavailable.");
        }
    }
}
