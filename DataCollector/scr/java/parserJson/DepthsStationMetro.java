package parserJson;


public record DepthsStationMetro(String station_name, String depths) {

    @Override
    public String toString() {
        return station_name + " " + depths + "\n";
    }
}
