package service;

import java.util.List;

public interface RetrieveNewAdress extends HttpService{

	String JUSO_URL = "https://business.juso.go.kr/addrlink/addrLinkApi.do";
	String CONFM_KEY = "devU01TX0FVVEgyMDI0MTAxODE0MDQwMzExNTE2ODc=";
	String RESULT_TYPE = "json";
	
	// �˻��� �ּ���ȸ
	List<String> getAddress(String search);
}
