package com.ERR.infra.character;

import java.util.Date;

public class CharacterDto {
	private String characterSeq;
	private String characterName;
	private Integer characterHp;
	private Integer characterMp;
	private Integer characterDef;
	private Integer characterAtk;
	private String characterVideoPath;
	private String characterDept;
	private Integer characterDelNy;
	private Date characterRegDate;
	private Date characterModDate;
	
	private String[] checkboxSeqArray = null;
	
	private String XtopBarSearchInputValue;

	public String getCharacterSeq() {
		return characterSeq;
	}

	public void setCharacterSeq(String characterSeq) {
		this.characterSeq = characterSeq;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Integer getCharacterHp() {
		return characterHp;
	}

	public void setCharacterHp(Integer characterHp) {
		this.characterHp = characterHp;
	}

	public Integer getCharacterMp() {
		return characterMp;
	}

	public void setCharacterMp(Integer characterMp) {
		this.characterMp = characterMp;
	}

	public Integer getCharacterDef() {
		return characterDef;
	}

	public void setCharacterDef(Integer characterDef) {
		this.characterDef = characterDef;
	}

	public Integer getCharacterAtk() {
		return characterAtk;
	}

	public void setCharacterAtk(Integer characterAtk) {
		this.characterAtk = characterAtk;
	}

	public String getCharacterVideoPath() {
		return characterVideoPath;
	}

	public void setCharacterVideoPath(String characterVideoPath) {
		this.characterVideoPath = characterVideoPath;
	}

	public String getCharacterDept() {
		return characterDept;
	}

	public void setCharacterDept(String characterDept) {
		this.characterDept = characterDept;
	}

	public Integer getCharacterDelNy() {
		return characterDelNy;
	}

	public void setCharacterDelNy(Integer characterDelNy) {
		this.characterDelNy = characterDelNy;
	}

	public Date getCharacterRegDate() {
		return characterRegDate;
	}

	public void setCharacterRegDate(Date characterRegDate) {
		this.characterRegDate = characterRegDate;
	}

	public Date getCharacterModDate() {
		return characterModDate;
	}

	public void setCharacterModDate(Date characterModDate) {
		this.characterModDate = characterModDate;
	}

	public String getXtopBarSearchInputValue() {
		return XtopBarSearchInputValue;
	}

	public void setXtopBarSearchInputValue(String xtopBarSearchInputValue) {
		XtopBarSearchInputValue = xtopBarSearchInputValue;
	}
	

	public String[] getCheckboxSeqArray() {
		return checkboxSeqArray;
	}

	public void setCheckboxSeqArray(String[] checkboxSeqArray) {
		this.checkboxSeqArray = checkboxSeqArray;
	}

	@Override
	public String toString() {
		return "CharacterDto [characterSeq=" + characterSeq + ", characterName=" + characterName + ", characterHp="
				+ characterHp + ", characterMp=" + characterMp + ", characterDef=" + characterDef + ", characterAtk="
				+ characterAtk + ", characterVideoPath=" + characterVideoPath + ", characterDept=" + characterDept
				+ ", characterDelNy=" + characterDelNy + ", characterRegDate=" + characterRegDate
				+ ", characterModDate=" + characterModDate + ", XtopBarSearchInputValue=" + XtopBarSearchInputValue
				+ "]";
	}

	
	

}
