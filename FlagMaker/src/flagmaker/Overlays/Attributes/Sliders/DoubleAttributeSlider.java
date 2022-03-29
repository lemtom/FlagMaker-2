package flagmaker.Overlays.Attributes.Sliders;

import flagmaker.Extensions.ControlExtensions;
import flagmaker.Files.LocalizationHandler;
import flagmaker.Overlays.OverlayControl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DoubleAttributeSlider extends NumericAttributeSlider
{
	@FXML private Label lblName;
	@FXML private Label lblValue;
	@FXML private TextField txtValue;
	@FXML private Slider slider;
	@FXML private CheckBox chkDiscrete;
	
	private boolean isDiscrete;
	
	private double oldValue;
	private boolean wasDiscrete;
	
	public DoubleAttributeSlider(OverlayControl parent, String name, boolean isDiscrete, double value, int maximum, boolean useMaxX)
	{
		super(parent, name, useMaxX);
		load();
		
		String label = LocalizationHandler.get(name);
		lblName.setText(label);
		lblName.setTooltip(new Tooltip(label));
		this.isDiscrete = isDiscrete && (value % 1 == 0);
		chkDiscrete.setSelected(this.isDiscrete);
		chkDiscrete.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldval, Boolean newval) -> checkChanged());
		lblValue.setText(this.isDiscrete ? String.format("%d", (int)value) : String.format("%.3f", value));
		slider.setMax(maximum);
		slider.setSnapToTicks(this.isDiscrete);
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
		double currentValue = slider.getValue();
		double ratio = currentValue / slider.getMax();
		double newValue = ratio * value;
		
		slider.setMax(value);
		setValue(newValue);
	}
	
	@Override
	public Double getValue()
	{
		return slider.getValue();
	}
	
	public void setValue(double value)
	{
		triggeredByUser = false;
		slider.setValue(value);
		sliderValueChanged();
		triggeredByUser = true;
	}

	@Override
	public void setValue(Object value)
	{
		setValue((double)value);
	}
	
	public void setDiscrete(boolean isDiscrete)
	{
		this.isDiscrete = isDiscrete;
		chkDiscrete.setSelected(isDiscrete);
	}
	
	private void sliderValueChanged()
	{
		lblValue.setText(isDiscrete
				? String.format("%d", (int)slider.getValue())
				: String.format("%.3f", slider.getValue()));
		valueChanged();
	}
	
	private void checkChanged()
	{
		isDiscrete = chkDiscrete.isSelected();
		slider.setSnapToTicks(isDiscrete);
		
		if (isDiscrete)
		{
			setValue((int)Math.round(slider.getValue()));
		}
	}

	private void load()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DoubleAttributeSlider.fxml"));
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
		oldValue = slider.getValue();
		wasDiscrete = isDiscrete;
		txtValue.setText(Double.toString(oldValue));
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
				String text = txtValue.getText();
				
				if (text.contains("%"))
				{
					String stringVal = text.split("%")[0];
					try
					{
						double percentValue = Double.parseDouble(stringVal);
						setValueByFraction(percentValue / 100);
					}
					catch (Exception ex)
					{
					}
				}
				else if (text.contains("/"))
				{
					String[] fraction = text.split("/");
					if (fraction.length != 2)
					{
						return;
					}

					String numerator = fraction[0];
					String denominator = fraction[1];
					try
					{
						double num = Double.parseDouble(numerator);
						double den = Double.parseDouble(denominator);
						if (den <= 0) return;
						setValueByFraction(num / den);
					}
					catch (Exception ex)
					{
					}
				}
				else
				{
					try
					{
						double value = Double.parseDouble(text);
						chkDiscrete.setSelected(value % 1 == 0);
						slider.setValue(value);
					}
					catch (Exception ex)
					{
					}
				}
				break;
			case ESCAPE:
				chkDiscrete.setSelected(wasDiscrete);
				slider.setValue(oldValue);
				hideTxtValue();
				break;
			case DOWN:
			case UP:
				try
				{
					double value = Double.parseDouble(txtValue.getText());
					value = value + (k == KeyCode.UP ? 0.01 : -0.01);
					
					if (value < 0.0) value = 0.0;
					if (value > slider.getMax()) value = slider.getMax();
					
					chkDiscrete.setSelected(false);
					txtValue.setText(String.format("%.3f", value));
					slider.setValue(value);
				}
				catch (Exception ex)
				{
				}
				break;
		}
	}
	
	private void setValueByFraction(double fraction)
	{
		if (fraction > 1) fraction = 1;
		if (fraction < 0) fraction = 0;
		double result = fraction * slider.getMax();
		result = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

		chkDiscrete.setSelected(result % 1 == 0);
		slider.setValue(result);
	}
	
	private void hideTxtValue()
	{
		ControlExtensions.hideControl(txtValue);
		ControlExtensions.showControl(lblValue);
	}
}
