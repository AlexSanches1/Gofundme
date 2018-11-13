package com.app.gofundme.services;

import com.app.gofundme.controllers.dto.*;
import com.app.gofundme.controllers.request_dto.RequestCreateProjectDTO;
import com.app.gofundme.models.History;
import com.app.gofundme.models.Project;
import com.app.gofundme.models.User;
import com.app.gofundme.repositories.ResourceRepository;
import com.app.gofundme.utils.DateUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertService {

    private ResourceRepository resourceRepository;

    @Autowired
    public ConvertService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public TokenDTO convertToBase64ForToken(Long id) {
        TokenDTO dto = new TokenDTO();
        String idString = id.toString();
        byte[] token = Base64.encodeBase64(idString.getBytes());
        dto.setToken(new String(token));
        return dto;
    }

    public Long convertFromBase64ForToken(String token) {
        byte[] bytes = Base64.decodeBase64(token.getBytes());
        String idString = new String(bytes);
        Long id = Long.valueOf(idString);
        return id;
    }

    public String convertToBase64ForResource(byte[] bytes) {
        byte[] base64 = Base64.encodeBase64(bytes);
        return new String(base64);
    }

    public byte[] convertFromBase64ForResource(String name) {
        byte[] bytes = name.getBytes();
        return Base64.decodeBase64(bytes);
    }

    public UserInfoDTO userConvertToDTO(User user) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setId(user.getId());
        dto.setSecondName(user.getSecondName());
        return dto;
    }

    public ProjectDTO convertProjectToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setPathToImage(project.getImage());
        dto.setShortDescription(project.getShortDescription());
        dto.setPathToVideo(project.getVideo());
        dto.setDaysBetween(DateUtil.daysBetween(project.getStartDate(), project.getEndDate()));
        return dto;
    }

    public ProjectFullInfoDTO convertProjectToFullInfoDTO(Project project) {
        ProjectFullInfoDTO projectFullInfoDTO = new ProjectFullInfoDTO();
        projectFullInfoDTO.setId(project.getId());
        projectFullInfoDTO.setImage(resourceRepository.findByNameInBase64(project.getImage()).get(0).getId());
        projectFullInfoDTO.setVideo(resourceRepository.findByNameInBase64(project.getVideo()).get(0).getId());
        if (project.getParticipantsCount() == null) {
            projectFullInfoDTO.setParticipantsCount(0);
        } else {
            projectFullInfoDTO.setParticipantsCount(project.getParticipantsCount());
        }
        if (project.getCurrentSum() == null) {
            projectFullInfoDTO.setCurrentSum(0.0);
        } else {
            projectFullInfoDTO.setCurrentSum(project.getCurrentSum());
        }
        projectFullInfoDTO.setDaysBetween(
                DateUtil.daysBetween(project.getStartDate(), project.getEndDate()));
        projectFullInfoDTO.setGoal(project.getGoal());
        projectFullInfoDTO.setTitle(project.getTitle());
        projectFullInfoDTO.setShortDescription(project.getShortDescription());
        projectFullInfoDTO.setHistory(convertHistoryToDTO(project.getHistory()));
//TODO   projectFullInfoDTO.setUsers(project.getUsers());
        return projectFullInfoDTO;
    }

    public Project convertToProject(RequestCreateProjectDTO requestcreateProjectDTO) {
        Project project = new Project();
        project.setEndDate(requestcreateProjectDTO.getEndDate());
        project.setStartDate(requestcreateProjectDTO.getStartDate());
        project.setShortDescription(requestcreateProjectDTO.getShortDescription());
        project.setTitle(requestcreateProjectDTO.getTitle());
        return project;
    }

    public HistoryDTO convertHistoryToDTO(History history) {
        HistoryDTO dto = new HistoryDTO();
        dto.setId(history.getId());
        dto.setText(history.getText());
        return dto;
    }

    public ProjectShortDTO convertProjectToShotrDTO(Project project){
        ProjectShortDTO dto = new ProjectShortDTO();
        dto.setCurrentSum(project.getCurrentSum());
        dto.setDaysBetween(
                DateUtil.daysBetween(project.getStartDate(), project.getEndDate()));
        dto.setGoal(project.getGoal());
        dto.setId(project.getId());
        dto.setImage(project.getImage());
        dto.setTitle(project.getTitle());
        return dto;
    }
}
