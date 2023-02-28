package projectFinish1;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class InGameBGM {
	
	private Clip clip;
	private FloatControl gainControl;
	
	public InGameBGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/InfiniteDoors.wav"));
			clip = AudioSystem.getClip();
			clip.open(ais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public FloatControl getGainControl() {
		return gainControl;
	}

	public void setGainControl(FloatControl gainControl) {
		this.gainControl = gainControl;
	}
	
}
