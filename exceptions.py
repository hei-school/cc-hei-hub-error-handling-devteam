class FilenameInvalid(Exception):
    def __init__(self, code=400, message="The file format is invalid"):
        self.status_code = code
        self.message = message
        super().__init__(self.status_code, self.message)


class NotAuthorized(Exception):
    def __init__(self, code=401, message="The path does not exist"):
        self.status_code = code
        self.message = message
        super().__init__(self.status_code, self.message)

class TooLargeFile(Exception):
    def __init__(self, code=423, message="File size exceeds 10 MB"):
        self.status_code = code
        self.message = message
        super().__init__(self.status_code, self.message)