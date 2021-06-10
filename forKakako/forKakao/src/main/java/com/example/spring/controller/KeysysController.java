package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.dto.ReponseDto;
import com.example.spring.dto.RegisterDto;
import com.example.spring.service.CallKeyGeneratorService;
import com.example.spring.service.KeyInfoPutService;

@RestController
public class KeysysController {
	
	@Autowired
	private KeyInfoPutService keyInfoCheckService;
	@Autowired
	private CallKeyGeneratorService callKeyGeneratorService;

	/*
	 * Key정보 취득
	 */
	@RequestMapping(value = "/api/key/register", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String setKeyRegister(@RequestParam("key") String keyCode, @RequestParam("description") String description, 
			@RequestParam("type") String type, @RequestParam("generator") String generator, @RequestParam("min_length") long min_length) {

		// Key 정보 등록
		try {
			// 입력 파라미터
			RegisterDto registerDto = new RegisterDto(keyCode, description, type, generator, min_length, null, null);

			if (!keyInfoCheckService.putKeyInfo(registerDto).equals("OK")) {
				return keyInfoCheckService.putKeyInfo(registerDto); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

		return "200 OK";
	}


	/*
	 * Key 발급 처리
	 */
	@RequestMapping(value = "/api/key/{keyInfo}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ReponseDto setPolicyKey(@PathVariable ("keyInfo") String keyInfo) {

		// 입력 파라미터
		RegisterDto registerDto = new RegisterDto();
		registerDto.setKeyCode(keyInfo);

		try {
			String rtValue = callKeyGeneratorService.callKeyGenerator(registerDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

		

	}

}