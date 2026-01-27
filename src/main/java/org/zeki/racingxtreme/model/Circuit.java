package org.zeki.racingxtreme.model;

public class Circuit {
    private enum circuit {FRANCIA, ALEMANIA, INGLATERRA, ESTADOS_UNIDOS, AUSTRALIA, HUNGRIA, ITALIA, MEXICO, BRASIL, CANADA, AUSTRIA, SINGAPUR, CHINA, BELGICA, QATAR}

    private double length;
    private String name;

    public Circuit() {
        this.length = (Math.random() * (500 - 250) + 250);
    }

    public double getLength() {
        return length;
    }


    public String getName() {
        return name;
    }

    public void setName() {
        int randomNumber = (int) (Math.random() * circuit.values().length);
        name = String.valueOf(circuit.values()[randomNumber]);

    }
}
