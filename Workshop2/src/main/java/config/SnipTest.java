package main.java.config;

import java.math.BigDecimal;

public class SnipTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SnipTest snip = new SnipTest();
		snip.tabledatatest();
	}

	public void tabledatatest() {

		// Product - Aantal - Prijs - Subtotaal",
		String prodA = "kaas";
		String prodB = "leer dammer kaas";

		BigDecimal p1 = new BigDecimal("1.20");
		BigDecimal p2 = new BigDecimal("101.20");
		p1.setScale(2);
		p2.setScale(2);
		String paddedString0 = padString("Id","  ", "Product         -","Aantal", "-  Prijs", "- Subtotaal");
		String paddedString1 = padString(" 2","  ", prodA, "40", p1.toString(), p1.toString());
		String paddedString2 = padString(" 10","  ", prodB, "1", p2.toString(), p2.toString());
//		System.out.println(" Id - Product       -        Aantal  -  Prijs - Subtotaal");
		System.out.println(paddedString0);
		System.out.println(paddedString1);
		System.out.println(paddedString2);
	}

	public String padString(String... strings) {
		int[] padnums = { 4,4,25, 10, 10, 12, 6, 6, 6 }; // kan 8 strings padden
		int[] allignLR = { 1,1, 0, 1, 1, 1, 1, 1, 1 }; // 0 = allign left, 1 =
														// right
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < strings.length; j++) {
			String tempString = strings[j];
			int padnum = padnums[j];
			int lr = allignLR[j];
			int rest = padnum - tempString.length();
			if (rest < 0)
				rest = 0;
			if (lr == 0){
				sb.append(tempString);
			}
			for (int i = 1; i < rest; i++) {
				sb.append(" ");
			}
			if(lr==1)sb.append(tempString);
		}
		return sb.toString();
	}

}
