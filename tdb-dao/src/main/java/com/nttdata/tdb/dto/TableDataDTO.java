package com.nttdata.tdb.dto;

/**
 * @author jean.lorenzini
 *
 */
public class TableDataDTO {

	private String dataText;
	private int pbAssigned;
	private int pbResolved;
	private int pbSlaOk;
	private int pbSlaNok;
	private int inAssigned;
	private int inResolved;
	private int inSlaOk;
	private int inSlaNok;
	private int srAssigned;
	private int srResolved;
	private int srSlaOk;
	private int srSlaNok;
	
	/**
	 * @return the dataText
	 */
	public String getDataText() {
		return dataText;
	}
	/**
	 * @param dataText the dataText to set
	 */
	public void setDataText(String dataText) {
		this.dataText = dataText;
	}
	/**
	 * @return the pbAssigned
	 */
	public int getPbAssigned() {
		return pbAssigned;
	}
	/**
	 * @param pbAssigned the pbAssigned to set
	 */
	public void setPbAssigned(int pbAssigned) {
		this.pbAssigned = pbAssigned;
	}
	/**
	 * @return the pbResolved
	 */
	public int getPbResolved() {
		return pbResolved;
	}
	/**
	 * @param pbResolved the pbResolved to set
	 */
	public void setPbResolved(int pbResolved) {
		this.pbResolved = pbResolved;
	}
	/**
	 * @return the inAssigned
	 */
	public int getInAssigned() {
		return inAssigned;
	}
	/**
	 * @param inAssigned the inAssigned to set
	 */
	public void setInAssigned(int inAssigned) {
		this.inAssigned = inAssigned;
	}
	/**
	 * @return the inResolved
	 */
	public int getInResolved() {
		return inResolved;
	}
	/**
	 * @param inResolved the inResolved to set
	 */
	public void setInResolved(int inResolved) {
		this.inResolved = inResolved;
	}
	/**
	 * @return the srAssigned
	 */
	public int getSrAssigned() {
		return srAssigned;
	}
	/**
	 * @param srAssigned the srAssigned to set
	 */
	public void setSrAssigned(int srAssigned) {
		this.srAssigned = srAssigned;
	}
	/**
	 * @return the srResolved
	 */
	public int getSrResolved() {
		return srResolved;
	}
	/**
	 * @param srResolved the srResolved to set
	 */
	public void setSrResolved(int srResolved) {
		this.srResolved = srResolved;
	}
	/**
	 * @return the pbSlaOk
	 */
	public int getPbSlaOk() {
		return pbSlaOk;
	}
	/**
	 * @param pbSlaOk the pbSlaOk to set
	 */
	public void setPbSlaOk(int pbSlaOk) {
		this.pbSlaOk = pbSlaOk;
	}
	/**
	 * @return the pbSlaNok
	 */
	public int getPbSlaNok() {
		return pbSlaNok;
	}
	/**
	 * @param pbSlaNok the pbSlaNok to set
	 */
	public void setPbSlaNok(int pbSlaNok) {
		this.pbSlaNok = pbSlaNok;
	}
	/**
	 * @return the inSlaOk
	 */
	public int getInSlaOk() {
		return inSlaOk;
	}
	/**
	 * @param inSlaOk the inSlaOk to set
	 */
	public void setInSlaOk(int inSlaOk) {
		this.inSlaOk = inSlaOk;
	}
	/**
	 * @return the inSlaNok
	 */
	public int getInSlaNok() {
		return inSlaNok;
	}
	/**
	 * @param inSlaNok the inSlaNok to set
	 */
	public void setInSlaNok(int inSlaNok) {
		this.inSlaNok = inSlaNok;
	}
	/**
	 * @return the srSlaOk
	 */
	public int getSrSlaOk() {
		return srSlaOk;
	}
	/**
	 * @param srSlaOk the srSlaOk to set
	 */
	public void setSrSlaOk(int srSlaOk) {
		this.srSlaOk = srSlaOk;
	}
	/**
	 * @return the srSlaNok
	 */
	public int getSrSlaNok() {
		return srSlaNok;
	}
	/**
	 * @param srSlaNok the srSlaNok to set
	 */
	public void setSrSlaNok(int srSlaNok) {
		this.srSlaNok = srSlaNok;
	}
	
	public void addSrAssigned(){
		this.srAssigned++;
	}
	
	public void addSrSlaOk(){
		this.srSlaOk++;
	}
	
	public void addSrSlaNok(){
		this.srSlaNok++;
	}
	
	public void addPBAssigned(){
		this.pbAssigned++;
	}
	
	public void addPBSlaOk(){
		this.pbSlaOk++;
	}
	
	public void addPBSlaNok(){
		this.pbSlaNok++;
	}
	
	public void addInAssigned(){
		this.inAssigned++;
	}
	
	public void addInSlaOk(){
		this.inSlaOk++;
	}
	
	public void addInSlaNok(){
		this.inSlaNok++;
	}
	
	public float getAvrSlaOk(){
		int correction = inAssigned+pbAssigned+srAssigned;//inSlaNok+pbSlaNok+srSlaNok;
		int sumSlaOk = inSlaOk+pbSlaOk+srSlaOk;
		
		if(correction != 0){
			return ((sumSlaOk*100) / correction);
		} else {
			return 100F;
		}
		
	}
}
