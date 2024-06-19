package com.ERR.infra.codegroup;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ERR.common.base.BaseController;
import com.ERR.common.util.UtilDateTime;
import com.ERR.common.util.UtilSetSearch;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CodeGroupController extends BaseController {

	private String XdmCdgCommomPath = "xdm/cdg/";

	@Autowired
	CodeGroupService service;
//	CodeGroupService codeGroupService;

	@RequestMapping(value = "/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);
		return XdmCdgCommomPath + "codeGroupXdmList";
	}

	@RequestMapping(value = "/codeGroupXdmLita")
	public String codeGroupXdmLita(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);

		vo.setParamsPaging(service.selectCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", service.selectListWithPaging(vo));
		}

		return XdmCdgCommomPath + "codeGroupXdmLita";
	}

//	@RequestMapping(value="/codeGroupView")
//	public String codeGroupView(CodeGroupDto dto, Model model) throws Exception{
//		/*
//		 * 넘겨 받은 변수 2개 확인하는 방법
//		 * 1번째 방법 : 받는 객체를 dto로 받는 방법
//		 * -> dto의 변수들 이름과 html에서 받는 이름이 같으면 자동으로 매핑됨(대소문자도 같아야함)
//		 */
//		System.out.println(dto.toString());
//		
//		model.addAttribute("item",service.selectOne(dto));
//		
//		return "codeGroupView";
//	}

	@RequestMapping(value = "/codeGroupXdmView")
	public String codeGroupXdmView(CodeGroupDto dto, Model model) throws Exception {

		model.addAttribute("item", service.selectOne(dto));

		return XdmCdgCommomPath + "codeGroupXdmView";
	}

	@RequestMapping(value = "/codeGroupXdmUpdateForm")
	public String codeGroupXdmUpdateForm(Model model, CodeGroupDto dto) throws Exception {

		model.addAttribute("item", service.selectOne(dto));
		return XdmCdgCommomPath + "codeGroupXdmUpdateForm";
	}

	@RequestMapping(value = "/codeGroupXdmInsertForm")
	public String codeGroupXdmInsertForm(Model model) throws Exception {
		return XdmCdgCommomPath + "codeGroupXdmInsertForm";
	}

	@RequestMapping(value = "/codeGroupXdmInsert")
	public String codeGroupXdmInsert(CodeGroupDto dto, Model model) throws Exception {
		// System.out.println(dto.toString());

		service.insert(dto);
		return "redirect:/codeGroupXdmList";
	}

	@RequestMapping(value = "/codeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto dto, Model model) throws Exception {

		System.out.println(dto.toString() + "==========업데이트 전 ==========");
		service.update(dto);
		return "redirect:/codeGroupXdmList";
	}

	@RequestMapping(value = "/codeGroupXdmUelete")
	public String codeGroupXdmUelete(CodeGroupDto dto, Model model) throws Exception {

		service.uelete(dto);
		System.out.println(dto.toString() + "--------------삭제후-------------------------");
		return "redirect:/codeGroupXdmList";
	}

	@RequestMapping(value = "/codeGroupXdmDelete")
	public String codeGroupXdmDelete(CodeGroupDto dto, Model model) throws Exception {

		service.delete(dto);
		return "redirect:/codeGroupXdmList";
	}

	@RequestMapping(value = "/codeGroupXdmMultiUelete")
	public String codeGroupXdmMultiUelete(CodeGroupDto dto) throws Exception {
		String[] checkboxSeqArray = dto.getCheckboxSeqArray();
		for (String checkboxSeq : checkboxSeqArray) {
			dto.setCodeGroupSeq(checkboxSeq);
			service.uelete(dto);
		}

		return "redirect:/codeGroupXdmList";
	}

	@RequestMapping("excelDownload")
	public void excelDownload(CodeGroupVo vo, HttpServletResponse httpServletResponse) throws Exception {

		UtilSetSearch.setSearch(vo);
		vo.setParamsPaging(service.selectCount(vo));

		if (vo.getTotalRows() > 0) {
			List<CodeGroupDto> list = service.selectListWithPaging(vo);

//			Workbook workbook = new HSSFWorkbook(); // xls용
	      Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("첫번째 시트");
			CellStyle cellStyle = workbook.createCellStyle();
			Row row = null;
			Cell cell = null;
			int rowNum = 0;

//	      각 열의 너비 설정
			sheet.setColumnWidth(0, 2100);
			sheet.setColumnWidth(1, 3100);

//	      헤더
			String[] tableHeader = { "코드그룹 Seq", "코드그룹 이름", "사용여부", "등록일", "수정일" };

			row = sheet.createRow(rowNum++);
			for (int i = 0; i < tableHeader.length; i++) {
				cell = row.createCell(i);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(tableHeader[i]);
			}

//	      본문
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(rowNum++);

//	          String 타입: null 전달되어도 ok
//	          int, date 타입: null 시 오류 발생하므로 null 체크
//	          String 타입이지만 정수형 데이터가 전체인 seq의 경우 캐스팅

				cell = row.createCell(0);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(Integer.parseInt(list.get(i).getCodeGroupSeq()));

				cell = row.createCell(1);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(list.get(i).getCodeGroupName());

				cell = row.createCell(2);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				if (list.get(i).getCodeGroupDelNy() != null)
					cell.setCellValue(list.get(i).getCodeGroupDelNy() == 0 ? "N" : "Y");

				cell = row.createCell(3);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				if (list.get(i).getCodeGroupRegDate() != null)
					cell.setCellValue(UtilDateTime.dateTimeToString(list.get(i).getCodeGroupRegDate()));

				cell = row.createCell(4);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				if (list.get(i).getCodeGroupModDate() != null)
					cell.setCellValue(UtilDateTime.dateTimeToString(list.get(i).getCodeGroupModDate()));
			}

			httpServletResponse.setContentType("ms-vnd/excel");
//	      httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xls"); // xls용
			httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

			workbook.write(httpServletResponse.getOutputStream());
			workbook.close();
		}

	}
}
