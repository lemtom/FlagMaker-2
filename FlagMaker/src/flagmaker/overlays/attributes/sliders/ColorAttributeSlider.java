package flagmaker.overlays.attributes.sliders;

import flagmaker.color.ColorButton;
import flagmaker.color.ColorButtonListener;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.OverlayControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

public class ColorAttributeSlider extends AttributeSlider implements ColorButtonListener
{
	@FXML private Label lblName;
	@FXML private ColorButton picker;
		
	public ColorAttributeSlider(OverlayControl parent, String name, Color value)
	{
		super(parent, name);
		load();
		picker.setListener(parent.mainWindow, parent.stage, this);
		
		String label = LocalizationHandler.get(name);
		lblName.setText(label);
		lblName.setTooltip(new Tooltip(label));
		picker.setValue(value);
	}
	
	@Override
	public Color getValue()
	{
		return picker.getValue();
	}
	
	public void setValue(Color value)
	{
		triggeredByUser = false;
		picker.setValue(value);
		triggeredByUser = true;
	}

	@Override
	public void setValue(Object value)
	{
		setValue((Color)value);
	}

	private void load()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ColorAttributeSlider.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception ex)
		{
			String s = ex.getMessage();
		}
	}

	@Override
	public void colorChanged(Color oldval, Color newval)
	{
			if (triggeredByUser && !newval.equals(oldval)) valueChanged();
			triggeredByUser = true;
	}
}
