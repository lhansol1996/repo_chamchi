package com.ERR.infra.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ERR.common.base.BaseController;
import com.ERR.common.constants.Constants;
import com.ERR.common.util.UtilSetSearch;
import com.ERR.infra.characteruploaded.CharacterUploadedDao;
import com.ERR.infra.characteruploaded.CharacterUploadedDto;
import com.amazonaws.services.s3.AmazonS3Client;

@Controller
public class CharacterController extends BaseController {

	private String XdmCharacterCommonPath = "xdm/character/";
	private String UsrCharacterCommonPath = "usr/character/";

	@Autowired
	CharacterService characterService;

	@RequestMapping(value = "/characterXdmList")
	public String characterXdmList(@ModelAttribute("vo") CharacterVo vo, Model model) throws Exception {

		UtilSetSearch.setSearch(vo);
		vo.setParamsPaging(characterService.selectCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", characterService.selectListWithPaging(vo));
		}

		return XdmCharacterCommonPath + "characterXdmList";
	}

	@RequestMapping(value = "/characterXdmView")
	public String characterXdmView(CharacterDto dto, Model model) throws Exception {

		model.addAttribute("item", characterService.selectOne(dto));
		return XdmCharacterCommonPath + "characterXdmView";
	}

	@RequestMapping(value = "/characterXdmUpdateForm")
	public String characterXdmUpdateForm(Model model, CharacterDto dto) throws Exception {

		model.addAttribute("item", characterService.selectOne(dto));
		return XdmCharacterCommonPath + "characterXdmUpdateForm";
	}

	@RequestMapping(value = "/characterXdmInsertForm")
	public String characterXdmInsertForm(Model model) throws Exception {

		return XdmCharacterCommonPath + "characterXdmInsertForm";
	}

	@RequestMapping(value = "/characterXdmInsert")
	public String characterXdmInsert(CharacterDto characterDto, CharacterUploadedDto characterUploadedDto) throws Exception {

		characterService.insert(characterDto, characterUploadedDto);
		return "redirect:/characterXdmList";
	}

	@RequestMapping(value = "/characterXdmUpdt")
	public String characterXdmUpdt(CharacterDto dto, Model model) throws Exception {

		characterService.update(dto);
		return "redirect:/characterXdmList";
	}

	@RequestMapping(value = "/characterXdmUelete")
	public String characterXdmUelete(CharacterDto dto, Model model) throws Exception {

		characterService.uelete(dto);
		return "redirect:/characterXdmList";
	}

	@RequestMapping(value = "/characterXdmDelete")
	public String characterXdmDelete(CharacterDto dto, Model model) throws Exception {

		characterService.delete(dto);
		return "redirect:/characterXdmList";
	}

	@RequestMapping(value = "/characterDetail")
	public String characterDetail(Model model, CharacterDto dto) throws Exception {

		CharacterDto character = characterService.selectOneByName(dto);
		System.out.println(character.toString());
		if (character != null) {
			if (Constants.CHARACTER_NAMES.contains(character.getCharacterName())) {
				model.addAttribute("item", character);
				return UsrCharacterCommonPath + "character-detail";
			}
		}
		return "usr/pageNotFound";
	}

	@RequestMapping(value = "/characterXdmMultiUelete")
	public String characterXdmMultiUelete(CharacterDto dto) throws Exception {
		String[] checkboxSeqArray = dto.getCheckboxSeqArray();
		for (String checkboxSeq : checkboxSeqArray) {
			dto.setCharacterSeq(checkboxSeq);
			characterService.uelete(dto);
		}

		return "redirect:/characterXdmList";
	}

}
