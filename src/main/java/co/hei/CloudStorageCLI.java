package co.hei;


import co.hei.exceptions.CorruptedFileException;
import co.hei.exceptions.DuplicateFileException;
import co.hei.exceptions.InsufficientSpaceDiskException;
import co.hei.exceptions.LockException;
import co.hei.exceptions.NotFoundException;
import co.hei.exceptions.RequestTimeOutException;
import co.hei.exceptions.ServerDownException;
import co.hei.exceptions.TooLargeFileSizeException;
import co.hei.exceptions.TooManyRequestsException;
import co.hei.utilities.RandomNumber;
import co.hei.utilities.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static co.hei.utilities.FileTypeChecker.isValidFileType;

class CloudStorageCLI {
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("logs.txt");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int MAX_STORAGE_SIZE_MB = 10;
    private static int currentStorageSize = 0;
    private final static Map<String, String> imagesFolder = new HashMap<>();
    private final static Map<String, String> videosFolder = new HashMap<>();
    private final static Map<String, String> pdfFolder = new HashMap<>();
    private final static Map<String, String> docsFolder = new HashMap<>();

    CloudStorageCLI() throws IOException {
    }

    public static void handler() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Ui.showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        uploadFile(scanner);
                        break;
                    case 2:
                        downloadFile(scanner);
                        break;
                    case 3:
                        listFiles();
                        break;
                    case 4:
                        deleteFile(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (RuntimeException e){
                Logger logger = Logger.getLogger("MyLog");
                logger.addHandler(fileHandler);
                logger.info(e.toString());
            }
        }
    }

    private static void uploadFile(Scanner scanner) {
        Ui.showFileTypes("upload");
        int fileTypeChoice = scanner.nextInt();
        scanner.nextLine();

        Map<String, String> selectedFolder = getFolderByType(fileTypeChoice);

        System.out.print("Enter the absolute path to the file: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try {
            if (fileName.toLowerCase().contains("corrupt")) {
                throw new CorruptedFileException("it is a corrupted file");
            }
            validateFile(filePath, selectedFolder, fileTypeChoice);
            RandomNumber.generateAndCheck();
            selectedFolder.put(fileName, filePath);
            System.out.println("File uploaded successfully!");
        } catch (TooManyRequestsException e) {
            System.out.println("Error TooManyRequests: " + e.getMessage());
        } catch (RequestTimeOutException e) {
            System.out.println("Error RequestTimeout: " + e.getMessage());
        } catch (ServerDownException e) {
            System.out.println("Error ServerDown: " + e.getMessage());
        } catch (Exception e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.addHandler(fileHandler);
            logger.info(e.toString());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void downloadFile(Scanner scanner) {
        Ui.showFileTypes("download");
        int fileTypeChoice = scanner.nextInt();
        scanner.nextLine();

        Map<String, String> selectedFolder = getFolderByType(fileTypeChoice);

        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try {
            RandomNumber.generateAndCheck();

            if (selectedFolder.containsKey(fileName)) {
                String filePath = selectedFolder.get(fileName);
                System.out.println("Downloading file from: " + filePath);
            } else {
                System.out.println("Error: File not found.");
            }
        } catch (TooManyRequestsException e) {
            System.out.println("Error TooManyRequests: " + e.getMessage());
        } catch (RequestTimeOutException e) {
            System.out.println("Error RequestTimeout: " + e.getMessage());
        } catch (ServerDownException e) {
            System.out.println("Error ServerDown: " + e.getMessage());
        } catch (Exception e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.addHandler(fileHandler);
            logger.info(e.toString());
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void listFiles() {
        System.out.println("Listing all files:");

        for (Map.Entry<String, String> entry : imagesFolder.entrySet()) {
            System.out.println("Images: " + entry.getKey() + " - " + entry.getValue());
        }
        for (Map.Entry<String, String> entry : videosFolder.entrySet()) {
            System.out.println("Videos: " + entry.getKey() + " - " + entry.getValue());
        }
        for (Map.Entry<String, String> entry : pdfFolder.entrySet()) {
            System.out.println("PDFs: " + entry.getKey() + " - " + entry.getValue());
        }
        for (Map.Entry<String, String> entry : docsFolder.entrySet()) {
            System.out.println("Docs: " + entry.getKey() + " - " + entry.getValue());
        }
    }


    private static void validateFile(String filePath, Map<String, String> selectedFolder, int fileType) throws IOException, NotFoundException, TooLargeFileSizeException, InsufficientSpaceDiskException, DuplicateFileException {
        Path file = Paths.get(filePath);

        if (!Files.exists(file)) {
            throw new NotFoundException("File not found: " + filePath);
        }

        if (!isValidFileType(file, fileType)) {
            throw new NotFoundException("Invalid file type for the specified folder");
        }


        long fileSize = Files.size(file);
        if (fileSize > 10 * 1024 * 1024) {
            throw new TooLargeFileSizeException("File size exceeds 10MB: " + fileSize);
        }

        if (currentStorageSize + fileSize > MAX_STORAGE_SIZE_MB * 1024 * 1024) {
            throw new InsufficientSpaceDiskException("Insufficient space on disk");
        }

        if (selectedFolder.containsValue(filePath)) {
            throw new DuplicateFileException("File with the same name already exists in the selected folder");
        }

        currentStorageSize += (int) fileSize;
    }

    private static void deleteFile(Scanner scanner) {
        Ui.showFileTypes("delete");
        int fileTypeChoice = scanner.nextInt();
        scanner.nextLine();

        Map<String, String> selectedFolder = getFolderByType(fileTypeChoice);

        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try {
            validateDelete(selectedFolder, fileName);
            selectedFolder.remove(fileName);
            System.out.println("File deleted successfully!");
        } catch (LockException e) {
            System.out.println("\n" + e);
        } catch (NotFoundException e) {
            System.out.println("\n" + e);
        }
    }


    private static void validateDelete(Map<String, String> selectedFolder, String fileName) throws NotFoundException, LockException {
        if (!selectedFolder.containsKey(fileName)) {
            throw new NotFoundException("Error: File not found.");
        }

        throw new LockException("Error: Deletion is locked.");
    }

    private static Map<String, String> getFolderByType(int fileTypeChoice) {
        return switch (fileTypeChoice) {
            case 1 -> imagesFolder;
            case 2 -> videosFolder;
            case 3 -> pdfFolder;
            case 4 -> docsFolder;
            default -> throw new IllegalArgumentException("Invalid file type choice");
        };
    }
}
