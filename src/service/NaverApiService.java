package service;

import java.io.File;
import java.util.List;

import dto.AddressDTO;

public interface NaverApiService extends HttpService {

	String CLIENT_ID = "7302a5367e";
	String CLIENT_SECRET = "wkqwjF0ZfL5IJahA8krUlbvz09iTyUIgRafIU9Jf";
	
	String NAVER_GEOCODE_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
	String NAVER_STATIC_URL = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster";
	
	int IMAGE_ICON_L = 16;
	int IMAGE_ICON_W = 430;
	int IMAGE_ICON_H = 270;
	
	String TEMP_IMAGE_PATH = "temp";
	
	List<AddressDTO> getGeocode(String addr);
	File getStatic(AddressDTO dto);
	
}
