package com.app.gofundme.controllers;

import com.app.gofundme.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("api/upload/image")
    public ResponseEntity uploadImage(long id, MultipartFile file) {
        String nameFile = resourceService.uploadFile(file);
        resourceService.setImageInProject(id, nameFile);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @PostMapping("api/upload/video")
    public ResponseEntity uoloadVideo(long id, MultipartFile file) {
        String nameVideo = resourceService.uploadFile(file);
        resourceService.setVideoInProject(id, nameVideo);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @GetMapping("api/resource/{id}")
    public ResponseEntity getFileById(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResourceById(id));
    }
}
