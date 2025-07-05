package org.example.studymaterial;

public record AudioEditParams(
        AudioReference.AudioQuality audioQuality,
        boolean isDownloadable,
        String title,
        String description,
        String link,
        String accessRights,
        String license,
        String language,
        int rating,
        int viewCount,
        int shareCount
) {}
