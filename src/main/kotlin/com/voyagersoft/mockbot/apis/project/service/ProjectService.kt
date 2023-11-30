package com.voyagersoft.mockbot.apis.project.service

import com.voyagersoft.mockbot.apis.project.model.dto.response.ProjectDto
import com.voyagersoft.mockbot.apis.project.model.entity.Project
import com.voyagersoft.mockbot.apis.project.repository.ProjectRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val modelMapper: ModelMapper
) {

    fun loadProject(): Any {
        var projects = projectRepository.findAll()
        return projects.stream().map { project: Project? -> modelMapper.map(project, ProjectDto::class.java) }
            .collect(Collectors.toList())
    }

}