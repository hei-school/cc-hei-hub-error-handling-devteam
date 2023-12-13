import random

from exceptions import ServerError, NotImplemented
from exceptions import TooManyRequests, RequestTimeout, ServerDown
 feature/python


class RandomNumberGenerator:
    @staticmethod
    def generate_and_check():
        random_number = random.randint(1, 100)


        if random_number == 70:
            raise ServerError
        if random_number == 40:
            raise NotImplemented

        if random_number == 50:
            raise TooManyRequests()

        if random_number == 99:
            raise RequestTimeout()

        if random_number == 88:
            raise ServerDown()
 feature/python
