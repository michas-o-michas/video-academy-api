package com.video.academy.academy.dtos;

import com.video.academy.academy.enums.Privacy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record VideoRecordDto(@NotBlank String title, String description, @NotNull String url_video, Privacy privacy ,@NotNull String thumbnail_url) {
    public VideoRecordDto {
        if (privacy == null) {
            privacy = Privacy.PRIVATE;
        }
    }
}
