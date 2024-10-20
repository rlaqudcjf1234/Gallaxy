package service;

import java.util.Map;

public interface RetrieveNewAdress extends HttpService{

	String JUSO_URL = "https://business.juso.go.kr/addrlink/addrLinkApi.do";
	String CONFM_KEY = "devU01TX0FVVEgyMDI0MTAxODE0MDQwMzExNTE2ODc=";
	int COUNT_PER_PAGE = 10;
	String RESULT_TYPE = "json";
	
	// 검색어 주소조회
	Map<String, Object> getAddress(String keyword, int currentPage);
}
