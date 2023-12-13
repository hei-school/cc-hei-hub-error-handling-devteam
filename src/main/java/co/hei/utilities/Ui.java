package co.hei.utilities;

public class Ui {
    public static void showMenu() {
        final String menu = """
1. Upload File
2. Download File
3. List Files
4. Exit
                """;
        System.out.println(menu);
        System.out.print("Select an option: ");
    }

    public static void showFileTypes (String action){
        final String fileTypes ="""
Select file type to %s:
    1. Images
    2. Videos
    3. PDFs
    4. Docs
                """;
        System.out.printf((fileTypes) + "%n", action);
        System.out.print("Enter the type (1-4): ");
    }
}
