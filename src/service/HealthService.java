package service;

import dto.HealthDTO;

public interface HealthService {

	// �ｺ�ɾ� ���
	public int insertHealth(HealthDTO dto);

	// �ｺ�ɾ� ����� ��ȸ
	public HealthDTO selectHealthSt(HealthDTO dto);

	// �ｺ�ɾ� ��ȸ
	public HealthDTO selectHealth(HealthDTO dto);
	
	
	
	
}
