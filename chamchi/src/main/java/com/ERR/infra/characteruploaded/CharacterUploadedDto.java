package com.ERR.infra.characteruploaded;

import java.util.Date;

public class CharacterUploadedDto {
	private String characterUploadedSeq;
	private Integer characterUploadedDefaultNy;
	private String characterUploadedPath;
	private String characterUploadedOriginalName;
	private String characterUploadedUuidName;
	private String characterUploadedExt;
	private Integer characterUploadedSize;
	private Integer characterUploadedDelNy;
	private String characterUploadedPseq;
	private Date characterUploadedRegDate;

	public String getCharacterUploadedSeq() {
		return characterUploadedSeq;
	}

	public void setCharacterUploadedSeq(String characterUploadedSeq) {
		this.characterUploadedSeq = characterUploadedSeq;
	}

	public Integer getCharacterUploadedDefaultNy() {
		return characterUploadedDefaultNy;
	}

	public void setCharacterUploadedDefaultNy(Integer characterUploadedDefaultNy) {
		this.characterUploadedDefaultNy = characterUploadedDefaultNy;
	}

	public String getCharacterUploadedPath() {
		return characterUploadedPath;
	}

	public void setCharacterUploadedPath(String characterUploadedPath) {
		this.characterUploadedPath = characterUploadedPath;
	}

	public String getCharacterUploadedOriginalName() {
		return characterUploadedOriginalName;
	}

	public void setCharacterUploadedOriginalName(String characterUploadedOriginalName) {
		this.characterUploadedOriginalName = characterUploadedOriginalName;
	}

	public String getCharacterUploadedUuidName() {
		return characterUploadedUuidName;
	}

	public void setCharacterUploadedUuidName(String characterUploadedUuidName) {
		this.characterUploadedUuidName = characterUploadedUuidName;
	}

	public String getCharacterUploadedExt() {
		return characterUploadedExt;
	}

	public void setCharacterUploadedExt(String characterUploadedExt) {
		this.characterUploadedExt = characterUploadedExt;
	}

	public Integer getCharacterUploadedSize() {
		return characterUploadedSize;
	}

	public void setCharacterUploadedSize(Integer characterUploadedSize) {
		this.characterUploadedSize = characterUploadedSize;
	}

	public Integer getCharacterUploadedDelNy() {
		return characterUploadedDelNy;
	}

	public void setCharacterUploadedDelNy(Integer characterUploadedDelNy) {
		this.characterUploadedDelNy = characterUploadedDelNy;
	}

	public String getCharacterUploadedPseq() {
		return characterUploadedPseq;
	}

	public void setCharacterUploadedPseq(String characterUploadedPseq) {
		this.characterUploadedPseq = characterUploadedPseq;
	}

	public Date getCharacterUploadedRegDate() {
		return characterUploadedRegDate;
	}

	public void setCharacterUploadedRegDate(Date characterUploadedRegDate) {
		this.characterUploadedRegDate = characterUploadedRegDate;
	}

	@Override
	public String toString() {
		return "CharacterUploadedDto [characterUploadedSeq=" + characterUploadedSeq + ", characterUploadedDefaultNy="
				+ characterUploadedDefaultNy + ", characterUploadedPath=" + characterUploadedPath
				+ ", characterUploadedOriginalName=" + characterUploadedOriginalName + ", characterUploadedUuidName="
				+ characterUploadedUuidName + ", characterUploadedExt=" + characterUploadedExt
				+ ", characterUploadedSize=" + characterUploadedSize + ", characterUploadedDelNy="
				+ characterUploadedDelNy + ", characterUploadedPseq=" + characterUploadedPseq
				+ ", characterUploadedRegDate=" + characterUploadedRegDate + "]";
	}

}
