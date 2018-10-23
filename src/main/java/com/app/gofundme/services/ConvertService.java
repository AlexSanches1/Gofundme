package com.app.gofundme.services;

import com.app.gofundme.controllers.dto.*;
import com.app.gofundme.controllers.request_dto.RequestCreateProjectDTO;
import com.app.gofundme.models.History;
import com.app.gofundme.models.Project;
import com.app.gofundme.models.User;
import com.app.gofundme.utils.DateUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvertService {

    public TokenDTO convertToBase64(Long id) {
        TokenDTO dto = new TokenDTO();
        String idString = id.toString();
        byte[] token = Base64.encodeBase64(idString.getBytes());
        dto.setToken(new String(token));
        return dto;
    }

    public Long convertFromBase64(String token) {
        byte[] bytes = Base64.decodeBase64(token.getBytes());
        String idString = new String(bytes);
        Long id = Long.valueOf(idString);
        return id;
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
        dto.setTitle(project.getTitle());
        dto.setImage(project.getImage());
        dto.setShortDescription(project.getShortDescription());
        dto.setVideo(project.getVideo());
        dto.setDaysBetween(DateUtil.daysBetween(project.getStartDate(), project.getEndDate()));
        return dto;
    }

    public ProjectFullInfoDTO convertProjectToFullInfoDTO(Project project) {
        ProjectFullInfoDTO projectFullInfoDTO = new ProjectFullInfoDTO();
        projectFullInfoDTO.setId(project.getId());
        projectFullInfoDTO.setImage(project.getImage());
        projectFullInfoDTO.setVideo(project.getVideo());
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
//TODO   projectFullInfoDTO.setContributors(project.getContributors());
        return projectFullInfoDTO;
    }

    public Project convertToProject(RequestCreateProjectDTO requestcreateProjectDTO) {
        Project project = new Project();
        project.setEndDate(requestcreateProjectDTO.getEndDate());
        project.setStartDate(requestcreateProjectDTO.getStartDate());
        project.setShortDescription(requestcreateProjectDTO.getShortDescription());
        project.setVideo(requestcreateProjectDTO.getVideo());
        project.setImage(requestcreateProjectDTO.getImage());
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
