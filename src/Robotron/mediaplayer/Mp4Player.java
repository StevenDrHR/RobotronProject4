package Robotron.mediaplayer;

/**
 * Created by Robin on 7/4/2017.
 */
public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void PlayOgg(String fileName) {
        //do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}