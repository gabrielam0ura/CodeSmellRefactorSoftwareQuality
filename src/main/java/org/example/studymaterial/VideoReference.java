package org.example.studymaterial;

public class VideoReference extends Reference {
    private boolean isAvailable;
    private String resolution;
    private String frameRate;
    private String videoFormat;

    public VideoReference(String title, String description) {
        super(title, description, null, null, false);
    }

    public VideoReference(boolean isAvailable, String title, String description, String resolution, String frameRate, String videoFormat, String accessRights) {
        super(title, description, null, null, false);
        this.isAvailable = isAvailable;
        this.resolution = resolution;
        this.frameRate = frameRate;
        this.videoFormat = videoFormat;
        this.setAccessRights(accessRights);
    }

    public void editAvailability(boolean isAvailable, boolean isDownloadable) {
        this.isAvailable = isAvailable;
        this.setDownloadable(isDownloadable);
    }

    public boolean handleStreamAvailability() {
        if (!isAvailable) {
            return false;
        } else if (!this.getIsDownloadable()) {
            return false;
        }
        return true;
    }
}
