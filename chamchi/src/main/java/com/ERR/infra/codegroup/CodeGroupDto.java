package com.ERR.infra.codegroup;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class CodeGroupDto {
	private String codeGroupSeq;
	private String codeGroupName;
	private Integer codeGroupDelNy;
	private Date codeGroupRegDate;
	private Date codeGroupModDate;

	private Integer xcount;

	private String[] checkboxSeqArray = null;
	
	private MultipartFile uploadFile;
	private MultipartFile[] uploadFiles;

	public MultipartFile[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(MultipartFile[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getCodeGroupSeq() {
		return codeGroupSeq;
	}

	public void setCodeGroupSeq(String codeGroupSeq) {
		this.codeGroupSeq = codeGroupSeq;
	}

	public String getCodeGroupName() {
		return codeGroupName;
	}

	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
	}

	public Integer getCodeGroupDelNy() {
		return codeGroupDelNy;
	}

	public void setCodeGroupDelNy(Integer codeGroupDelNy) {
		this.codeGroupDelNy = codeGroupDelNy;
	}

	public Date getCodeGroupRegDate() {
		return codeGroupRegDate;
	}

	public void setCodeGroupRegDate(Date codeGroupRegDate) {
		this.codeGroupRegDate = codeGroupRegDate;
	}

	public Date getCodeGroupModDate() {
		return codeGroupModDate;
	}

	public void setCodeGroupModDate(Date codeGroupModDate) {
		this.codeGroupModDate = codeGroupModDate;
	}

	public Integer getXcount() {
		return xcount;
	}

	public void setXcount(Integer xcount) {
		this.xcount = xcount;
	}

	public String[] getCheckboxSeqArray() {
		return checkboxSeqArray;
	}

	public void setCheckboxSeqArray(String[] checkboxSeqArray) {
		this.checkboxSeqArray = checkboxSeqArray;
	}
	
	

	@Override
	public String toString() {
		return "CodeGroupDto [codeGroupSeq=" + codeGroupSeq + ", codeGroupName=" + codeGroupName + ", codeGroupDelNy="
				+ codeGroupDelNy + ", codeGroupRegDate=" + codeGroupRegDate + ", codeGroupModDate=" + codeGroupModDate
				+ ", xcount=" + xcount + "]";
	}

}
