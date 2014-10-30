package mowItNow;

import java.util.ArrayList;
import java.util.List;

public class Mow {

	private static final List<String> cardinals = new ArrayList<String>() {
		/**
		 * Unique ID
		 */
		private static final long serialVersionUID = 1L;

		{
			add("N");
			add("E");
			add("W");
			add("S");
		}
	};

	/**
	 * Verify that the letter entered is one of the cardinal point
	 * @param letter
	 * @return
	 */
	private static boolean isCardinalLetter(String letter) {

		boolean result = false;
		for (String cardinal : cardinals) {
			if (cardinal.equals(letter)){
				result = true;
				break;
			}
			else{
				result = false;
			}
		}

		return result;
	}

	private String cardinal;
	private int mowNumber;
	private int x;

	private int y;

	public Mow(int x, int y, String cardinal, int mowNumber) {
		this.x = x;
		this.y = y;

		if (isCardinalLetter(cardinal)) {
			this.cardinal = cardinal;
		} else {
			System.out.println(cardinal + " is not a cardinal point");
		}
		this.mowNumber = mowNumber;
	}

	public String getCardinal() {
		return cardinal;
	}

	public int getMowNumber() {
		return mowNumber;
	}

	/**
	 * return the formatted output position on the console
	 * @return String
	 */
	public String getPosition() {

		StringBuffer position = new StringBuffer();

		position.append(getX()).append(" ").append(getY()).append(" ")
				.append(getCardinal());

		return position.toString();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setCardinal(String cardinal) {
		this.cardinal = cardinal;
	}

	public void setMowNumber(int mowNumber) {
		this.mowNumber = mowNumber;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * The mow musn't move if the length or height are over the lawn
	 * @param lawn
	 * @return true whether condition is respected, false otherwise
	 */
	public boolean invariantCondition(Lawn lawn) {

		boolean result = false;

		if (null == lawn) {
			result = false;
		} else {

			if ((this.getX() <= lawn.getLengthLawn()
					&& (lawn.getLengthLawn() - this.getX()) >= 0) 
					&& (this.getY() <= lawn.getHeight()
							&& (lawn.getLengthLawn() - this.getY()) >= 0)) {
				result = true;
			}else{
				result = false;
			}
		}
		return result;
	}

	/**
	 * Move forward with the command entry
	 * @param command
	 * @param lawn
	 * @return a Message if there's something wrong, nothing otherwise
	 */
	public String moveForward(String command, Lawn lawn) {

		StringBuffer result = new StringBuffer();

		if (null != command) {
			if (command.equals("A")) {

				switch (this.getCardinal()) {

				case "N":
					this.setY(this.getY() + 1);
					break;

				case "S":
					this.setY(this.getY() - 1);
					break;

				case "E":
					this.setX(this.getX() + 1);
					break;

				case "W":
					this.setX(this.getX() - 1);
					break;

				default:
					result.append("Not moving");
					break;
				}

				if (!invariantCondition(lawn)) {
					this.setX(this.getX() - 1);
					result.append("I don't move.");
				}
			} else {

				result.append("Command unknown, I stay here.");
			}

		} else {
			result.append("Command doesn't understand, I stay here.");
		}

		return result.toString();
	}

	/**
	 * Turn left with the command entry
	 * @param command
	 * @return a Message if there's something wrong, nothing otherwise
	 */
	public String turnLeft(String command) {

		StringBuffer result = new StringBuffer();

		if (null != command) {
			if (command.equals("G")) {

				switch (this.getCardinal()) {

				case "N":
					this.setCardinal("W");
					break;

				case "S":
					this.setCardinal("E");
					break;

				case "E":
					this.setCardinal("N");
					break;

				case "W":
					this.setCardinal("S");
					break;

				default:
					result.append("Not moving");
					break;
				}

			} else {

				result.append("Command unknown, I stay here.");
			}
		} else {
			result.append("Command not understand, I stay here.");
		}

		return result.toString();
	}

	/**
	 * Turn right with the command entry
	 * @param command
	 * @return a Message if there's something wrong, nothing otherwise
	 */
	public String turnRight(String command) {

		StringBuffer result = new StringBuffer();

		if (null != command) {
			if (command.equals("D")) {

				switch (this.getCardinal()) {

				case "N":
					this.setCardinal("E");
					break;

				case "S":
					this.setCardinal("W");
					break;

				case "E":
					this.setCardinal("S");
					break;

				case "W":
					this.setCardinal("N");
					break;

				default:
					result.append("Not moving");
					break;
				}

			} else {
				result.append("Command unknown, I stay here.");
			}
		} else {
			result.append("Command not understand, I stay here.");
		}

		return result.toString();
	}

}
