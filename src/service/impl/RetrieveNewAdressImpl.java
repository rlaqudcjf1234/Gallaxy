package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import dto.HttpDTO;
import service.RetrieveNewAdress;

public class RetrieveNewAdressImpl extends HttpServiceImpl implements RetrieveNewAdress {

	@Override
	public Map<String, Object> getAddress(String search, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String errorCode = "0";
		String errorMessage = "";
		int totalCount = 0;
		int totalPage = 0;
		List<String> list = new ArrayList<String>();
		try {
			setHttp(JUSO_URL);
			addParam("confmKey", CONFM_KEY);
			addParam("resultType", RESULT_TYPE);
			addParam("keyword", search);
			addParam("countPerPage", 10);
			addParam("currentPage", COUNT_PER_PAGE);
			addProperty("Content-type", "application/json");
			HttpDTO httpDTO = getConn();

			if (httpDTO.getResponseCode() == 200) {
				JSONTokener tokener = new JSONTokener(httpDTO.getResponseText());
				JSONObject object = new JSONObject(tokener);

				errorCode = object.getJSONObject("results").getJSONObject("common").getString("errorCode");
				errorMessage = object.getJSONObject("results").getJSONObject("common").getString("errorMessage");
				
				// 연계 결과 정상
				if(errorCode.equals("0")) {
					totalCount = object.getJSONObject("results").getJSONObject("common").getInt("totalCount");
					JSONArray juso = object.getJSONObject("results").getJSONArray("juso");
					for (Object o : juso) {
						String jibunAddr = ((JSONObject) o).getString("jibunAddr");
						list.add(jibunAddr);
					}
				}
			}
		} finally {
			disConn();
		}
		
		if(totalCount > 0) {
			totalPage = (int) Math.ceil((double) totalCount / COUNT_PER_PAGE);
		}

		map.put("errorCode", errorCode);
		map.put("errorMessage", errorMessage);
		map.put("totalPage", totalPage);
		map.put("list", list);
		
		return map;
	}

}
