package main;

import java.io.File;

public class SearchFile {
    private static final File URL = new File(
            "C:/Users/ACER/Desktop/java/java_basics1/FilesAndNetwork/DATA/data");

    private String jsonUrl = "";
    private String csvUrl = "";

    public String searchFileCsv(File rootFile) {
        if (rootFile.isDirectory()) {
            File[] directoryFile = rootFile.listFiles();
            if (directoryFile != null) {
                for (File file : directoryFile) {
                    if (file.isDirectory()) {
                        searchFileCsv(file);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".csv")) {
                            csvUrl += file.getAbsolutePath() + "\n";
                        }
                    }
                }
            }
        }
        return csvUrl;
    }

    public String searchFileJson(File rootFile) {
        if (rootFile.isDirectory()) {
            File[] directoryFile = rootFile.listFiles();
            if (directoryFile != null) {
                for (File file : directoryFile) {
                    if (file.isDirectory()) {
                        searchFileJson(file);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".json")) {
                            jsonUrl += file.getAbsolutePath() + "\n";
                        }
                    }
                }
            }
        }
        return jsonUrl;
    }

    public String getJsonUrl() {
        searchFileJson(URL);
        return jsonUrl;
    }

    public String getCsvUrl() {
        searchFileCsv(URL);
        return csvUrl;
    }
}
