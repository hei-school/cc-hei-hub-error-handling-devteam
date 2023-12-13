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
        

class TooManyRequests(Exception):
    def __init__(self, code=429, message="Too many requests in a short period of time"):
        self.status_code = code
        self.message = message
        super().__init__(self.status_code, self.message)


class RequestTimeout(Exception):
    def __init__(self, code=408, message="The request has expired"):

      
class DuplicatedFile(Exception):
    def __init__(self, message="Duplicated file"):
        self.message = message
        super().__init__(self.message)

class CorruptedFile(Exception):
    def __init__(self, code=500, message="The file is corrupted"):
        self.status_code = code
        self.message = message
        super().__init__(self.status_code, self.message)


class ServerDown(Exception):
    def __init__(self, code=504, message="The server is unavailable."):
        self.status_code = code
        self.message = message
        super().__init__(self.message)

        
class LockException(Exception):
    def __init__(self, code=403, message="File deletion is locked"):
        self.status_code = code
        self.message = message
        super().__init__(self.status_code, self.message)

