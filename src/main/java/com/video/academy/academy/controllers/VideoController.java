package com.video.academy.academy.controllers;

import com.video.academy.academy.dtos.VideoRecordDto;
import com.video.academy.academy.models.VideoModel;
import com.video.academy.academy.repositories.VideoRepository;
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
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/videos")
    public ResponseEntity<VideoModel> saveVideo(@RequestBody @Valid VideoRecordDto videoRecordDto){
        var videoModel = new VideoModel();
        BeanUtils.copyProperties(videoRecordDto, videoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(videoRepository.save(videoModel));
    }
    @GetMapping("/videos")
    public ResponseEntity<List<VideoModel>> getAllVideos(){
        return ResponseEntity.status(HttpStatus.OK).body(videoRepository.findAll());
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Object> getOneVideo(@PathVariable(value = "id") UUID id){
        Optional<VideoModel> videoO = videoRepository.findById(id);
        if(videoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }
       return ResponseEntity.status(HttpStatus.OK).body(videoO.get());
     }

    @PutMapping("videos/{id}")
    public ResponseEntity<Object> updateVideo(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid VideoRecordDto videoRecordDto){
        Optional<VideoModel> videoO = videoRepository.findById(id);
        if(videoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }
        var videoModel = videoO.get();
        BeanUtils.copyProperties(videoRecordDto, videoModel);
        return ResponseEntity.status(HttpStatus.OK).body(videoRepository.save(videoModel));

    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<Object> deleteOneVideo(@PathVariable(value = "id") UUID id){
        Optional<VideoModel> videoO = videoRepository.findById(id);
        if(videoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found.");
        }
        videoRepository.delete(videoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("video deleted successfully.");
    }

}
