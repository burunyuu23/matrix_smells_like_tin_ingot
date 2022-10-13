package ru.vsu.edu.shlyikov_d_g;

import ru.vsu.edu.shlyikov_d_g.figure.Figure;
import ru.vsu.edu.shlyikov_d_g.figure.Point;

import java.util.ArrayList;
import java.util.List;

public class Transform {
    private String nameTransformation;
    private List<Double> attributtes = new ArrayList<>();
    private List<Point> centerPoses = new ArrayList<>();
    private Figure start;

    public Transform(Figure start){
        this.start = start;
    }

    public Transform(String nameTransformation, int x1, int y1, int x2, int y2){
        this.nameTransformation = nameTransformation;
        centerPoses.add(new Point(x1, y1));
        centerPoses.add(new Point(x2, y2));
    }

    @Override
    public String toString() {
        switch (nameTransformation){
            case "Translate transform pos" ->{
                return nameTransformation + ": " + centerPoses.get(0) + " -> " + centerPoses.get(1) + ".";
            }
            case "Scale" ->{
                return nameTransformation + " X*" + attributtes.get(0) + ", Y*" + attributtes.get(0);
            }
            case "Scale X", "Scale Y" ->{
                return nameTransformation + "*" + attributtes.get(0);
            }
            case "Rotate radians" ->{
                return nameTransformation + " " + attributtes.get(0) + "π";
            }
            case "Rotate degrees" ->{
                return nameTransformation + " " + attributtes.get(0) + "°";
            }
        }
        return "null";
    }

    public Figure transform(Matrix matrix){
        return new Figure(this.start.getCoords().multiply(matrix), this.start .getStartX(), this.start .getStartY());
    }

    public Figure translateX(int x){
        this.nameTransformation = "Translate X";
        attributtes.add(x * 1.0);
        return translation(x,0);
    }

    public Figure translateY(int y){
        this.nameTransformation = "Translate Y";
        attributtes.add(y * 1.0);
        return translation(0,y);
    }

    public Figure translate(int x, int y){
        this.nameTransformation = "Translate";
        attributtes.add(x * 1.0);
        attributtes.add(y * 1.0);
        return translation(x,y);
    }

    private Figure translation(int x, int y){
        Matrix shiftMatrix = new Matrix(new ArrayList<>(), new double[][]
                {{1,0,0},
                 {0,1,0},
                 {x,y,1}}, 3);
        this.start.startTransform();
        this.start = new Figure(this.start.getCoords().multiply(shiftMatrix), this.start.getStartX(), this.start.getStartY());
        this.start.endTransform();
        return this.start;
    }

    public Figure shiftDegrees(double angle1, double angle2){
        this.nameTransformation = "Shift degrees";
        attributtes.add(angle1);
        attributtes.add(angle2);
        return shift(Math.toRadians(angle1), Math.toRadians(angle2));
    }

    public Figure shiftRadians(double angle1, double angle2){
        this.nameTransformation = "Shift radians";
        attributtes.add(angle1);
        attributtes.add(angle2);
        return shift(Math.toDegrees(angle1), Math.toDegrees(angle2));
    }

    public Figure horShiftRadians(double angle){
        this.nameTransformation = "Horizontal shift radians";
        attributtes.add(angle);
        return shift(0, Math.toDegrees(angle));
    }

    public Figure vertShiftRadians(double angle){
        this.nameTransformation = "Vertical shift radians";
        attributtes.add(angle);
        return shift(Math.toDegrees(angle), 0);
    }

    public Figure horShiftDegrees(double angle){
        this.nameTransformation = "Horizontal shift degrees";
        attributtes.add(angle);
        return shift(0, Math.toRadians(angle));
    }

    public Figure vertShiftDegrees(double angle){
        this.nameTransformation = "Vertical shift degrees";
        attributtes.add(angle);
        return shift(Math.toRadians(angle), 0);
    }

    private Figure shift(double alpha, double beta){
        Matrix shiftMatrix = new Matrix(new ArrayList<>(), new double[][]
                {{       1,       Math.tan(alpha),0},
                 {Math.tan(beta),       1,        0},
                 {       0,             0,        1}}, 3);
        this.start.startTransform();
        this.start = new Figure(this.start.getCoords().multiply(shiftMatrix), this.start.getStartX(), this.start.getStartY());
        this.start.endTransform();
        return this.start;
    }

    public Figure rotateDegrees(double angle){
        this.nameTransformation = "Rotate degrees";
        attributtes.add(angle);
        return rotate(Math.toRadians(angle));
    }

    public Figure rotateRadians(double angle){
        this.nameTransformation = "Rotate radians";
        attributtes.add(angle);
        return rotate(angle);
    }

    private Figure rotate(double angle){
        Matrix rotateMatrix = new Matrix(new ArrayList<>(), new double[][]
                {{ Math.cos(angle), Math.sin(angle), 0},
                 {-Math.sin(angle), Math.cos(angle), 0},
                 {       0,               0,         1}}, 3);
        this.start.startTransform();
        this.start = new Figure(this.start.getCoords().multiply(rotateMatrix), this.start.getStartX(), this.start.getStartY());
        this.start.endTransform();
        return this.start;
    }

    public Figure scaleX(double multiplyX){
        this.nameTransformation = "Scale X";
        attributtes.add(multiplyX);
        return scale(multiplyX,1);
    }

    public Figure scaleY(double multiplyY){
        this.nameTransformation = "Scale X";
        attributtes.add(multiplyY);
        return scale(1,multiplyY);
    }

    public Figure scale(double multiply){
        this.nameTransformation = "Scale";
        attributtes.add(multiply);
        return scale(multiply, multiply);
    }

    private Figure scale( double multiplyX, double multiplyY){
        Matrix mutilplyMatrix = new Matrix(new ArrayList<>(), new double[][]
                {{ multiplyX,    0,     0},
                 {     0  ,  multiplyY, 0},
                 {     0,        0,     1}}, 3);
        this.start.startTransform();
        this.start = new Figure(this.start.getCoords().multiply(mutilplyMatrix), this.start.getStartX(), this.start.getStartY());
        this.start.endTransform();
        return this.start;
    }
}
