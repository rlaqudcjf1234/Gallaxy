package service.impl;

import org.json.JSONObject;
import org.json.JSONTokener;

import dto.HttpDTO;
import service.IpAddressService;

public class IpAddressServiceImpl extends HttpServiceImpl implements IpAddressService {

	@Override
	public String getExternalIp() {
		// TODO Auto-generated method stub
		setHttp(CHECK_IP_URL);
		HttpDTO httpDTO = getConn();
		if (httpDTO.getResponseCode() == 200) {
			return httpDTO.getResponseText();
		}
		return null;
	}

	@Override
	public String getAdress(String ip) {
		// TODO Auto-generated method stub
		setHttp(IP_ADDRESS_URL);
		addParam("serviceKey", DECODING_KEY);
		addParam("query", ip);
		addParam("answer", ANSWER);
		addProperty("Content-type", "application/json");
		HttpDTO httpDTO = getConn();

		if (httpDTO.getResponseCode() == 200) {
			JSONTokener tokener = new JSONTokener(httpDTO.getResponseText());
			JSONObject object = new JSONObject(tokener);
			JSONObject response = object.getJSONObject("response");
			String resultCode = response.getJSONObject("result").getString("result_code");
			if (resultCode.equals("10000")) {
				return response.getJSONObject("whois").getJSONObject("korean").getJSONObject("user")
						.getJSONObject("netinfo").getString("addr");
			}
		}
		return null;
	}
}
