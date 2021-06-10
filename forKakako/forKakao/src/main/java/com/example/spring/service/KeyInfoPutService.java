package com.example.spring.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.dto.RegisterDto;
import com.example.spring.model.KeyInfo;
import com.example.spring.model.KeyInfoRepository;;

@Service
public class KeyInfoPutService{

	@Autowired
	private KeyInfoRepository keyInfoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public String putKeyInfo(RegisterDto registerDto) throws Exception {

		KeyInfo keyInfo = modelMapper.map(registerDto, KeyInfo.class);
		String message = null;

		try {
			// key 정보 중복 확인
			if(keyInfoRepository.existsById(keyInfo.getKeyCode())) {

				message = "이미 등록되어 있는 Key 정보 입니다. Key를 변경해주세요";
				return message;
				
			} else {
				// 키정보 등록
				keyInfoRepository.save(keyInfo);
			}

			return "OK";
		} catch (Exception e) {
			throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 한 번 시도해주십시오.");
		}
	}
	
}
