from exceptions import FilenameInvalid, NotAuthorized, TooLargeFile, NotImplemented
from randomNumber import RandomNumberGenerator


class Cloud:
    def __init__(self, cloud_name):
        self.cloud_name = cloud_name
        self.filesize_limit_upload = 10  # MB UNIT
        self.folder_size_limit = 10000  # MB UNIT
        self.is_implemented = False
        self.folders = {
            'images': {
                'files': {},
                'format': ['jpg', 'png']
            },
            'videos': {
                'files': {},
                'format': ['mp4', 'mov', 'avi']
            },
            'pdf': {
                'files': {},
                'format': ['pdf']
            },
            'docs': {
                'files': {},
                'format': ['txt', 'docx', 'xlsx']
            }
        }

    def upload_file(self, folder_name, file, file_size):
        RandomNumberGenerator.generate_and_check()

        if not self.is_valid_path(folder_name):
            raise NotAuthorized()

        if not self.is_valid_format(folder_name, file):
            raise FilenameInvalid(f"Invalid filename '{file}' for folder '{folder_name}'")
        
        if file_size > self.filesize_limit_upload:
            raise TooLargeFile()

    def read_file(self, folder_name, filename):
        RandomNumberGenerator.generate_and_check()

        if not self.is_implemented:
            raise NotImplemented()

    def download_file(self, folder_name, filename):
        RandomNumberGenerator.generate_and_check()

        if not self.is_implemented:
            raise NotImplemented()

    def delete_file(self, folder_name, filename):
        RandomNumberGenerator.generate_and_check()
        
        if not self.is_implemented:
            raise NotImplemented()

    def is_valid_format(self, folder_name, file):
        folder = self.folders.get(folder_name).get('format', [])
        file_format = file.split('.')[-1].lower()
        return file_format in folder

    def is_valid_path(self, folder_name):
        folder = self.folders.get(folder_name)
        return folder
    

def main():
    hei_cloud = Cloud("HeiCloud")

    while True:
        print("\nOptions:")
        print("1. Upload")
        print("2. Read")
        print("3. Download")
        print("4. Delete")
        print("5. Exit")

        choice = input("Enter your choice (1-5): ")

        if choice == '1':
            folder_name = input("Enter the folder name (images, videos, pdf, docs): ")
            file = input("Enter file content: ")
            size = input("Enter the size: ")
            size_int = int(size)
            hei_cloud.upload_file(folder_name, file, size_int)
        elif choice == '2':
            folder_name = input("Enter the folder name (images, videos, pdf, docs): ")
            file = input("Enter filename: ")
            hei_cloud.read_file(folder_name, file)
        elif choice == '3':
            folder_name = input("Enter the folder name (images, videos, pdf, docs): ")
            file = input("Enter file to download: ")
            hei_cloud.download_file(folder_name, file)
        elif choice == '4':
            folder_name = input("Enter the folder name (images, videos, pdf, docs): ")
            file = input("Enter file to delete: ")
            hei_cloud.delete_file(folder_name, file)

        elif choice == '5':
            print("Exiting the program!")
            break
        else:
            print("Invalid choice. Please enter a number between 1 and 5.")


if __name__ == "__main__":
    main()
