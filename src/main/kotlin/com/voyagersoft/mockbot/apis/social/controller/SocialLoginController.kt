package com.voyagersoft.mockbot.apis.social.controller

import com.voyagersoft.mockbot.apis.social.service.SocialLoginService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/login/oauth2"], produces = ["application/json"])
class SocialLoginController(val socialLoginService: SocialLoginService) {

    // 2023.10.20[shiningtrue]: oAuth (구글, 카카오, 네이버)
    @GetMapping("/code/{registrationId}")
    fun googleLogin(@RequestParam code: String, @PathVariable registrationId: String) : String{
        var result: String = socialLoginService.socialLogin(code, registrationId)
        return result
    }
}