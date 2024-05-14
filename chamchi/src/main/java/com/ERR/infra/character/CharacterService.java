package com.ERR.infra.character;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ERR.common.util.UtilDateTime;
import com.ERR.infra.characteruploaded.CharacterUploadedDao;
import com.ERR.infra.characteruploaded.CharacterUploadedDto;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class CharacterService {
	@Autowired
	CharacterDao characterDao;
	@Autowired
	CharacterUploadedDao characterUploadedDao;
	@Autowired
	AmazonS3Client amazonS3Client;

	@Value("${cloud_aws_bucket}")
	private String bucket;

	public CharacterDto selectOne(CharacterDto dto) {
		return characterDao.selectOne(dto);
	}

	public CharacterDto selectOneByName(CharacterDto dto) {
		return characterDao.selectOneByName(dto);
	}

	// 등록버튼
	public int insert(CharacterDto characterDto, CharacterUploadedDto characterUploadedDto,
			MultipartFile[] multipartFiles, String tableName, int type, String pSeq,
			CharacterDao characterDao, CharacterUploadedDao characterUploadedDao, AmazonS3Client amazonS3Client)
			throws Exception, SdkClientException, IOException {

		characterDao.insert(characterDto);

		for (int i = 0; i < multipartFiles.length; i++) {

			if (!multipartFiles[i].isEmpty()) {

				String className = characterDto.getClass().getSimpleName().toString().toLowerCase();
				String fileName = multipartFiles[i].getOriginalFilename();
				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				String uuid = UUID.randomUUID().toString();
				String uuidFileName = uuid + "." + ext;
				String pathModule = className;
				String nowString = UtilDateTime.nowString();
				String pathDate = nowString.substring(0, 4) + "/" + nowString.substring(5, 7) + "/"
						+ nowString.substring(8, 10);
				String path = pathModule + "/" + type + "/" + pathDate + "/";
//						String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";

				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(multipartFiles[i].getSize());
				metadata.setContentType(multipartFiles[i].getContentType());

				amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);

				String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();

				characterUploadedDto.setCharacterUploadedPath(objectUrl);
				characterUploadedDto.setCharacterUploadedOriginalName(fileName);
				characterUploadedDto.setCharacterUploadedUuidName(uuidFileName);
				characterUploadedDto.setCharacterUploadedExt(ext);
				characterUploadedDto.setCharacterUploadedSize(Integer.valueOf((int) multipartFiles[i].getSize()));

				characterUploadedDto.setCharacterUploadedType(type);
				characterUploadedDto.setCharacterUploadedPseq(characterDto.getCharacterSeq());

				characterUploadedDao.insert(characterUploadedDto);
			}
		}

		return 1;

	}

	// 수정버튼
	public int update(CharacterDto dto) {
		return characterDao.update(dto);
	}

	// 삭제버튼(uelete)
	public int uelete(CharacterDto dto) {
		return characterDao.uelete(dto);
	}

	// 삭제버튼(delete)
	public int delete(CharacterDto dto) {
		return characterDao.delete(dto);
	}

	// 페이징없는 코드그룹 리스트
	public List<CharacterDto> selectListWithoutPaging() {
		return characterDao.selectListWithoutPaging();
	}

	// 페이징 처리 리스트
	public List<CharacterDto> selectListWithPaging(CharacterVo vo) {
		return characterDao.selectListWithPaging(vo);
	}

	// 개수 리턴
	public Integer selectCount(CharacterVo vo) {
		return characterDao.selectCount(vo);
	}

}
