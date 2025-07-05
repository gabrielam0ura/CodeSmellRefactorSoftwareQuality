package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {

    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality, String title, String description, String link, String language, boolean isDownloadable) {
        super(title, description, link, language, isDownloadable);
        this.audioQuality = quality;
    }

    public AudioReference(AudioQuality quality) {
        super("", "", "", "", false);
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void editAudio(AudioEditParams params) {
        editBasic(params.title(), params.description(), params.link());
        this.setAccessRights(params.accessRights());
        this.setLicense(params.license());
        this.setAudioQuality(params.audioQuality());
        editVideoAttributes(params.rating(), params.language(), params.viewCount(), params.shareCount(), params.isDownloadable());
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        AudioEditParams params = new AudioEditParams(
                audioQuality,
                isDownloadable,
                properties.get(0), // title
                properties.get(1), // description
                properties.get(2), // link
                properties.get(3), // accessRights
                properties.get(4), // license
                properties.get(5), // language
                intProperties.get(0), // rating
                intProperties.get(1), // viewCount
                intProperties.get(2)  // shareCount
        );
        this.editAudio(params);
    }

    private void editVideoAttributes(int rating, String language, int viewCount, int shareCount, boolean isDownloadable) {
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setDownloadable(isDownloadable);
        this.setLanguage(language);
    }

    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }
}
