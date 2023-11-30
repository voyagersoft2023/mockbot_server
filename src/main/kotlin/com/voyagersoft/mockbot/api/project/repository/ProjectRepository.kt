package com.voyagersoft.mockbot.api.project.repository

import com.voyagersoft.mockbot.api.project.model.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ProjectRepository : JpaRepository<Project?, Long?>, JpaSpecificationExecutor<Project?> {

}