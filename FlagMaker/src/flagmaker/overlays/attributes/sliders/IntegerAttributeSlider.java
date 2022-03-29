package flagmaker.overlays.attributes.sliders;

import flagmaker.extensions.ControlExtensions;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.OverlayControl;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class IntegerAttributeSlider extends NumericAttributeSlider
{
	@FXML private Label lblName;
	@FXML private Label lblValue;
	@FXML private TextField txtValue;
	@FXML private Slider slider;
	
	private int oldValue;
		
	public IntegerAttributeSlider(OverlayControl parent, String name, int value, int maximum, boolean useMaxX)
	{
		super(parent, name, useMaxX);
		load();
		
		String label = LocalizationHandler.get(name);
		lblName.setText(label);
		lblName.setTooltip(new Tooltip(label));
		lblValue.setText(String.format("%d", (int)value));
		slider.setMax(maximum);
		slider.setValue(value);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) ->
		{
			if (triggeredByUser && !oldval.equals(newval)) sliderValueChanged();
			triggeredByUser = true;
		});
		ControlExtensions.hideControl(txtValue);
		txtValue.setOnKeyPressed((KeyEvent event) -> txtValueKeyDown(event));
		txtValue.focusedProperty().addListener((arg0, oldval, newval) ->
		{
			if (oldval && !newval)
			{
				hideTxtValue();
			}
		});
	}
	
	@Override
	public int getMaximum()
	{
		return (int)slider.getMax();
	}
	
	@Override
	public void setMaximum(int value)
	{
		slider.setMax(value);
	}
	
	@Override
	public Integer getValue()
	{
		return (int)slider.getValue();
	}
	
	public void setValue(int value)
	{
		triggeredByUser = false;
		slider.setValue(value);
		sliderValueChanged();
		triggeredByUser = true;
	}

	@Override
	public void setValue(Object value)
	{
		setValue((int)value);
	}
	
	private void sliderValueChanged()
	{
		lblValue.setText(String.format("%d", (int)slider.getValue()));
		valueChanged();
	}

	private void load()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("IntegerAttributeSlider.fxml"));
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
	
	@FXML
	private void clicked()
	{
		ControlExtensions.hideControl(lblValue);
		ControlExtensions.showControl(txtValue);
		oldValue = (int)slider.getValue();
		txtValue.setText(Integer.toString((int)slider.getValue()));
		txtValue.selectAll();
		txtValue.requestFocus();
	}
	
	@FXML
	private void txtValueKeyDown(KeyEvent e)
	{
		KeyCode k = e.getCode();
		switch (k)
		{
			case ENTER:
				hideTxtValue();
				try
				{
					slider.setValue(Integer.parseInt(txtValue.getText()));
				}
				catch (Exception ex)
				{
				}
				break;
			case ESCAPE:
				slider.setValue(oldValue);
				hideTxtValue();
				break;
			case DOWN:
			case UP:
				try
				{
					int value = Integer.parseInt(txtValue.getText());
					value = value + (k == KeyCode.UP ? 1 : -1);
					
					if (value < 0) value = 0;
					if (value > slider.getMax()) value = (int)slider.getMax();
					
					txtValue.setText(String.format("%d", value));
					slider.setValue(value);
				}
				catch (Exception ex)
				{
				}
				break;
		}
	}
	
	private void hideTxtValue()
	{
		ControlExtensions.hideControl(txtValue);
		ControlExtensions.showControl(lblValue);
	}
}
