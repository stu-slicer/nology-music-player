package com.nology.musicplayer.audio;

import com.nology.musicplayer.data.Track;
import com.nology.musicplayer.player.TrackPlayer;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class AudioTrackPlayer implements TrackPlayer {

    /*
     * Base directory for all the music .wav files.
     */
    private static final String MUSIC_DIR = "music\\";

    private static final List<String> MUSIC = new ArrayList<>();

    static {
        MUSIC.add(MUSIC_DIR + "bensound-cute.wav");
        MUSIC.add(MUSIC_DIR + "bensound-dance.wav");
        MUSIC.add(MUSIC_DIR + "bensound-funkyelement.wav");
        MUSIC.add(MUSIC_DIR + "bensound-goinghigher.wav");
        MUSIC.add(MUSIC_DIR + "bensound-happyrock.wav");
        MUSIC.add(MUSIC_DIR + "bensound-perception.wav");
        MUSIC.add(MUSIC_DIR + "bensound-rumble.wav");
    }

    enum Status {
        stopped
        ,playing
        ,paused;
    }

    /**
     * Time to limit the track, in milliseconds.
     */
    private long playTime = TimeUnit.SECONDS.toMillis(25 );
    private Thread stopThread;
    private String currentFile;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private long currentFrame;
    private Track current;
    private Status status = Status.stopped;

    @Override
    public void playTrack(Track track) {
        stop();
        try {
            setupClipAndStream(track);
            play();
            this.current = track;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start playing the track, kick off the stop thread to ensure only plays for given time.
     */
    private void play() {
        this.clip.start();
        this.status = Status.playing;

        // start the thead that will stop the playback after given time.
        this.stopThread = new Thread(() -> {
            try {
                Thread.sleep( this.playTime );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (status.equals(Status.playing)) {
                stop();
            }
        });
        this.stopThread.start();
    }

    /**
     * Shutdown the stop thread.
     * Shutdown by interrupting the thread, rather than stopping - cleaner.
     */
    private void shutdownStopThread() {
        if (this.stopThread != null) {
            this.stopThread.interrupt();
        }
    }

    @Override
    public void stop() {
        if (this.status == Status.playing || this.status == Status.paused) {
            this.clip.stop();
            this.clip.close();
            this.currentFrame = 0L;
            this.status = Status.stopped;
            this.shutdownStopThread();
        }
    }

    @Override
    public void pause() {
        if (this.status == Status.playing) {
            this.currentFrame = this.clip.getMicrosecondPosition();
            clip.stop();
            this.status = Status.paused;
        }
    }

    @Override
    public void resume() {
        if (this.status == Status.paused) {
            clip.close();
            resetAudioStream();
            clip.setMicrosecondPosition(currentFrame);
            this.play();
        }
    }

    public void resetAudioStream() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(this.currentFile).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(10); // let clip catch up
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Create the {@link AudioInputStream} add {@link Clip} ready for playing.
     * @param track
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    private void setupClipAndStream(Track track) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.currentFile = getClipFilePath(track);

        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(currentFile).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    /**
     * Get track based on track's name and artist.
     * @param track
     * @return
     */
    private String getClipFilePath(Track track) {
        int index = Math.abs((track.getName() + track.getArtist()).hashCode()) % MUSIC.size();
        return MUSIC.get(index);
    }

}
