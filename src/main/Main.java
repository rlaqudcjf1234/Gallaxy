package main;

import java.util.List;

import dto.AddressDTO;
import service.IpAddressService;
import service.NaverApiService;
import service.RetrieveNewAdress;
import service.impl.IpAddressServiceImpl;
import service.impl.NaverApiServiceImpl;
import service.impl.RetrieveNewAdressImpl;

public class Main {

	public static void main(String[] args) {

		/*
		ExampleService example = new ExampleServiceImpl();
		System.out.println(example.getInit());
		*/
		/*
		new BoardWriteFrame().initGUI();
		*/
		IpAddressService ias = new IpAddressServiceImpl();
		String address = ias.getAdress(ias.getExternalIp());
		
		RetrieveNewAdress rna = new RetrieveNewAdressImpl();
		List<String> addrs = rna.getAddress(address);
		
		NaverApiService nas = new NaverApiServiceImpl();
		List<AddressDTO> list =  nas.getGeocode(addrs.get(0));
		if(list != null && list.size() > 0) {
			nas.getStatic(list.get(0));
		}
		
	}
}
