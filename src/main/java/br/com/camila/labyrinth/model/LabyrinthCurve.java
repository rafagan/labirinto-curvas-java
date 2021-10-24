package br.com.camila.labyrinth.model;

public class LabyrinthCurve {
    private int p1;
    private int p2;
    private int p3;
    private Integer p4;

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public int getP3() {
        return p3;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public Integer getP4() {
        return p4;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }

    public boolean hasFourPoints() {
        return this.p4 != null;
    }
}
