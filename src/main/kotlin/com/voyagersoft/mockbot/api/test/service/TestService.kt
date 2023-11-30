package com.voyagersoft.mockbot.api.test.service

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
class TestService(
    private val apiRepository: ApiRepository,
    private val modelMapper: ModelMapper
) {

}