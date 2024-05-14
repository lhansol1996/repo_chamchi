package com.ERR.infra.characteruploaded;

public interface CharacterUploadedDao {
	// DTO 중 하나를 리턴
	public CharacterUploadedDto selectOne(CharacterUploadedDto dto);
	
	// 등록 버튼
	public int insert(CharacterUploadedDto dto);

	// 삭제 버튼
	public int uelete(CharacterUploadedDto dto);

	// 삭제 버튼(drop)
	public int delete(CharacterUploadedDto dto);


}
