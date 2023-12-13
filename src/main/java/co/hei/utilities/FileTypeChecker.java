package co.hei.utilities;

import org.apache.tika.Tika;

import java.io.IOException;
import java.nio.file.Path;

public class FileTypeChecker {
    public static boolean isValidFileType(Path filePath, int fileType) throws IOException {
        Tika tika = new Tika();
        String detectedType = tika.detect(filePath);

        switch (fileType) {
            case 1:
                return isImageType(detectedType);
            case 2:
                return isVideoType(detectedType);
            case 3:
                return isPDFType(detectedType);
            case 4:
                return isOfficeFileType(detectedType);
            default:
                throw new IllegalArgumentException("Invalid file type");
        }
    }

    private static boolean isVideoType(String detectedType) {
        return detectedType.startsWith("video/");
    }

    private static boolean isImageType(String detectedType) {
        return detectedType.startsWith("image/");
    }

    private static boolean isPDFType(String detectedType) {
        return detectedType.equals("application/pdf");
    }

    private static boolean isOfficeFileType(String detectedType) {
        return detectedType.startsWith("application/vnd.openxmlformats-officedocument")
                || detectedType.startsWith("application/msword");
    }
}

