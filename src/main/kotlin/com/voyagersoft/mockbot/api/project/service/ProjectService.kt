package com.voyagersoft.mockbot.api.project.service

import com.voyagersoft.mockbot.api.api.model.dto.response.ApiDto
import com.voyagersoft.mockbot.api.api.model.entity.Api
import com.voyagersoft.mockbot.api.api.repository.ApiRepository
import com.voyagersoft.mockbot.api.project.model.dto.response.ProjectDto
import com.voyagersoft.mockbot.api.project.model.entity.Project
import com.voyagersoft.mockbot.api.project.repository.ProjectRepository
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