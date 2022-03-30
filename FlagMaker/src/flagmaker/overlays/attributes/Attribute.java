package flagmaker.overlays.attributes;

import flagmaker.overlays.OverlayControl;
import flagmaker.overlays.attributes.sliders.AttributeSlider;

public abstract class Attribute<T> {
	public String name;

	protected Attribute(String name) {
		this.name = name;
	}

	public abstract <T> void setValue(T value);

	public abstract <T> void setValue(String value);

	public abstract <T> T getValue();

	public abstract AttributeSlider getSlider(OverlayControl parent);

	public abstract Attribute clone();

	public abstract String exportAsString();
}
