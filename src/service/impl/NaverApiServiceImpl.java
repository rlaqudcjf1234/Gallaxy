package service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import dao.AddressDAO;
import dto.AddressDTO;
import dto.HttpDTO;
import service.NaverApiService;

public class NaverApiServiceImpl extends HttpServiceImpl implements NaverApiService {

	AddressDAO addressDAO = new AddressDAO();
	
	InputStream is;
	OutputStream out;

	@Override
	public List<AddressDTO> getGeocode(String address) {
		// TODO Auto-generated method stub
		List<AddressDTO> list = new ArrayList<AddressDTO>();
		try {
			setHttp(NAVER_GEOCODE_URL);
			addParam("query", address);
			addProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
			addProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
			HttpDTO httpDTO = getConn();
			if (httpDTO.getResponseCode() == 200) {
				JSONTokener tokener = new JSONTokener(httpDTO.getResponseText());
				JSONObject object = new JSONObject(tokener);
				JSONArray addresses = object.getJSONArray("addresses");
				for (Object o : addresses) {
					JSONObject addresse = (JSONObject) o;
					AddressDTO addressDTO = new AddressDTO();
					
					addressDTO.setSearchAddress(address);
					addressDTO.setRoadAddress(addresse.getString("roadAddress"));
					addressDTO.setJibunAddress(addresse.getString("jibunAddress"));
					addressDTO.setX(addresse.getString("x"));
					addressDTO.setY(addresse.getString("y"));

					list.add(addressDTO);
				}
			}
		} finally {
			disConn();
		}
		return list;
	}

	@Override
	public File getStatic(AddressDTO dto) {
		// TODO Auto-generated method stub
		File file = null;
		try {
			setHttp(NAVER_STATIC_URL);
			String center = dto.getX() + "," + dto.getY();
			String markers = "type:t|size:mid|pos:" + dto.getX() + " " + dto.getY() + "|label:" + dto.getRoadAddress();
			addParam("center", center);
			addParam("level", IMAGE_ICON_L);
			addParam("w", IMAGE_ICON_W);
			addParam("h", IMAGE_ICON_H);
			addParam("markers", markers);
			addProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
			addProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);

			is = getConnStream();

			if (is != null) {
				file = new File(TEMP_IMAGE_PATH + File.separator + dto.getRoadAddress() + ".jpg");
				file.createNewFile();

				out = new FileOutputStream(file);

				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read); // 파일 작성
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disConn();
		}
		return file;
	}

}
