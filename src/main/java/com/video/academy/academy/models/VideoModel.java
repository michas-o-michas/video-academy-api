package com.video.academy.academy.models;

import com.video.academy.academy.enums.Privacy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoModel implements Serializable {
    private static final long serialversionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    private String title;
    private String description;
    private String url_video;
    @NotNull
    private Privacy privacy;
    private String thumbnail_url;


}
