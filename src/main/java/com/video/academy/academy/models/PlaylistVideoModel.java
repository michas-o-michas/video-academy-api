package com.video.academy.academy.models;

import com.video.academy.academy.enums.Privacy;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "videos_playlists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistVideoModel implements Serializable {

    @EmbeddedId
    private PlaylistVideoId id;
    @NotNull
    private Privacy privacy;

}
