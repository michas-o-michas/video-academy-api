package com.video.academy.academy.repositories;

import com.video.academy.academy.models.PlaylistVideoId;
import com.video.academy.academy.models.PlaylistVideoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideoModel, PlaylistVideoId> {
    List<PlaylistVideoModel> findById_PlaylistModel_IdAndId_VideoModel_Id(UUID playlistId, UUID videoId);

}
