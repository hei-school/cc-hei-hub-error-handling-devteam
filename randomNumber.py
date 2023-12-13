import random
from exceptions import TooManyRequests


class RandomNumberGenerator:
    @staticmethod
    def generate_and_check():
        random_number = random.randint(1, 100)
        print(f"Le nombre généré est : {random_number}")

        if random_number == 50:
            raise TooManyRequests()