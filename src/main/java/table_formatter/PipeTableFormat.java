
package table_formatter;

import java.util.Arrays;

import string_formatter.StringFormatter;

public class PipeTableFormat {
	public static String[] preprocess(String[] string) { // divide string into several independent tables
		int ending = 0;
		int beginning = 0;
		while (ending < string.length) {
			int count = 0;
			for (int i = ending; i < string.length; i++) {
				ending = i;
				if (string[i].trim().isEmpty() || !string[i].trim().contains("|")) {
					break;
				} else {
					count++;
				}
			}
			int temp = beginning + count;
			String[] table = Arrays.copyOfRange(string, beginning, temp);
			if (table.length > 0) {
				format(table);
			}
			for (int i = beginning; i < temp; i++) {
				string[i] = table[i - beginning];
			}
			beginning = ++ending;
		}

		return string;
	}

	public static String[] format(String[] string) {
		StringFormatter stringFormatter = new StringFormatter();
		if (string.length == 1) {
			return string;
		}
		String tempSecondLine = string[1];
		boolean judge = true;

		judge = checkSecondLine(tempSecondLine);
		if (!judge) {
			return string;
		}

		String[][] format = new String[string.length][];
		int lengthOfComponents = 0;
		int lengthOfString = string.length;
		boolean beginningPipe = true;
		boolean closingPipe = true;
		if (string[0].trim().charAt(0) == '|') {
			beginningPipe = true;
		} else {
			beginningPipe = false;
		}
		if (string[0].trim().charAt(string[0].trim().length() - 1) == '|') {
			closingPipe = true;
		} else {
			closingPipe = false;
		}
		string[0] = stringFormatter.format(string[0]);
		format[0] = string[0].split("\\|", -1);
		lengthOfComponents = format[0].length;
		for (int i = 1; i < lengthOfString; i++) {
			string[i] = stringFormatter.format(string[i]); // format every line
			applyPatternOfFirstLine(string, beginningPipe, closingPipe, i);
			format[i] = string[i].split("\\|", -1);
			if (format[i].length > lengthOfComponents) { // calculate the number of
															// columns
				lengthOfComponents = format[i].length;
			}
		}
		int length = format.length;
		for (int i = 0; i < length; i++) {
			format[i] = Arrays.copyOf(format[i], lengthOfComponents);
		}
		formatEachComponent(format, lengthOfComponents, length);
		for (int i = 0; i < length; i++) {
			join(string, format, i);
		}
		return string;
	}

	// does every line in table has beginning pipe or closing pipe is determined by
	// the pattern of first line
	private static void applyPatternOfFirstLine(String[] string, boolean beginningPipe, boolean closingPipe, int i) {
		boolean charAtZero = string[i].charAt(0) == '|';
		boolean charAtLast = string[i].charAt(string[i].length() - 1) == '|';
		if (beginningPipe) {
			if (!charAtZero) {
				string[i] = "| " + string[i];
			}
		} else {
			if (charAtZero) {
				string[i] = string[i].substring(2, string[i].length());
			}
		}
		if (closingPipe) {
			if (!charAtLast) {
				string[i] = string[i] + " |";
			}
		} else {
			if (charAtLast) {
				string[i] = string[i].substring(0, string[i].length() - 2);
			}
		}
	}

	private static boolean checkSecondLine(String tempSecondLine) {
		if (tempSecondLine.trim().charAt(tempSecondLine.trim().length() - 1) != '|') {
			tempSecondLine += '|';
		}
		if (tempSecondLine.trim().charAt(0) != '|') {
			tempSecondLine = '|' + tempSecondLine;
		}
		boolean judge = true;
		String[] secondLine = tempSecondLine.trim().split("\\|", -1);
		int lengthOfSecondLine = secondLine.length;
		for (int i = 1; i < lengthOfSecondLine - 1; i++) {
			boolean checkEveryChar = checkEverySplitedComponent(secondLine, i);
			if (!checkEveryChar) {
				judge = false;
				break;
			}
		}
		return judge;
	}

	private static boolean checkEverySplitedComponent(String[] secondLine, int i) {
		int tempLength = secondLine[i].trim().length() - 1;
		boolean checkEveryChar = true;
		if (tempLength < 0 || ((secondLine[i].trim().charAt(0) != '-' && secondLine[i].trim().charAt(0) != ':')
				|| (secondLine[i].trim().charAt(tempLength) != '-'
						&& secondLine[i].trim().charAt(tempLength) != ':'))) {
			checkEveryChar = false;
		}
		for (int j = 1; j < tempLength; j++) {
			if (secondLine[i].trim().charAt(j) != '-') {
				checkEveryChar = false;
				break;
			}
		}
		if (secondLine[i].trim().isEmpty()) {
			checkEveryChar = false;
		}
		return checkEveryChar;
	}

	private static void formatEachComponent(String[][] format, int lengthOfComponents, int length) {
		for (int m = 0; m < lengthOfComponents; m++) {
			int lengthOfColumn = getLengthOfColumn(format,lengthOfComponents, m);
			for (int n = 0; n < length; n++) {
				// Ignore the second line, because we want to trim the hyphens if needed
				if (format[n][m] != null) {
					addContentToEveryComponent(format, lengthOfComponents, lengthOfColumn, m, n);
				} else if (n == 1) {
					boolean isLast = false;
					if (m == lengthOfComponents - 1) {
						isLast = true;
					}
					// isFirst is always false because format[n][m] == null only happens at the end of array
					// when I add the length of array
					format[n][m] = addHyphen("", lengthOfColumn, false, isLast);
				} else {
					format[n][m] = addSpaces(lengthOfColumn);
				}
			}
		}
	}

	private static void addContentToEveryComponent(String[][] format, int lengthOfComponents, int lengthOfColumn, int m,
			int n) {
		int numberOfSpaces = lengthOfColumn - format[n][m].length();
		if (n == 1) {
			boolean isLast = false;
			boolean isFirst = false;
			if (m == lengthOfComponents - 1) {
				isLast = true;
			}
			if(m == 0) {
				isFirst = true;
			}
			format[n][m] = addHyphen(format[n][m], numberOfSpaces, isFirst, isLast);
		} else {
			format[n][m] += addSpaces(numberOfSpaces);
		}
	}

	private static String addHyphen(String format, int numberOfSpaces, boolean isFirst, boolean isLast) {
		if (!format.isEmpty()) {
			if (numberOfSpaces >= 0) {
				if (isFirst) {
					format = format.substring(0, 1) + stringBuilderAppend(numberOfSpaces)
					+ format.substring(1, format.length());
				} else if (isLast || format.charAt(format.length() - 1) == ' ') {
					format = format.substring(0, 2) + stringBuilderAppend(numberOfSpaces)
							+ format.substring(2, format.length());
				} else {
					format = format.substring(0, 2) + stringBuilderAppend(numberOfSpaces - 1)
							+ format.substring(2, format.length()) + " ";
				}
			} else {
				format = format.substring(0, 2) + format.substring(2 + numberOfSpaces * -1, format.length());
			}
		} else if (numberOfSpaces > 2) {
			if (isLast) {
				format = " " + stringBuilderAppend(numberOfSpaces - 1);
			} else {
				format = " " + stringBuilderAppend(numberOfSpaces - 2) + " ";
			}
		}
		return format;
	}

	private static String stringBuilderAppend(int numberOfSpaces) {
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < numberOfSpaces; i++) {
			strB.append("-");
		}
		return strB.toString();
	}

	private static String addSpaces(int numberOfSpaces) {
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < numberOfSpaces; i++) {

			strB.append(" ");
		}
		return strB.toString();
	}

	private static void join(String[] string, String[][] format, int i) {
		string[i] = "";
		int lengthOfFormatI = format[i].length;
		for (int j = 0; j < lengthOfFormatI - 1; j++) {
			string[i] += format[i][j] + "|";
		}
		if (!format[i][lengthOfFormatI - 1].isEmpty()) {
			string[i] += format[i][lengthOfFormatI - 1] + "|";
			string[i] = string[i].substring(0, string[i].length() - 1);
		}
	}

	private static int getLengthOfColumn(String[][] format, int lengthOfComponents, int m) {
		int lengthOfColumn = 0;
		int lengthOfFormat = format.length;
		for (int n = 0; n < lengthOfFormat; n++) {
			// Ignore second line, because length is adjusted by other content
			if (n != 1 && format[n][m] != null && format[n][m].length() > lengthOfColumn) {
				lengthOfColumn = format[n][m].length();
			}
		}
		// for the first last column in table, it at least needs 3 hyphens + 1 space
		// for other columns in table, it needs 3 hyphens + 2 spaces
		if (m == 0 || m == lengthOfComponents - 1) {
			if (lengthOfColumn > 0 && lengthOfColumn < 4)
				lengthOfColumn = 4;
		} else {
			if (lengthOfColumn < 5)
				lengthOfColumn = 5;
		}
		int lengthOfThreeHyphens = 0;
		int counter = 0;
		lengthOfThreeHyphens = calculateMinimumLength(format, m, lengthOfThreeHyphens, counter);
		if (lengthOfColumn > 0 && lengthOfColumn < lengthOfThreeHyphens)  lengthOfColumn = lengthOfThreeHyphens;
		return lengthOfColumn;
	}

	// commonmark requires at least 3 hyphens, so for condition like | :-- | its minimum length should be 6
	private static int calculateMinimumLength(String[][] format, int m, int lengthOfThreeHyphens, int counter) {
		if (format[1][m] != null) {
			int lentghOfSecondLine = format[1][m].length();
			for (int i = 0; i < lentghOfSecondLine; i++) {
				if (format[1][m].charAt(i) == '-') {
					counter++;
				}
			}
			if (counter < 3)  lengthOfThreeHyphens = 3 - counter + format[1][m].length();
		}
		return lengthOfThreeHyphens;
	}
}
