package flagmaker.overlays.attributes;

public abstract class NumericAttribute extends Attribute
{
	public int maximum;
	public boolean useMaxX;
	
	protected NumericAttribute(String name, int maximum, boolean useMaxX)
	{
		super(name);
		this.maximum = maximum;
		this.useMaxX = useMaxX;
	}
}
