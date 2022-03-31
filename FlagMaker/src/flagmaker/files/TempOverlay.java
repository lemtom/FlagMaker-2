package flagmaker.files;

import flagmaker.overlays.Overlay;
import flagmaker.overlays.OverlayFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TempOverlay {
	public String type;
	public final Map<String, String> values;

	public TempOverlay() {
		values = new HashMap<>();
	}

	public Overlay toOverlay(int maximumX, int maximumY, String directory) throws Exception {
		Overlay overlay = null;

		if (values.containsKey("path")) {
			File path = FileHandler.getFilePossiblyRelative(new File(values.get("path")), directory);
			if (path != null) {
				overlay = type.equals("flag") ? OverlayFactory.getFlagInstance(path, maximumX, maximumY)
						: OverlayFactory.getImageInstance(path, maximumX, maximumY);
			}
		} else {
			overlay = OverlayFactory.getInstanceByShortName(type, maximumX, maximumY);
		}

		if (overlay == null) {
			return null;
		}

		overlay.setValuesFromStrings(values);
		return overlay;
	}
}
