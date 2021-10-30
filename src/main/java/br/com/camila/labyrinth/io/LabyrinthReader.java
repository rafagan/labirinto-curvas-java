package br.com.camila.labyrinth.io;

import br.com.camila.labyrinth.model.Labyrinth;
import br.com.camila.labyrinth.model.LabyrinthCurve;
import br.com.camila.labyrinth.model.LabyrinthPoint;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.joml.Vector2f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LabyrinthReader {
    public static Labyrinth readFromJson(String path) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, Labyrinth.class);
    }

    public static Labyrinth readFromTxtFiles(String pointsPath, String curvesPath) throws IOException {
        Labyrinth labyrinth = new Labyrinth();

        try (BufferedReader pointsFile = new BufferedReader(new FileReader(pointsPath))) {
            long count = Long.parseLong((pointsFile.readLine()));
            for (long i = 0; i < count; i++) {
                String line = pointsFile.readLine();
                String[] tokens = line.split(" ");

                LabyrinthPoint point = new LabyrinthPoint();
                point.setX(Float.parseFloat(tokens[0]));
                point.setY(Float.parseFloat(tokens[1]));
                labyrinth.getPoints().add(point);
            }
        }

        try (BufferedReader curvesFile = new BufferedReader(new FileReader(curvesPath))) {
            long count = Long.parseLong((curvesFile.readLine()));
            for (long i = 0; i < count; i++) {
                String line = curvesFile.readLine();
                String[] tokens = line.split(" ");

                LabyrinthCurve curve = new LabyrinthCurve();
                curve.setP1(Integer.parseInt(tokens[0]));
                curve.setP2(Integer.parseInt(tokens[1]));
                curve.setP3(Integer.parseInt(tokens[2]));

                if(tokens.length == 4) {
                    curve.setP4(Integer.parseInt(tokens[3]));
                }

                labyrinth.getCurves().add(curve);
            }
        }

        Vector2f min = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
        Vector2f max = new Vector2f(Float.MIN_VALUE, Float.MIN_VALUE);
        for(LabyrinthPoint point: labyrinth.getPoints()) {
            if(point.getX() < min.x) min.x = point.getX();
            if(point.getY() < min.y) min.y = point.getY();
            if(point.getX() > max.x) max.x = point.getX();
            if(point.getY() > max.y) max.y = point.getY();
        }

        min.add(new Vector2f(-2, -2));
        max.add(new Vector2f(2, 2));
        Vector2f size = new Vector2f(
                Math.abs(min.x) + Math.abs(max.x),
                Math.abs(min.y) + Math.abs(max.y)
        );

        for(LabyrinthPoint point: labyrinth.getPoints()) {
            point.setX((point.getX() + Math.abs(min.x)) / size.x);
            point.setY((point.getY() + Math.abs(min.y)) / size.y);
        }

        return labyrinth;
    }
}
