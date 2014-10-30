package mowItNow;

public class Lawn {
	
	private int height;
	private int lengthLawn;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Lawn(int x, int y) {

		this.lengthLawn = x;
		this.height = y;
	}

	public int getHeight() {
		return height;
	}

	public int getLengthLawn() {
		return lengthLawn;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLengthLawn(int lengthLawn) {
		this.lengthLawn = lengthLawn;
	}

}
