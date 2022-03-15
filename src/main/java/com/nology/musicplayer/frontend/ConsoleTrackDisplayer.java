package com.nology.musicplayer.frontend;

import com.github.lalyos.jfiglet.FigletFont;
import com.nology.musicplayer.data.Track;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ConsoleTrackDisplayer implements TrackDisplayer {

    interface Output {
        void out(int i);
        void out(double d);
        void out(String s);
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM, YYYY");

    private Output out = new ConsoleOutput();

    @Override
    public void displayTrack(Track track) {
        try {
            String nameFiglet = FigletFont.convertOneLine(track.getName());
            out.out( Colour.red(nameFiglet) );

            out.out( Colour.green(track.getArtist()) );
            if (track.getReleaseDate() != null) {
                out.out("Released on: " + track.getReleaseDate().format(formatter));
            }
            if (track.getStarRating() != null) {
                out.out("Rating: " + track.getStarRating().getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayTracks(List<Track> trackList) {
        int row = 1;
        for (Track track : trackList) {
            String line = String.format("%2d]  %s - %s  %s",
                    row++,
                    Colour.red( track.getName() ),
                    Colour.green( track.getArtist() ),
                    track.getStarRating() != null ? track.getStarRating().getText() : ""
            );
            out.out(line);
        }
    }

    class ConsoleOutput implements Output {

        @Override
        public void out(int i) {
            System.out.println(i);
        }

        @Override
        public void out(double d) {
            System.out.println(d);
        }

        @Override
        public void out(String s) {
            System.out.println(s);
        }
    }

}
