package parseCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParserCVS {
    int i = 0;
    String regex = ",";
    private final String pathFileInCSV;
    private List<DateCSV> dataCSVArrayList;

    public ParserCVS(String pathFileInCSV) {
        this.pathFileInCSV = pathFileInCSV;
        getArrayCSV();
    }

    public ArrayList<String> listCsvData() {
        dataCSVArrayList = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        String[] textUrl = pathFileInCSV.split("\n");
        try {
            for (String url : textUrl) {
                lines.add(Files.readString(Paths.get(url)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (ArrayList<String>) lines;
    }

    public List<DateCSV> getArrayCSV() {
        listCsvData().forEach(text -> {
            String[] splitT;
            splitT = text.split("\r\n");
            for (String texts : splitT) {
                if (i != 0) {
                    String[] splitText = texts.split(regex);
                    dataCSVArrayList.add(new DateCSV(splitText[0], splitText[1]));
                }
                i++;
            }
        });
        return dataCSVArrayList;
    }

    public List<DateCSV> getDataCSV() {
        return dataCSVArrayList;
    }

    @Override
    public String toString() {
        return "ParserCVS{" +
                "dataCSVArrayList=" + dataCSVArrayList +
                "\n";
    }
}
