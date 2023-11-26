package parserJson;

import main.SearchFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseJsonFile {
    private List<DepthsStationMetro> stationsDepth;

    public ParseJsonFile() {
        parse();
        listFormatted();
    }

    private void parse() {
        stationsDepth = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            for (String string : getJsonInString()) {
                JSONArray jsonData = (JSONArray) jsonParser.parse(string);
                for (Object infoDepth : jsonData) {
                    JSONObject stationDepth = (JSONObject) infoDepth;
                    String name = (String) stationDepth.get("station_name");
                    String depth = (String) stationDepth.get("depth");
                    String depth1 = depth.replaceAll(",", ".");
                    String depth2 = depth1.replaceAll("\\?", "-0");
                    stationsDepth.add(new DepthsStationMetro(name, depth2));
                }
            }
        } catch (ClassCastException e) {
            e.getStackTrace();
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void listFormatted() {
        for (int i = 0; i < stationsDepth.size(); i++) {
            String name = stationsDepth.get(i).station_name();
            Double depth = Double.parseDouble(stationsDepth.get(i).depths());
            for (int j = 0; j < stationsDepth.size(); j++) {
                String anotherName = stationsDepth.get(j).station_name();
                Double anotherDepth = Double.parseDouble(stationsDepth.get(j).depths());
                String sameName1 = "Смоленская";
                String sameName2 = "Арбатская";
                if (name.equals(anotherName) && !name.equals(sameName1) && !name.equals(sameName2)) {
                    if (depth.compareTo(anotherDepth) > 0) {
                        stationsDepth.remove(j);
                    } else {
                        stationsDepth.remove(i);
                    }
                }
            }
        }
    }

    private List<String> getJsonInString() {
        List<String> jsonString = new ArrayList<>();
        SearchFile filesSearch = new SearchFile();
        String[] paths = filesSearch.getJsonUrl().split("\n");

        for (String path : paths) {
            try {
                jsonString.add(Files.readString(Paths.get(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonString;
    }

    public List<DepthsStationMetro> getStationsDepth() {
        return stationsDepth;
    }
}
