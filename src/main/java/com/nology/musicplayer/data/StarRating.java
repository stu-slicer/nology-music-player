package com.nology.musicplayer.data;

public enum StarRating {

    _5Stars("5 stars")
    ,_4Stars("4 stars")
    ,_3Stars("3 stars")
    ,_2Stars("2 stars")
    ,_1Star("1 star");

    private String text;

    StarRating(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
