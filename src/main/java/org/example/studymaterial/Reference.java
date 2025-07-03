package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    // === Construtor seguro ===
    protected Reference(String title, String description, String link, String language, boolean isDownloadable) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.language = language;
        this.isDownloadable = isDownloadable;
        this.rating = 0;
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
    }

    // === Comportamentos de domínio ===

    public void view() {
        this.viewCount++;
    }

    public void share() {
        this.shareCount++;
    }

    public void download() {
        if (!isDownloadable) {
            throw new UnsupportedOperationException("Download não permitido para esta referência.");
        }
        this.downloadCount++;
    }

    public void evaluate(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Classificação deve estar entre 0 e 5.");
        }
        this.rating = rating;
    }

    public String summary() {
        return String.format(
                "Título: %s\nDescrição: %s\nIdioma: %s\nVisualizações: %d\nDownloads: %d\nNota: %d",
                title, description, language, viewCount, downloadCount, rating
        );
    }

    // === Getters públicos úteis ===

    public String getLink() {
        return link;
    }

    public String getLanguage() {
        return language;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    // === Setters e getters protegidos para subclasses ===

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    protected void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
    }

    protected void setLicense(String license) {
        this.license = license;
    }

    protected void setLink(String link) {
        this.link = link;
    }

    protected void setLanguage(String language) {
        this.language = language;
    }

    // === Getters protegidos úteis para subclasses ===

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    protected String getAccessRights() {
        return accessRights;
    }

    protected String getLicense() {
        return license;
    }

    protected int getRating() {
        return rating;
    }

    protected int getViewCount() {
        return viewCount;
    }

    protected int getDownloadCount() {
        return downloadCount;
    }

    protected int getShareCount() {
        return shareCount;
    }

    protected void setRating(int rating) {
        this.rating = rating;
    }

    protected void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    protected void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

}
