package com.video.academy.academy.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistVideoId  implements Serializable {
    @ManyToOne
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    private PlaylistModel playlistModel;

    @ManyToOne
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private VideoModel videoModel;


}
