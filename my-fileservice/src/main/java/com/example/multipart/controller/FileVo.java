package com.example.multipart.controller;

public class FileVo {

	 private String fileName;

     private Long fileSize;
     private String fileUuid;
     private String filePreviewUrl;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public String getFilePreviewUrl() {
		return filePreviewUrl;
	}

	public void setFilePreviewUrl(String filePreviewUrl) {
		this.filePreviewUrl = filePreviewUrl;
	}

	@Override
	public String toString() {
		return "FileVo{" +
				"fileName='" + fileName + '\'' +
				", fileSize=" + fileSize +
				", fileUuid='" + fileUuid + '\'' +
				", filePreviewUrl='" + filePreviewUrl + '\'' +
				'}';
	}
}
