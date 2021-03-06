package musicplayer;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlay {

    // size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 4096;

    /**
     * Method that plays the given audio file
     *
     * @param audioFilePath Path of the audio file
     */
    public void play(String audioFilePath) {

        File audioFile = new File(audioFilePath);

        try {

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);

            audioLine.open(format);

            audioLine.start();

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) { audioLine.write(bytesBuffer, 0, bytesRead); }

            audioLine.drain();
            audioLine.close();
            audioStream.close();

        }catch (UnsupportedAudioFileException ex) {
            System.out.println("The audio file is not supported");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file");
            ex.printStackTrace();
        }
    }
}