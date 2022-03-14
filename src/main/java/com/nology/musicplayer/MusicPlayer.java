package com.nology.musicplayer;

import com.nology.musicplayer.controller.MusicController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MusicPlayer {

    enum RendererType {
        console, web;
    }

    enum PlayerType {
        text, audio;
    }

    private void buildAndStart() {

        ApplicationContext context = new ClassPathXmlApplicationContext( "services.xml", "data-services.xml");
        MusicController controller = (MusicController) context.getBean("controller");
        controller.run();
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.buildAndStart();
    }

}
