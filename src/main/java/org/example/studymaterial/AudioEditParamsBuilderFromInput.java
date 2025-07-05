package org.example.studymaterial;

import static org.example.controllers.MainController.getInput;

public class AudioEditParamsBuilderFromInput {

    public AudioEditParams build() {
        return new AudioEditParams(
                getAudioQuality(),
                getIsDownloadable(),
                getTitle(),
                getDescription(),
                getLink(),
                getAccessRights(),
                getLicense(),
                getLanguage(),
                getRating(),
                getViewCount(),
                getShareCount()
        );
    }

    private AudioReference.AudioQuality getAudioQuality() {
        prompt("AudioQuality (LOW, MEDIUM, HIGH, VERY_HIGH)");
        return AudioReference.audioQualityAdapter(getInput());
    }

    private boolean getIsDownloadable() {
        prompt("Is downloadable (true/false)");
        return Boolean.parseBoolean(getInput());
    }

    private String getTitle() {
        return getStringField("title");
    }

    private String getDescription() {
        return getStringField("description");
    }

    private String getLink() {
        return getStringField("link");
    }

    private String getAccessRights() {
        return getStringField("access rights");
    }

    private String getLicense() {
        return getStringField("license");
    }

    private String getLanguage() {
        return getStringField("language");
    }

    private int getRating() {
        return getIntField("rating");
    }

    private int getViewCount() {
        return getIntField("view count");
    }

    private int getShareCount() {
        return getIntField("share count");
    }

    private String getStringField(String name) {
        prompt(name);
        return getInput();
    }

    private int getIntField(String name) {
        prompt(name + " (integer)");
        return Integer.parseInt(getInput());
    }

    private void prompt(String message) {
        System.out.print(capitalize(message) + ": ");
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
