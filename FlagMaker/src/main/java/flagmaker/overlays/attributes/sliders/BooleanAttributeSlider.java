package flagmaker.overlays.attributes.sliders;

import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.OverlayControl;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class BooleanAttributeSlider extends AttributeSlider {
	@FXML
	private Label lblName;
	@FXML
	private CheckBox chkEnabled;

	public BooleanAttributeSlider(OverlayControl parent, String name, boolean value) {
		super(parent, name);
		load();

		String label = LocalizationHandler.get(name);
		lblName.setText(label);
		lblName.setTooltip(new Tooltip(label));
		chkEnabled.setSelected(value);
		chkEnabled.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean oldval, Boolean newval) -> {
					if (triggeredByUser && !newval.equals(oldval))
						valueChanged();
					triggeredByUser = true;
				});
	}

	@Override
	public Boolean getValue() {
		return chkEnabled.isSelected();
	}

	public void setValue(boolean value) {
		triggeredByUser = false;
		chkEnabled.setSelected(value);
		triggeredByUser = true;
	}

	@Override
	public void setValue(Object value) {
		setValue((boolean) value);
	}

	private void load() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BooleanAttributeSlider.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (Exception ex) {
		}
	}
}
