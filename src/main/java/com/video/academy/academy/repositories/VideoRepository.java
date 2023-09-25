package com.video.academy.academy.repositories;

import com.video.academy.academy.models.VideoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoRepository  extends JpaRepository<VideoModel, UUID> {

}
