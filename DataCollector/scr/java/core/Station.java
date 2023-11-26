package core;

import java.util.List;

public class Station {
    private Line line;
    private final String name;
    private int numbers;
    private boolean hasConnection;


    private List<Station> listStation;

    public Station(String name, int numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    public Station(String name, Line line, int numbers, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.numbers = numbers;
        this.hasConnection = hasConnection;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public List<Station> getListStation() {
        return listStation;
    }

    public void setListStation(List<Station> listStation) {
        this.listStation = listStation;
    }

    public String getConnection() {
        String result;
        if (hasConnection) {
            result = "(Есть пересадка!)";
        } else {
            result = "(Пересадки нет!)";
        }
        return result;
    }

    public void addStation(Station station) {
        listStation.add(station);
    }

    @Override
    public String toString() {
        return numbers +
                " " +
                name + " " +
                hasConnection + "\n";
    }
}