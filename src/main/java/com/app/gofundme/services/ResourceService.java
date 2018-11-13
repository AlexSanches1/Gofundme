package com.app.gofundme.services;

import com.app.gofundme.models.Project;
import com.app.gofundme.models.Resource;
import com.app.gofundme.repositories.ProjectRepository;
import com.app.gofundme.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ResourceService {

    private ProjectRepository projectRepository;
    private ConvertService convertService;
    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ConvertService convertService,
                           ResourceRepository resourceRepository,
                           ProjectRepository projectRepository) {
        this.convertService = convertService;
        this.resourceRepository = resourceRepository;
        this.projectRepository = projectRepository;
    }

    public String uploadFile(MultipartFile file) {
        String name = null;
        try {
             name = convertService.convertToBase64ForResource(file.getBytes());
            Resource resource = new Resource();
            resource.setNameInBase64(name);
            resourceRepository.save(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void setImageInProject(long id, String nameImage) {
        Project project = projectRepository.getOne(id);
        project.setImage("localhost:8888/api/resource/" + getResourceByName(nameImage).getId().toString());
        projectRepository.save(project);
    }

    public void setVideoInProject(long id, String nameVideo) {
        Project project = projectRepository.getOne(id);
        project.setImage("localhost:8888/api/resource" + getResourceByName(nameVideo).getId().toString());
        projectRepository.save(project);
    }

    public Resource getResourceByName(String name) {
        return resourceRepository.findByNameInBase64(name).get(0);
    }

    public byte[] getResourceById(Long id) {
        Resource resource = resourceRepository.getOne(id);
        return convertService.convertFromBase64ForResource(resource.getNameInBase64());
    }
}
