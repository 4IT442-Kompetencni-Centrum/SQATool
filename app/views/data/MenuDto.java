package views.data;

public class MenuDto {
	private String label;
	private String url;
	private String glyphicon;
	private boolean selected = false;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGlyphicon() {
		return glyphicon;
	}
	public void setGlyphicon(String glyphicon) {
		this.glyphicon = glyphicon;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
