package main;


import writerJson.WriterFileMetroJSON;
import writerJson.WriterFileStationsJSON;


public class Main {

    public static void main(String[] args) {
        WriterFileMetroJSON metroJSON = new WriterFileMetroJSON();
        metroJSON.getWriterFileMetroJson();
        WriterFileStationsJSON stationsJSON = new WriterFileStationsJSON();


    }
}
