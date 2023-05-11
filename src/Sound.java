import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip ;
    private ArrayList<URL> listURL = new ArrayList<URL>() ;

    public Sound(ArrayList<String> listPath){
        for(int i = 0; i < listPath.size(); i++){
            listURL.add(getClass().getResource(listPath.get(i))) ;
        }
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(listURL.get(i)) ;
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start() ;
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
