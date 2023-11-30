package com.voyagersoft.mockbot.apis.project.controller

import com.voyagersoft.mockbot.apis.project.model.dto.request.ProjectDto
import com.voyagersoft.mockbot.apis.project.service.ProjectService
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/project")
@RestController
class ProjectController(
    private val projectService: ProjectService,
) {

    /* 2023.11.30 [shiningtrue] : PROJECT 조회 */
    @PostMapping("/project")
    @Throws(Exception::class)
    fun loadApi(@RequestBody request: ProjectDto): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = projectService.loadProject()
        }
        return ResponseEntity(response, HttpStatus.OK)
    }

}