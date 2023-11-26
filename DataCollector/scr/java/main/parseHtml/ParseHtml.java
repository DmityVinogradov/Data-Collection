package main.parseHtml;

import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseHtml {
    private final String URL = "https://skillbox-java.github.io/";
    private final List<Line> line = new ArrayList<>();
    private final List<Station> stations = new ArrayList<>();
    Line lines;

    public ParseHtml() {
        parse();
        getParseHtml();
    }

    private Document parse() {
        Document docs = null;
        try {
            docs = Jsoup.connect(URL).get();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return docs;
    }

    public List<Line> getParseHtml() {
        Elements getLine = parse().getElementsByClass("js-metro-line");
        Elements getStation = parse().getElementsByClass("js-metro-stations");

        for (int i = 0; i < getLine.size(); i++) {

            line.add(lines = new Line(getLine.get(i).attr("data-line"), getLine.get(i).text()));

            for (int j = 0; j < getStation.get(i).getElementsByClass("name").size(); j++) {
                String addStation = getStation.get(i).getElementsByClass("name").get(j).text();
                boolean isConnection = !getStation.get(i).getElementsByClass("single-station")
                        .get(j).getElementsByClass("t-icon-metroln").isEmpty();

                int stationNumbers = 1;
                stations.add(new Station(addStation, lines, stationNumbers + j, isConnection));
                lines.addStation(new Station(addStation, lines, stationNumbers + j, isConnection));
            }
        }
        return line;
    }

    public List<Line> getLine() {
        return line;
    }

    public List<Station> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "" +
                getLine();
    }
}
