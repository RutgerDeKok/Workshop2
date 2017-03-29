package main.java.infrastructure;

public class Formatter {
	
	public final static String LINE = "============================================================";
	
	public static String padString(int[] padnums,int[] allignLR,String... strings) { 
		
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < strings.length; j++) {
			String tempString = strings[j];
			int padnum = padnums[j];
			int lr = allignLR[j];
			int rest = padnum - tempString.length();
			if (rest < 0)
				rest = 0;
			if (lr == 0){ // allign left
				sb.append(tempString);
			}
			for (int i = 1; i < rest; i++) {
				sb.append(" ");
			}
			if(lr==1)sb.append(tempString); // allign right
		}
		return sb.toString();
	}
	

}
