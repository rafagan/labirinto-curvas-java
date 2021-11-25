package br.com.app.labyrinth.model;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private List<LabyrinthPoint> points = new ArrayList<>();
    private List<LabyrinthCurve> curves = new ArrayList<>();

    public List<LabyrinthPoint> getPoints() {
        return points;
    }

    public void setPoints(List<LabyrinthPoint> points) {
        this.points = points;
    }

    public List<LabyrinthCurve> getCurves() {
        return curves;
    }

    public void setCurves(List<LabyrinthCurve> curves) {
        this.curves = curves;
    }
}
