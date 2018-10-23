package com.app.gofundme.controllers;

import com.app.gofundme.controllers.request_dto.RequestCreateProjectDTO;
import com.app.gofundme.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("api/admin/create/project")
    public ResponseEntity createProjet(@RequestBody RequestCreateProjectDTO requestCreateProjectDTO) {
        return ResponseEntity.ok(projectService.createProject(requestCreateProjectDTO));
    }

    @GetMapping("api/project/getId/{id}")
    public ResponseEntity getProjectId(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectId(id));
    }

    @GetMapping("api/project/list/")
    public ResponseEntity getProjectList() {
        return ResponseEntity.ok(projectService.listProject());
    }
}
