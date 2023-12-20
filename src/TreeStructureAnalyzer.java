import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class TreeStructureAnalyzer {
    private static File f; // Input file or directory
    private static int fileCounter = 0; // Counter for total files
    private static int dirCounter = 0; // Counter for total directories
    private static Date d = new Date(); // Date object for formatting last modified timestamp

    // Count the number of subdirectories in a given directory
    static int getFolders(File file) {
        int counter = 0;
        if (file.isDirectory()) {
            for (File f: file.listFiles()) {
                if (f.isDirectory())
                    counter += 1;
            }
        }
        return counter;
    }

    // Count the number of files in a given directory
    static int getFiles(File file) {
        int counter = 0;
        if (file.isDirectory()) {
            for (File f: file.listFiles()) {
                if (f.isFile())
                    counter += 1;
            }
        }
        return counter;
    }

    // Recursively count the total number of files in a directory
    static int getTotalFiles(File file) {
        File[] files;
        if (file.isDirectory()) {
            files = file.listFiles();
            for (File file1 : files) {
                if (file1.isFile()) {
                    fileCounter += 1;
                } else {
                    fileCounter += getFiles(file1);
                    getTotalFiles(file1);
                }
            }
        }
        return fileCounter;
    }

    // Recursively count the total number of subdirectories in a directory
    static int getTotalFolders(File file) {
        File[] files;
        if (file.isDirectory()) {
            files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    dirCounter += 1;
                    getTotalFolders(file1);
                }
            }
        }
        return dirCounter;
    }

    // Recursively traverse the directory and build a formatted string with information
    static String walkDirectory(File file, String prefix) {
        StringBuilder source = new StringBuilder(">" + prefix + file.getName() + " is directory: " + file.isDirectory() + "\n");
        File[] files;
        if (file.isDirectory()) {
            files = file.listFiles();
            if (files != null) {
                for (File eachFile : files) {
                    if (eachFile.isFile()){
                        d.setTime(eachFile.lastModified());
                        source.append(prefix + " " + eachFile.getName() + " is directory: " + eachFile.isDirectory() +
                                "; last modified: " + d + "\n");
                    }
                    if (eachFile.isDirectory()) {
                        source.append("\n\n" + walkDirectory(eachFile, prefix + " "));
                    }
                }
            }
        }
        return source.toString();
    }

    public static void main(String[] args) throws IOException {
        int i;
        String resource = "";
        if (args.length != 1) {
            System.out.println("Invalid arguments: The name of a directory or a .txt file expected");
            return;
        }

        // Output file for storing the directory structure information
        File outputFile = new File("data/walkdir.txt");
        outputFile.getParentFile().mkdirs();

        f = new File(args[0]);
        if (f.isDirectory()) {
            // Build the directory structure information string
            StringBuilder source = new StringBuilder(walkDirectory(f, " "));
            // Additional information: total files and total folders
            String metaFiles = "\n total amount of files: " + Integer.toString(getTotalFiles(f));
            String metaFolders = "\n total amount of folders: " + Integer.toString(getTotalFolders(f));

            // Write the information to the output file
            try (FileWriter fileWriter = new FileWriter(outputFile)) {
                fileWriter.write(source.toString());
                fileWriter.write(metaFiles);
                fileWriter.write(metaFolders);
            } catch (IOException e) {
                System.err.println("An I/O Error occurred");
            }
        } else {
            // Read the content of a text file if the input is a file
            try (FileReader fileReader = new FileReader(f)) {
                do {
                    i = fileReader.read();
                    if (i != -1)
                        resource += (char) i;
                } while (i != -1);
            }
            System.out.println(resource);
        }
    }
}