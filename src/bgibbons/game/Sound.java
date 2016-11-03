package bgibbons.game;

import java.applet.Applet;
import java.applet.AudioClip;
/**
 * Class to play the background sound for the game.
 * @author Rony Singh
 * @version 1.0 November 2016
 */
public class Sound
{
  private AudioClip clip; // Creating an audio clip
  public boolean isPlaying;
  /**
   * Constructor for the Sound object.
   * @param path 	The path to the audio file.
   */
  public Sound(String path)
  {
    try
    {
      clip = Applet.newAudioClip(Sound.class.getResource(path));//Set path
    }catch(Exception e){
        e.printStackTrace();
    }
    this.isPlaying = false;
  }
  public void play()//Play the music
  {
    try
    {
      new Thread(){
        public void run(){
          clip.loop();
        }
      }.start();
      isPlaying = true;
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }

  public void pause() {
    isPlaying = false;
    clip.stop();
  }

  public void resume() {
    isPlaying = true;
    clip.loop();
  }
}
