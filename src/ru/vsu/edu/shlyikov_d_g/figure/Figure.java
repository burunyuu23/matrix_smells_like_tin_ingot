package ru.vsu.edu.shlyikov_d_g.figure;

import ru.vsu.edu.shlyikov_d_g.Matrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Figure extends Path2D.Float{
    int startX = 0;
    int startY = 0;
    private Matrix coords;
    private Image image;

    public Figure(Matrix coords, int startX, int startY){
        this.startX = startX;
        this.startY = startY;
        this.coords = coords;
    }

    public Figure(List<Point> coords, int startX, int startY){
        this.startX = startX;
        this.startY = startY;
        this.coords = new Matrix();
        addVectors(coords);
    }

    public Figure(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
        this.coords = new Matrix();
    }

    public static Figure test3Triangle(int startX, int startY){
        List<Point> list = new ArrayList<>();
        list.add(new Point(-100,-100));
        list.add(new Point(200,200));
        return new Figure(list, startX, startY, "triangle");
    }

    public static Figure test2Triangle(int startX, int startY){
        List<Point> list = new ArrayList<>();
        list.add(new Point(-100,-100));
        list.add(new Point(200,100));
        return new Figure(list, startX, startY, "triangle");
    }

    public static Figure test90Triangle(int startX, int startY){
        List<Point> list = new ArrayList<>();
        list.add(new Point(-100,-100));
        list.add(new Point(200,200));
        return new Figure(list, startX, startY, "90triangle");
    }

    public static Figure testTriangleInside(int startX, int startY){
        List<Point> list = new ArrayList<>();
        list.add(new Point(-100,-150));
        list.add(new Point(200,300));
        return new Figure(list, startX, startY, "triforce");
    }

    public static Figure testImage(int startX, int startY) throws IOException {
        List<Point> list = new ArrayList<>();
        list.add(new Point(-400,-300));
        list.add(new Point(810,610));
        Figure ans = new Figure(list, startX, startY, "quadrilateral");
        ans.setImage(ImageIO.read(new File("C:\\Users\\zEzzLike\\IdeaProjects\\kg task 2\\src\\ru\\vsu\\edu\\shlyikov_d_g\\figure\\testImage.jpg")));
        return ans;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Figure(List<Point> list, int startX, int startY, String current){
        this.startX = startX;
        this.startY = startY;
        this.coords = new Matrix();
        Point p = list.get(1);
        if (!current.toLowerCase(Locale.ROOT).equals("free form")) {
            p = list.remove(1);
        }
        switch (current.toLowerCase(Locale.ROOT)){
            case "triangle" -> {
                list.add(list.size(), new Point(list.get(0).getX() + p.getX()/2,list.get(0).getY() + p.getY()));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX(),list.get(0).getY()));
                addVectors(list);
            }
            case "90triangle" -> {
                list.add(list.size(), new Point(list.get(0).getX(),list.get(0).getY() + p.getY()));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX() + p.getX(),list.get(0).getY()));
                addVectors(list);
            }
            case "triforce" -> {
                list.add(list.size(), new Point(list.get(0).getX() + p.getX()/4,list.get(0).getY() + p.getY()/4));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX()/2,list.get(0).getY()));
                list.add(list.size(), new Point(list.get(0).getX() + 3*p.getX()/4,list.get(0).getY() + p.getY()/4));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX()/4,list.get(0).getY() + p.getY()/4));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX()/2,list.get(0).getY() + p.getY()/2));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX(),list.get(0).getY()));
                addVectors(list);
            }
            case "quadrilateral" -> {
                list.add(list.size(), new Point(list.get(0).getX(), list.get(0).getY() + p.getY()));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX(), list.get(0).getY() + p.getY()));
                list.add(list.size(), new Point(list.get(0).getX() + p.getX(), list.get(0).getY()));
                addVectors(list);
            }
            case "free form" -> {
                addVectors(list);
            }
        }
    }

    public void changeStart(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
    }

    public void startTransform(){
        for (int i = 0; i < getCoords().getSizeX(); i++) {
            getCoords().getMatrix().get(i).set(0, getCoords().getMatrix().get(i).get(0) - this.startX);
            getCoords().getMatrix().get(i).set(1, getCoords().getMatrix().get(i).get(1) - this.startY);
        }
    }

    public void endTransform(){
        for (int i = 0; i < getCoords().getSizeX(); i++) {
            getCoords().getMatrix().get(i).set(0, getCoords().getMatrix().get(i).get(0) + this.startX);
            getCoords().getMatrix().get(i).set(1, getCoords().getMatrix().get(i).get(1) + this.startY);
        }
    }

    private void addVectors(int[] x, int[] y){
        for (int i = 0; i < x.length; i++) {
            coords.setSizeX(coords.getSizeX() + 1);
            double[] vector = new double[]{startX+x[i], startY+y[i], 1};
            coords.addVector(vector);
        }
    }

    private void addVectors(List<Point> list){
        for (int i = 0; i < list.size(); i++) {
            coords.setSizeX(coords.getSizeX() + 1);
            double[] vector = new double[]{startX+list.get(i).getX(), startY+list.get(i).getY(), 1};
            coords.addVector(vector);
        }
    }

    public void addVector(int x, int y){
        double[] vector = new double[]{startX + x, startY + y, 1};
        coords.addVector(vector);
    }

    public Matrix getCoords(){
        return coords;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public Path2D getPath(){
        reset();

        for (int i = 0; i < getCoords().getSizeX(); i++) {
            try {
                if (i == 0) {
                    moveTo(getCoords().getMatrix().get(i).get(0), getCoords().getMatrix().get(i).get(1));
                } else {
                    lineTo(getCoords().getMatrix().get(i).get(0), getCoords().getMatrix().get(i).get(1));
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        closePath();
        return this;
    }

    public static Figure getFigure(String str, int startX, int startY, int[] x, int[] y){
        if (x.length != y.length){
            System.out.println("It isn't a " + str.toLowerCase(Locale.ROOT));
            return null;
        }
        Figure figure = new Figure(startX, startY);
        figure.addVectors(x, y);
        return figure;
}

//    public static boolean isQuadrilateral(List<Point> list){
//        double d;
//        double ans = 0f;
//        for (int i = 0; i < list.size(); i++) {
//            int j = i+1;
//            if (j >= list.size()){
//                j = 0;
//            }
//            d = Math.acos(
//                    (list.get(i).getX()*list.get(j).getX() + list.get(i).getY()*list.get(j).getY())
//            / (Math.sqrt(Math.pow(list.get(i).getX(),2)+Math.pow(list.get(i).getY(),2))*
//                            Math.sqrt(Math.pow(list.get(j).getX(),2)+Math.pow(list.get(j).getY(),2))));
//            ans += Math.abs(Math.toDegrees(d));
//        }
//
//        System.out.println(ans);
//
//        return ans == 360;
//    }
}
