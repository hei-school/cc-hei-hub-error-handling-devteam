import random
from exceptions import TooManyRequests, RequestTimeout, ServerDown


class RandomNumberGenerator:
    @staticmethod
    def generate_and_check():
        random_number = random.randint(1, 100)

        if random_number == 50:
            raise TooManyRequests()

        if random_number == 99:
            raise RequestTimeout()

        if random_number == 88:
            raise ServerDown()