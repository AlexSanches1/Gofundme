package com.app.gofundme.services;

import com.app.gofundme.controllers.dto.ProjectDTO;
import com.app.gofundme.controllers.dto.ProjectFullInfoDTO;
import com.app.gofundme.controllers.dto.ProjectShortDTO;
import com.app.gofundme.controllers.request_dto.RequestCreateProjectDTO;
import com.app.gofundme.models.Project;
import com.app.gofundme.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private ConvertService convertService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          ConvertService convertService) {
        this.projectRepository = projectRepository;
        this.convertService = convertService;
    }

    public ProjectDTO createProject(RequestCreateProjectDTO requestCreateProjectDTO) {
        Project project = convertService.convertToProject(requestCreateProjectDTO);
        Project saveProject = projectRepository.save(project);
        return convertService.convertProjectToDTO(saveProject);
    }

    public ProjectFullInfoDTO getProjectId(Long id) {
        Project project = projectRepository.getOne(id);
        return convertService.convertProjectToFullInfoDTO(project);
    }

    public List<ProjectShortDTO> listProject() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectShortDTO> projectShortDTO = new ArrayList<>();
        for (Project project:projects) {
          projectShortDTO.add(convertService.convertProjectToShotrDTO(project));
        }
        return projectShortDTO;
    }
}
