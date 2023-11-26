package writerJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Line;
import core.Station;
import main.SearchFile;
import main.parseHtml.ParseHtml;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import parseCSV.DateCSV;

import parseCSV.ParserCVS;
import parserJson.DepthsStationMetro;
import parserJson.ParseJsonFile;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterFileStationsJSON {
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    private final SearchFile searchFile = new SearchFile();
    private final ParseHtml parseHtml = new ParseHtml();
    private final ParseJsonFile jsonFile = new ParseJsonFile();
    private final ParserCVS parserCVS = new ParserCVS(searchFile.getCsvUrl());

    private final List<Station> stations = parseHtml.getStations();
    private final List<Line> lineList = parseHtml.getLine();
    private final List<DepthsStationMetro> depthsStationMetros = jsonFile.getStationsDepth();
    private final List<DateCSV> dataCSVS = parserCVS.getDataCSV();

    public WriterFileStationsJSON() {
        isWriterFileStationsJSON();
    }

    private void isWriterFileStationsJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        jsonObject = new JSONObject();
        jsonArray = new JSONArray();

        stations.forEach(station -> {
            JSONObject object = new JSONObject();
            String stationName = station.getName();


            lineList.forEach(line -> {
                if (station.getLine().getName().equals(line.getName())) {
                    String nameLine = line.getName();
                    object.put("line", nameLine);
                }
            });

            dataCSVS.forEach(stationDate -> {
                if (stationDate.name().equals(stationName)) {
                    object.put("date", stationDate.date());
                }
            });

            depthsStationMetros.forEach(depthsStationMetro -> {
                if (depthsStationMetro.station_name().equals(stationName) &&
                        !depthsStationMetro.depths().equals("-0")) {
                    object.put("depth", depthsStationMetro.depths());
                }
            });

            object.put("hasConnection", station.getConnection());
            jsonArray.add(object);
            object.put("name", stationName);
        });
        jsonObject.put("station", jsonArray);

        try (FileWriter file = new FileWriter("data/station.json")) {
            gson.toJson(jsonObject, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
