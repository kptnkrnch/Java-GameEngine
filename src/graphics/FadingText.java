package graphics;

import org.newdawn.slick.Color;

public class FadingText {
	public String text;
	public Color text_color;
	public int fade_rate;
	
	public FadingText(String text, int fade_rate) {
		if (fade_rate > 0 && fade_rate <= 255) {
			this.fade_rate = fade_rate;
		} else {
			this.fade_rate = 0;
		}
		this.text = text;
		this.text_color = new Color(255, 255, 255, 255);
	}
	
	public FadingText(String text, Color color, int fade_rate) {
		if (fade_rate > 0 && fade_rate <= 255) {
			this.fade_rate = fade_rate;
		} else {
			this.fade_rate = 0;
		}
		this.text = text;
		this.text_color = new Color(color.r, color.g, color.b, 255);
	}
	
	public void SetFadeRate(int rate) {
		if (rate > 0 && rate <= 255) {
			this.fade_rate = rate;
		}
	}
	
	public void Fade() {
		if (this.text_color != null) {
			float fade = (float)this.fade_rate / 255 / 15;
			if (this.text_color.getAlpha() - fade < 0) {
				this.text_color.a = 0;
			} else {
				this.text_color.a -= fade;
			}
		}
	}
	
	public boolean IsFaded() {
		if (this.text_color != null) {
			if (this.text_color.getAlpha() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
