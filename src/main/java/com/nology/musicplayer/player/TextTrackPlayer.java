package com.nology.musicplayer.player;

import com.nology.musicplayer.data.Track;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;

public class TextTrackPlayer implements TrackPlayer {

    private int stepIncrement = 2;
    private int maxRenderedLength = 150;

    @Override
    public void playTrack(Track track) {

        ProgressBarBuilder builder = new ProgressBarBuilder()
                .setStyle(ProgressBarStyle.COLORFUL_UNICODE_BLOCK)
                .setTaskName(track.getName() + " ...")
                .setMaxRenderedLength(this.maxRenderedLength)
                .setInitialMax(100);

        try (ProgressBar pb = builder.build()) {
            for (int i = 0; i < 100; i += this.stepIncrement) {
                pb.stepBy(this.stepIncrement);
                try {
                    Thread.sleep(70 );
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void stop() {
        // nothing to do
    }

    @Override
    public void pause() {
        // nothing to do
    }

    @Override
    public void resume() {
        // nothing to do
    }

    public void setStepIncrement(int stepIncrement) {
        this.stepIncrement = stepIncrement;
    }

    public void setMaxRenderedLength(int maxRenderedLength) {
        this.maxRenderedLength = maxRenderedLength;
    }
}
