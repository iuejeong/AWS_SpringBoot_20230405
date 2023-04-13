package com.web.study.service;

import com.web.study.dto.auth.LoginReqDto;
import com.web.study.dto.auth.RegisteUserReqDto;
import com.web.study.dto.response.auth.JwtTokenRespDto;

public interface AuthService {
	public void registeUser(RegisteUserReqDto registeUserReqDto);
	public void duplicatedUsername(RegisteUserReqDto registeUserReqDto);
	
	public JwtTokenRespDto login(LoginReqDto loginReqDto);
}
