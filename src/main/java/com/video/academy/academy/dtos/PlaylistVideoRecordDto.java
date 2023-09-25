package com.video.academy.academy.dtos;

import com.video.academy.academy.enums.Privacy;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PlaylistVideoRecordDto(@NotNull UUID video_id, UUID playlist_id, Privacy privacy) {
    public PlaylistVideoRecordDto {
        if (privacy == null) {
            privacy = Privacy.PRIVATE;
        }
    }
}

