package com.voyagersoft.mockbot.apis.test.service

import com.voyagersoft.mockbot.apis.api.repository.ApiRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TestService(
    private val apiRepository: ApiRepository,
    private val modelMapper: ModelMapper
) {

}