import magic
import os
from exceptions import NotAuthorized, TooLargeFile


class File_validator:
    def isImage(self, file_mime_type):
        return file_mime_type.startswith("image")

    def isVideo(self, file_mime_type):
        return file_mime_type.startswith("video")

    def isPdf(self, file_mime_type):
        return file_mime_type == "application/pdf"

    def isOffice(self, file_mime_type):
        return file_mime_type.startswith(
            "application/msword"
        ) or file_mime_type.startswith(
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        )

    def check_file_type(self, file_path, file_type):
        mime = magic.Magic()
        file_mime_type = mime.from_file(file_path)
        if file_type == "1" and not self.isImage(file_mime_type):
            raise NotAuthorized("File type do not correspond to the specified folder")
        elif file_type == "2" and not self.isVideo(file_mime_type):
            raise NotAuthorized("File type do not correspond to the specified folder")
        elif file_type == "3" and not self.isPdf(file_mime_type):
            raise NotAuthorized("File type do not correspond to the specified folder")
        elif file_type == "4" and not self.isOffice(file_mime_type):
            raise NotAuthorized("File type do not correspond to the specified folder")
        else:
            raise ValueError("Not existing file type.")

    def check_file_size(self, file_path):
        file_size = os.path.getsize(file_path)
        file_size_mb = file_size / (1024 * 1024)
        if file_size_mb > 10:
            raise TooLargeFile()
