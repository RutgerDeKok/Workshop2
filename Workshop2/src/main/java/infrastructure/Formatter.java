package main.java.infrastructure;

public class Formatter {

	public final static String LINE = "===========================================================================================";

	/*
	 * @Author = Rutger
	 * 
	 * This method can be uses to space out String components in one line, this
	 * can be useful for presenting tables.
	 *
	 * if you have x amount of Strings to space out, you have to specify how
	 * long each part will be with the first argument int[] padnums. So if you
	 * have 5 strings for each row, for example String id ="1", String blank =
	 * " ", String name ="Ford Bronco, String year = "1982", String price =
	 * "$4000.00" you specify the length for each like this: int[] padnums =
	 * {6,3,20,8,10};
	 * 
	 * Then specify how to align each part, left (0) or right (1) in the
	 * argument int[] alignLR = {1,1,0,1,1};
	 * 
	 * The last argument in the method String strings... is a vararg so you can
	 * enter all the Strings separated by a comma like this:
	 * Formatter.padString(padnums, allignLR, id, blank, name, year, price); now
	 * if do the same for another row of Strings with the same padnums and
	 * alignLR they will align nicely
	 * 
	 */
	public static String padString(int[] padnums, int[] alignLR, String... strings) {

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < strings.length; j++) {
			int padnum = padnums[j];
			int lr = alignLR[j];

			String tempString = strings[j];
			if (tempString == null) {
				tempString = "";
			}
			;
			tempString = tempString.length() > padnum - 1 ? tempString.substring(0, padnum - 2) : tempString;

			int rest = padnum - tempString.length();
			if (rest < 0)
				rest = 0;
			if (lr == 0) { // align left
				sb.append(tempString);
			}
			for (int i = 1; i < rest; i++) {
				sb.append(" ");
			}
			if (lr == 1)
				sb.append(tempString); // align right
		}
		return sb.toString();
	}

	
	public static String padString(int padnum, int alignLR, String inputString) {

		StringBuilder sb = new StringBuilder();
		//System.out.println("inputString = "+inputString);

		if (inputString == null) {
			inputString = "";
		}
		inputString = inputString.length() > padnum - 1 ? inputString.substring(0, padnum - 2) : inputString;

		int rest = padnum - inputString.length();
		if (rest < 0)
			rest = 0;
		if (alignLR == 0) { // align left
			sb.append(inputString);
		}
		for (int i = 1; i < rest; i++) {
			sb.append(" ");
		}
		if (alignLR == 1)
			sb.append(inputString); // align right

		return sb.toString();
	}

}
