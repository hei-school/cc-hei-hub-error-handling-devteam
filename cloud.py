from exceptions import BadFileType, DuplicateFile
from validators import File_validator


class Cloud:
    def __init__(self, cloud_name):
        self.cloud_name = cloud_name
        self.filesize_limit_upload = 10  # MB UNIT
        self.folder_size_limit = 10000  # MB UNIT
        self.file_validator = File_validator()
        self.folders = {
            "images": {"files": {}, "format": ["jpg", "png"]},
            "videos": {"files": {}, "format": ["mp4", "mov", "avi"]},
            "pdf": {"files": {}, "format": ["pdf"]},
            "docs": {"files": {}, "format": ["txt", "docx", "xlsx"]},
        }

    def get_folder_by_file_type(self, file_type):
        if file_type == "1":
            return "images"
        elif file_type == "2":
            return "videos"
        elif file_type == "3":
            return "pdf"
        elif file_type == "4":
            return "docs"
        else:
            raise BadFileType()

    def upload_file(self, file_type, file_path, file_name):
        self.file_validator.check_file_type(file_path, file_type)
        self.file_validator.check_file_size(file_path)
        current_folder = self.folders.get(self.get_folder_by_file_type(file_type)).get(
            "files"
        )
        if file_name in current_folder or file_path in current_folder.values():
            raise DuplicateFile()

    def read_file(self, folder_name, filename):
        pass

    def download_file(self, folder_name, filename):
        pass

    def delete_file(self, folder_name, filename):
        pass

    def is_valid_format(self, folder_name, file):
        folder = self.folders.get(folder_name).get("format", [])
        file_format = file.split(".")[-1].lower()
        return file_format in folder


def main():
    hei_cloud = Cloud("HeiCloud")
    while True:
        try:
            print("\nOptions:")
            print("1. Upload")
            print("2. Read")
            print("3. Download")
            print("4. Delete")
            print("5. Exit")

            choice = input("Enter your choice (1-5): ")

            if choice == "1":
                file_type = input(
                    "Enter the file type (1. images, 2. videos, 3. pdf, 4. docs): "
                )
                file_path = input("Enter file path: ")
                file_name = input("Enter the file name : ")
                hei_cloud.upload_file(file_type, file_path, file_name)
            elif choice == "2":
                folder_name = input(
                    "Enter the folder name (images, videos, pdf, docs): "
                )
                file = input("Enter filename: ")
                hei_cloud.read_file(folder_name, file)
            elif choice == "3":
                folder_name = input(
                    "Enter the folder name (images, videos, pdf, docs): "
                )
                file = input("Enter file to download: ")
                hei_cloud.download_file(folder_name, file)
            elif choice == "4":
                folder_name = input(
                    "Enter the folder name (images, videos, pdf, docs): "
                )
                file = input("Enter file to delete: ")
                hei_cloud.delete_file(folder_name, file)

            elif choice == "5":
                print("Exiting the program!")
                break
            else:
                print("Invalid choice. Please enter a number between 1 and 5.")
        except Exception as err:
            print("\nError : ")
            print(err)


if __name__ == "__main__":
    main()
