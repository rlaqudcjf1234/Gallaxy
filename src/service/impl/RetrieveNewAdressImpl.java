package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import dto.HttpDTO;
import service.RetrieveNewAdress;

public class RetrieveNewAdressImpl extends HttpServiceImpl implements RetrieveNewAdress {

	@Override
	public List<String> getAddress(String search) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try {
			setHttp(JUSO_URL);
			addParam("confmKey", CONFM_KEY);
			addParam("resultType", RESULT_TYPE);
			addParam("keyword", search);
			addProperty("Content-type", "application/json");
			HttpDTO httpDTO = getConn();

			if (httpDTO.getResponseCode() == 200) {
				JSONTokener tokener = new JSONTokener(httpDTO.getResponseText());
				JSONObject object = new JSONObject(tokener);
				JSONArray juso = object.getJSONObject("results").getJSONArray("juso");
				for (Object o : juso) {
					String jibunAddr = ((JSONObject) o).getString("jibunAddr");
					list.add(jibunAddr);
				}
			}
		} finally {
			disConn();
		}
		return list;
	}

}
