package flagmaker.data;

import flagmaker.divisions.Division;
import flagmaker.divisions.DivisionFesses;
import flagmaker.divisions.DivisionGrid;
import flagmaker.divisions.DivisionPales;
import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.ColorAttribute;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayPath;
import flagmaker.overlays.overlaytypes.repeatertypes.OverlayRepeater;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Flag {
	private String name;
	private Ratio ratio;
	private Ratio gridSize;
	private Division division;
	private Overlay[] overlays;

	public Flag(String name, Ratio ratio, Ratio gridSize, Division division, Overlay[] overlays) {
		this.name = name;
		this.ratio = ratio;
		this.gridSize = gridSize;
		this.division = division;
		this.overlays = overlays;
	}

	public static String getFlagPath() {
		return null;
	}

	public void draw(Pane canvas) {
		canvas.getChildren().clear();
		division.draw(canvas);
		setRepeaterOverlays();

		for (int i = 0; i < overlays.length; i++) {
			// Skip flagmaker.overlays used in repeaters or disabled in editor
			if ((i > 0 && overlays[i - 1] instanceof OverlayRepeater) || !overlays[i].isEnabled) {
				continue;
			}
			overlays[i].draw(canvas);
		}
	}

	public String exportToString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("name=%s\n", name));
		sb.append(String.format("ratio=%d:%d\n", ratio.getHeight(), ratio.getWidth()));
		sb.append(String.format("gridSize=%s\n\n", gridSize.toString()));
		sb.append(String.format("type=%s\n", division.getName()));

		for (int i = 0; i < division.colors.length; i++) {
			sb.append(String.format("flagmaker.color%d=%s\n", i + 1, ColorExtensions.toHexString(division.colors[i], false)));
		}

		for (int i = 0; i < division.values.length; i++) {
			sb.append(String.format("size%d=%d\n", i + 1, division.values[i]));
		}

		for (Overlay overlay : overlays) {
			sb.append(overlay.exportToString());
		}

		return sb.toString();
	}

	public void shuffleColors() {
		rotateColors(colorsUsed());
	}

	public void rotateColors(Color[] colorsUsed) {
		boolean skip2 = division instanceof DivisionGrid && division.values[0] == 1;
		division.colors[0] = getNextColor(division.colors[0], colorsUsed);

		if (!skip2) {
			division.colors[1] = getNextColor(division.colors[1], colorsUsed);
		}

		if (division instanceof DivisionPales || division instanceof DivisionFesses) {
			division.colors[2] = getNextColor(division.colors[2], colorsUsed);
		}

		for (Overlay overlay : overlays) {
			for (Attribute a : overlay.getAttributes()) {
				if (a instanceof ColorAttribute) {
					ColorAttribute c = (ColorAttribute) a;

					if (a.getName().equalsIgnoreCase("StrokeColor")) {
						double strokeWidth = overlay.getDoubleAttribute("Stroke");
						if (strokeWidth > 0) {
							c.setValue(getNextColor(c.value, colorsUsed));
						}
					} else {
						c.setValue(getNextColor(c.value, colorsUsed));
					}
				}
			}

			if (overlay instanceof OverlayFlag) {
				((OverlayFlag) overlay).flag.rotateColors(colorsUsed);
			}
		}
	}

	public Color[] colorsUsed() {
		ArrayList<Color> colors = new ArrayList<>();

		if (division instanceof DivisionGrid && division.values[0] == 1 && division.values[1] == 1) {
			colors.add(division.colors[0]);
		} else {
			colors.addAll(Arrays.asList(division.colors));
		}

		for (Overlay overlay : overlays) {
			if (overlay instanceof OverlayFlag) {
				colors.addAll(Arrays.asList(((OverlayFlag) overlay).flag.colorsUsed()));
			} else if (overlay instanceof OverlayPath) {
				OverlayPath p = (OverlayPath) overlay;
				colors.add(p.getColorAttribute("Color"));
				if (p.getDoubleAttribute("Stroke") > 0) {
					colors.add(p.getColorAttribute("StrokeColor"));
				}
			} else {
				for (Attribute a : overlay.getAttributes()) {
					if (a instanceof ColorAttribute) {
						colors.add(((ColorAttribute) a).value);
					}
				}
			}
		}

		Set<Color> hs = new HashSet<>(colors);
		Color[] returnValue = new Color[] {};
		return hs.toArray(returnValue);
	}

	private Color getNextColor(Color c, Color[] colors) {
		int index = Arrays.asList(colors).indexOf(c);
		return colors[(index + 1) % colors.length];
	}

	public void setRepeaterOverlays() {
		// Clear last repeater in list
		if (overlays.length > 0 && overlays[overlays.length - 1] instanceof OverlayRepeater) {
			((OverlayRepeater) overlays[overlays.length - 1]).setOverlay(null);
		}

		// Set flagmaker.overlays for others
		for (int i = overlays.length - 1; i > 0; i--) {
			if (overlays[i - 1] instanceof OverlayRepeater) {
				OverlayRepeater repeater = (OverlayRepeater) overlays[i - 1];
				repeater.setOverlay(overlays[i]);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ratio getRatio() {
		return ratio;
	}

	public void setRatio(Ratio ratio) {
		this.ratio = ratio;
	}

	public Ratio getGridSize() {
		return gridSize;
	}

	public void setGridSize(Ratio gridSize) {
		this.gridSize = gridSize;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Overlay[] getOverlays() {
		return overlays;
	}

	public void setOverlays(Overlay[] overlays) {
		this.overlays = overlays;
	}
}
