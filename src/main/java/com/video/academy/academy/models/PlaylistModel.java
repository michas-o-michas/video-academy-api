package com.video.academy.academy.models;

import com.video.academy.academy.enums.Privacy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "playlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistModel  implements Serializable {
    private static final long serialversionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    private String title;
    private String description;
    private Privacy privacy;
    private String thumbnail_url;

}
