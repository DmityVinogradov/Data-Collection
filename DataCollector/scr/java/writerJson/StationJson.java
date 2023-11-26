package writerJson;

public record StationJson (
        String name,
        String Line,
        String date,
        long depth,
        boolean hasConnection
) {

}
