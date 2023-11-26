package writerJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Line;
import main.parseHtml.ParseHtml;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterFileMetroJSON {
    LineJSON lineJSON;
    ParseHtml parseHtml = new ParseHtml();
    List<Line> lines = parseHtml.getLine();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void getWriterFileMetroJson(){

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectStation = new JSONObject();

        lines.forEach(stationObject -> {
            JSONArray jsonArray = new JSONArray();
            stationObject.getStations().forEach(station1 -> jsonArray.add(station1.getName()));
            jsonObjectStation.put(stationObject.getNumber(),jsonArray);
        });
        jsonObject.put("Station", jsonObjectStation);


        JSONArray arrayLine = new JSONArray();
        lines.forEach(line-> arrayLine.add(lineJSON = new LineJSON(line.getName(), line.getNumber())));
        jsonObject.put("Line", arrayLine);

        try (FileWriter file = new FileWriter("data/metro.json")) {
            gson.toJson(jsonObject, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
