package service;

import dto.HealthDTO;

public interface HealthService {

	// 헬스케어 등록
	public int insertHealth(HealthDTO dto);

	// 헬스케어 월통계 조회
	public HealthDTO selectHealthSt(HealthDTO dto);

	// 헬스케어 조회
	public HealthDTO selectHealth(HealthDTO dto);
	
	
	
	
}
