package br.com.camila.labyrinth.model;

import java.util.List;

public class Labyrinth {
    private List<LabyrinthPoint> points;
    private List<LabyrinthCurve> curves;

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
