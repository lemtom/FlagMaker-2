package flagmaker.overlays.overlaytypes.repeatertypes;

import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

public abstract class OverlayRepeater extends Overlay {
	protected Overlay overlay;

	public OverlayRepeater(String name, Attribute[] attributes, int maximumX, int maximumY) {
		super(name, attributes, maximumX, maximumY);
	}

	public void setOverlay(Overlay overlay) {
		this.overlay = overlay;
	}
}
