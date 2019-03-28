package com.example.appbaitaconteudo.enums;

public enum URLEnums {

    URL_CHANNEL("https://video.nbtelecom.com.br/stream.php.m3u8?");

    private String url;

    URLEnums(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
