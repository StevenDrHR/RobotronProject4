package Robotron.mediaplayer;

/**
 * Created by Robin on 7/4/2017.
 */
public class OggPlayer implements AdvancedMediaPlayer{
    @Override
    public void PlayOgg(String fileName) {
        System.out.println("Playing Ogg file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //do nothing
    }
}