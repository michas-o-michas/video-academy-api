package com.video.academy.academy.dtos;

import com.video.academy.academy.enums.Privacy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlaylistRecordDto(@NotBlank String title, @NotNull String description, Privacy privacy, @NotNull String thumbnail_url) {
    public PlaylistRecordDto {
        if (privacy == null) {
            privacy = Privacy.PRIVATE;
        }

    }
}
