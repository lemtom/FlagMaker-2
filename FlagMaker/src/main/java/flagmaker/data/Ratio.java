package flagmaker.data;

public class Ratio {

	private int width;
	private int height;

	public Ratio(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Ratio(String asString) {
		String[] parts = asString.split(":");
		height = Integer.parseInt(parts[0]);
		width = Integer.parseInt(parts[1]);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return height + ":" + width;
	}
}
