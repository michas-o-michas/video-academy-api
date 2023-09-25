package com.video.academy.academy.controllers;

import com.video.academy.academy.dtos.PlaylistRecordDto;
import com.video.academy.academy.models.PlaylistModel;
import com.video.academy.academy.repositories.PlaylistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PlaylistController {

    @Autowired
    PlaylistRepository playlistRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/playlists")
    public ResponseEntity<PlaylistModel> savePlaylist(@RequestBody @Valid PlaylistRecordDto playlistRecordDto){
        var playlistModel = new PlaylistModel();
        BeanUtils.copyProperties(playlistRecordDto, playlistModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistRepository.save(playlistModel));
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/playlists")
    public ResponseEntity<List<PlaylistModel>> getAllPlaylist(){
        return ResponseEntity.status(HttpStatus.OK).body(playlistRepository.findAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/playlists/{playlist_id}")
    public ResponseEntity<Object> updatePlaylist(@PathVariable UUID playlist_id){
        var playlistO = playlistRepository.findById(playlist_id);
        if (playlistO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playlist not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(playlistO.get());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/playlists/{playlist_id}")
    public ResponseEntity<Object> updatePlaylist(@PathVariable UUID playlist_id, @RequestBody @Valid PlaylistRecordDto playlistRecordDto){
        Optional<PlaylistModel> playlistO = playlistRepository.findById(playlist_id);

        if(playlistO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playlist not found.");
        }

        var playlistModel = playlistO.get();
        BeanUtils.copyProperties(playlistRecordDto, playlistModel);
        return ResponseEntity.status(HttpStatus.OK).body(playlistRepository.save(playlistModel));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/playlists/{playlist_id}")
    public ResponseEntity<String> deletePlaylistById(@PathVariable UUID playlist_id){
        Optional<PlaylistModel> playlistO = playlistRepository.findById(playlist_id);

        if(playlistO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("playlist not found.");
        }

        playlistRepository.delete(playlistO.get());
        return ResponseEntity.status(HttpStatus.OK).body("playlist deleted successfully.");
    }

}
