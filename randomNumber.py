import random
from exceptions import ServerError


class RandomNumberGenerator:
    @staticmethod
    def generate_and_check():
        random_number = random.randint(1, 100)
        
        if random_number == 70:
            raise ServerError