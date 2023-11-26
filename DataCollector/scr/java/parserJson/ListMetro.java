package parserJson;

import java.util.List;

public record ListMetro (
        List<DepthsStationMetro> list
){
    public void addList(DepthsStationMetro d){
        list.add(d);
    }

    @Override
    public String toString() {
        return list +
                "\n";
    }
}
