package flagmaker.data;

public class Ratio {
	public int width;
	public int height;

	public Ratio(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Ratio(String asString) {
		String[] parts = asString.split(":");
		height = Integer.parseInt(parts[0]);
		width = Integer.parseInt(parts[1]);
	}

	public String toString() {
		return height + ":" + width;
	}
}
