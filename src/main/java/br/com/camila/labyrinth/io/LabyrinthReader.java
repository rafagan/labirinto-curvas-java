package br.com.camila.labyrinth.io;

import br.com.camila.labyrinth.model.Labyrinth;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LabyrinthReader {
    public static Labyrinth read(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, Labyrinth.class);
    }
}
