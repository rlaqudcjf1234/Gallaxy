package service;

public interface IpAddressService extends HttpService {

	String CHECK_IP_URL = "http://checkip.amazonaws.com";

	String IP_ADDRESS_URL = "http://apis.data.go.kr/B551505/whois/ip_address";
	String ENCODING_KEY = "CEk7PWkIW38uH3ZPB6gQwLYg833tyh1EMEaduza%2FP3o%2FNCM7i4rcYZQjZnl%2B%2BdDe2g6ZESTED%2F8%2Byrjkm4ypGg%3D%3D";
	String DECODING_KEY = "CEk7PWkIW38uH3ZPB6gQwLYg833tyh1EMEaduza/P3o/NCM7i4rcYZQjZnl++dDe2g6ZESTED/8+yrjkm4ypGg==";
	String ANSWER = "json";

	// aws�� ���� �ܺ� ������ ��ȸ
	String getExternalIp();

	// �ѱ����ͳ������ ������ �ּ� ��ȸ
	String getAdress(String ip);
}
