/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation;

import java.awt.Color;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.infrastructure.Kleur;
import main.java.model.Order;

/**
 *
 * @author jurjen
 */
public interface DisplayOrders {
    
    public default void displayOrders (ColorConsole console, List<Order> orders) {
        console.println(Formatter.LINE, Color.ORANGE);
    
		console.println("Uw bestellingen", Kleur.RED_ORANGE);
		console.println(Formatter.LINE, Color.orange);
		int[] padnums = { 4, 10, 20};
		int[] alignLR = { 1, 1,  1};
                // header
		console.println(Formatter.padString(padnums, alignLR, "id","besteldatum","totaalprijs"), Kleur.RED_ORANGE);
		
		int i = 1;
                int aantalArtikelen = 0;
		for (Order o : orders) {
                   int id = i;
                   LocalDate saleDate = o.getSaledate();
                   saleDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                   BigDecimal totaalPrijs = o.getTotalPrice();
                   aantalArtikelen += o.getSubOrders().get(i).getQuantity();
                   console.println(Formatter.padString(padnums, alignLR, "" + i + id + saleDate + totaalPrijs + aantalArtikelen), Kleur.RED_ORANGE);
                }

    }
}