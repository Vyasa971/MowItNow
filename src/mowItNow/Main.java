/**
 * 
 */
package mowItNow;

import java.io.IOException;
import java.util.List;
import static java.lang.System.out;
import static java.lang.System.err;

/**
 * @author samy
 *
 */
public class Main {

	private static final String patternLength = "\\d{2}";
	private static final String patternline = "\\d{2}N|\\d{2}E|\\d{2}W|\\d{2}S";

	private static Mow createMow(Mow mow, List<String> sortedLines, int line) {
		if (isLineDefineMowPosition(sortedLines, line)) {

			mow = initMow(sortedLines, line);

		} else {
			out.println("It's not legal it should be a mow position text here");
		}
		return mow;
	}

	/**
	 * Get each char from the line
	 * 
	 * @param sortedLines
	 * @param line
	 * @return char[] of the line
	 */
	private static char[] getCharactersFromLine(List<String> sortedLines,
			int line) {
		return sortedLines.get(line).toCharArray();
	}

	/**
	 * Get the first line
	 * 
	 * @param sortedLines
	 * @return first line in char[]
	 */
	private static char[] getFirstLine(List<String> sortedLines) {
		return sortedLines.get(0).toCharArray();
	}

	/**
	 * Initialization of the lawn
	 * 
	 * @param sortedLines
	 * @return Lawn
	 */
	private static Lawn initLawn(List<String> sortedLines) {
		char[] length = getFirstLine(sortedLines);
		Lawn lawn = new Lawn(Character.getNumericValue(length[0]),
				Character.getNumericValue(length[1]));
		return lawn;
	}

	/**
	 * Initialization of the Mow
	 * 
	 * @param sortedLines
	 * @param line
	 * @return Mow
	 */
	private static Mow initMow(List<String> sortedLines, int line) {
		Mow mow;
		char[] position = getCharactersFromLine(sortedLines, line);

		mow = null;
		mow = new Mow(Character.getNumericValue(position[0]),
				Character.getNumericValue(position[1]),
				String.valueOf(position[2]),
				line);
		out.println("I'm mow number : " + mow.getMowNumber() + " my position is : " + mow.getPosition());
		return mow;
	}

	/**
	 * is it the first line that define the lawn square
	 * 
	 * @param sortedLines
	 * @return true or false
	 */
	private static boolean isFirstLineLawnLength(List<String> sortedLines) {
		return sortedLines.get(0).matches(patternLength);
	}

	/**
	 * is it the position of the mow
	 * 
	 * @param sortedLines
	 * @param line
	 * @return true or false
	 */
	private static boolean isLineDefineMowPosition(List<String> sortedLines,
			int line) {
		return sortedLines.get(line).matches(patternline);
	}

	/**
	 * is it the line for init mow
	 * 
	 * @param line
	 * @return true or false
	 */
	private static boolean isMowLine(int line) {
		return line % 2 == 0;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		MowFileManagement mowFile = new MowFileManagement();
		Mow mow = null;
		List<String> sortedLines;

		try {

			sortedLines = mowFile.getLinesFromFile();

			if (isFirstLineLawnLength(sortedLines)) {

				Lawn lawn = initLawn(sortedLines);

				// Remove the position line
				sortedLines.remove(0);

				for (int line = 0; line < sortedLines.size(); line++) {

					if (isMowLine(line)) {
						mow = createMow(mow, sortedLines, line);

					} else {
						char[] ctrl = getCharactersFromLine(sortedLines, line);
						mowInMotion(mow, lawn, ctrl);
					}
				}
			} else {
				out.println("Wrong way ! I keep my position");
			}

		} catch (IOException e) {
			err.println("File not found : " + e.getMessage());
		}

	}

	/**
	 * Mow moves, turns
	 * 
	 * @param mow
	 * @param lawn
	 * @param ctrl
	 */
	private static void mowInMotion(Mow mow, Lawn lawn, char[] ctrl) {
		for (char command : ctrl) {

			if ("G".equals(String.valueOf(command))) {
				mow.turnLeft(String.valueOf(command));
				System.out.println("Left : " + command);
			}

			if ("D".equals(String.valueOf(command))) {
				mow.turnRight(String.valueOf(command));
				System.out.println("Right : " + mow.getPosition());
			}

			if ("A".equals(String.valueOf(command))) {
				mow.moveForward(String.valueOf(command), lawn);
				System.out.println("Forward : " + mow.getPosition());
			}
		}
	}
}
