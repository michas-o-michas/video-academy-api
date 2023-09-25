package com.video.academy.academy.controllers;

import com.video.academy.academy.dtos.PlaylistVideoRecordDto;
import com.video.academy.academy.enums.Privacy;
import com.video.academy.academy.models.PlaylistModel;
import com.video.academy.academy.models.PlaylistVideoId;
import com.video.academy.academy.models.PlaylistVideoModel;
import com.video.academy.academy.models.VideoModel;
import com.video.academy.academy.repositories.PlaylistRepository;
import com.video.academy.academy.repositories.PlaylistVideoRepository;
import com.video.academy.academy.repositories.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PlaylistVideoController {

    @Autowired
    PlaylistVideoRepository playlistVideoRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    PlaylistRepository playlistRepository;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/playlists/{playlist_id}/videos")
    public ResponseEntity<Object> addVideoToPlaylist(@PathVariable(value = "playlist_id") UUID playlist_id,
                                                     @RequestBody @Valid PlaylistVideoRecordDto playlistVideoRecordDto) {

        // Verifique se a playlist e o vídeo existem antes de prosseguir
        Optional<PlaylistModel> playlist = playlistRepository.findById(playlist_id);
        UUID video_id = playlistVideoRecordDto.video_id();
        Optional<VideoModel> video = videoRepository.findById(video_id);

        if (playlist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist not found.");
        }

        if (video.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }

        // Crie uma instância de PlaylistVideoModel com a PlaylistModel, VideoModel e Privacy adequados
        PlaylistModel playlistModel = playlist.get();
        VideoModel videoModel = video.get();
        Privacy privacy = playlistVideoRecordDto.privacy();

        PlaylistVideoId playlistVideoId = new PlaylistVideoId(playlistModel, videoModel);
        PlaylistVideoModel playlistVideoModel = new PlaylistVideoModel(playlistVideoId, privacy);

        // Salve a associação na tabela associativa
        playlistVideoRepository.save(playlistVideoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Video added to playlist.");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/playlists/{playlist_id}/videos")
    public ResponseEntity<List<PlaylistVideoModel>> getAllVideoPlaylist(@PathVariable(value = "playlist_id") UUID playlist_id){
        return ResponseEntity.status(HttpStatus.OK).body( playlistVideoRepository.findAll());
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/playlists/{playlist_id}/videos/{video_id}")
    public ResponseEntity<Object> removeVideoFromPlaylist(
            @PathVariable("playlist_id") UUID playlistId,
            @PathVariable("video_id") UUID videoId
    ) {
        var playlist = new PlaylistModel();
        var video = new VideoModel();
        var id = new PlaylistVideoId(playlist, video);

        var playlistVideoO = playlistVideoRepository.findById_PlaylistModel_IdAndId_VideoModel_Id(playlistId, videoId);

        return ResponseEntity.status(HttpStatus.OK).body(playlistVideoO);
    }
}
