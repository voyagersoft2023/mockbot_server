package com.voyagersoft.mockbot.api.api.service

import com.voyagersoft.mockbot.api.api.model.dto.response.ApiDto
import com.voyagersoft.mockbot.api.api.model.dto.response.ApiRequestDto
import com.voyagersoft.mockbot.api.api.model.dto.response.ApiResponseDto
import com.voyagersoft.mockbot.api.api.model.entity.Api
import com.voyagersoft.mockbot.api.api.model.entity.ApiRequest
import com.voyagersoft.mockbot.api.api.model.entity.ApiResponse
import com.voyagersoft.mockbot.api.api.repository.ApiRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ApiService(
    private val apiRepository: ApiRepository,
    private val modelMapper: ModelMapper
) {

    fun loadApi(projectId: Long): Any {
        var apis = apiRepository.findByProjectId(projectId)
        return apis.stream().map { api: Api? -> modelMapper.map(api, ApiDto::class.java) }
            .collect(Collectors.toList())
    }
    fun loadApiRequest(): Any {
        var apiRequests = apiRepository.loadApiRequest()
        return apiRequests.stream().map { apiRequest: ApiRequest? -> modelMapper.map(apiRequest, ApiRequestDto::class.java) }
            .collect(Collectors.toList())
    }

    fun loadApiResponse(): Any {
        var apiResponses = apiRepository.loadApiResponse()
        return apiResponses.stream().map { apiResponse: ApiResponse? -> modelMapper.map(apiResponse, ApiResponseDto::class.java) }
            .collect(Collectors.toList())
    }

}