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
		int[] padnums = { 4, 15, 20, 30};
		int[] alignLR = { 1, 1,  1, 1};
                // header
		console.println(Formatter.padString(padnums, alignLR, "id","besteldatum","totaalprijs","aantal artikelen"), Kleur.RED_ORANGE);
		
		int i = 1;
                int aantalArtikelen = 0;
		for (Order o : orders) {
                   String saleDate = o.getSaledate().format(DateTimeFormatter.ISO_LOCAL_DATE);
                   //saleDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                   BigDecimal totaalPrijs = o.getTotalPrice();
                   for (int j = 0; j < o.getSubOrders().size(); j++) {
                       aantalArtikelen += o.getSubOrders().get(j).getQuantity();
                   }               
                   console.println(Formatter.padString(padnums, alignLR, "" + i, "" + saleDate, "" + totaalPrijs, "" + aantalArtikelen), Kleur.RED_ORANGE);
                   i++;
                }

    }
}