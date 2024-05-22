package com.ERR.infra.character;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ERR.common.base.BaseController;
import com.amazonaws.SdkClientException;


@RestController
@RequestMapping("/rest/character")
public class CharacterRestController extends BaseController{
	
	@Autowired
	CharacterService characterService;
	
	@GetMapping("")
	//@GetMapping("")
	public List<CharacterDto> selectList() throws Exception {
		List<CharacterDto> list = characterService.selectListWithoutPaging();
		return list;
	}
	
	@GetMapping(value="/{XtopBarSearchInputValue}")
	public CharacterDto selectOneByName(@PathVariable("XtopBarSearchInputValue") String XtopBarSearchInputValue, CharacterDto dto) throws Exception {
		CharacterDto characterDto = characterService.selectOneByName(dto);
		return characterDto;
	}
 
	@PostMapping("")
	public CharacterDto insert(CharacterDto characterDto) throws Exception {
		characterService.insert(characterDto, null);
		return characterDto;
	}
	
	@PutMapping("/{characterSeq}")
	public CharacterDto update(@PathVariable("characterSeq") String characterSeq, CharacterDto characterDto) throws Exception {
		
		System.out.println("seq: " + characterSeq);
		characterDto.setCharacterSeq(characterSeq);
		characterService.update(characterDto);
		return characterDto;
	}
	
}
