package it.andrea.OldFashionPound.model;

import lombok.Data;

@Data
public class OldBritishCurrency {
    private String diplayValue;

    private OldBritishCurrency rest; 
    private int p; //sterline
    private int s; //scellini
    private int d; //pence

    public void convert(String input) {
        this.diplayValue = input; 

        input = input.replaceAll("[a-zA-Z]", "");
        String[] parts = input.split(" ");
        this.p = Integer.parseInt(parts[0]);  //sterline
        this.s = Integer.parseInt(parts[1]);  //scellini
        this.d = Integer.parseInt(parts[2]);  //pence
    }

    @Override
    public String toString(){
            return this.p + "p " + Math.abs(this.s) + "s " + Math.abs(this.d) + "d";

    }
}
