package flagmaker.overlays.attributes;

import flagmaker.overlays.OverlayControl;
import flagmaker.overlays.attributes.sliders.AttributeSlider;

public abstract class Attribute {
	protected String name;

	protected Attribute(String name) {
		this.name = name;
	}

	public abstract void setValue(Object value);

	public abstract void setValue(String value);

	public abstract Object getValue();

	public abstract AttributeSlider getSlider(OverlayControl parent);

	public abstract Attribute clone();

	public abstract String exportAsString();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
