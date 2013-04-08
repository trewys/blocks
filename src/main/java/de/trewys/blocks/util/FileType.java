/*
 * Copyright 2013 trewys GmbH 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package de.trewys.blocks.util;

import java.util.HashMap;
import java.util.Map;

public class FileType {

	private final static String APPLICATION_IMAGE = "image";
	private final static String APPLICATION_TEXT = "text";
	private final static String APPLICATION_PDF = "pdf";
	private final static String APPLICATION_WORD = "word";
	private final static String APPLICATION_EXCEL = "excel";
	private final static String APPLICATION_POWERPOINT = "powerpoint";
	private final static String APPLICATION_FILE = "file";
	
	private static Map<String, FileType> instances = 
		new HashMap<String, FileType>();

   public final static FileType BMP = new FileType("bmp", APPLICATION_IMAGE, "image/bmp");
   public final static FileType JPG = new FileType("jpg", APPLICATION_IMAGE, "image/jpeg");
   public final static FileType JPEG = new FileType("jpeg", APPLICATION_IMAGE, "image/jpeg");
   public final static FileType PNG = new FileType("png", APPLICATION_IMAGE, "image/png");
   public final static FileType GIF = new FileType("gif", APPLICATION_IMAGE, "image/gif");
   public final static FileType TIF = new FileType("tif", APPLICATION_IMAGE, "image/tiff");
   public final static FileType TIFF = new FileType("tiff", APPLICATION_IMAGE, "image/tiff");
  
   public final static FileType PDF = new FileType("pdf", APPLICATION_PDF, "application/pdf");
   
   public final static FileType RTF = new FileType("rtf", APPLICATION_TEXT, "application/rtf");
   public final static FileType TXT = new FileType("txt", APPLICATION_TEXT, "text/plain");
   public final static FileType CSS = new FileType("css", APPLICATION_TEXT, "text/css");
   
   public final static FileType DOC = new FileType("doc", APPLICATION_WORD, "application/msword");
   public final static FileType DOCX = new FileType("docx", APPLICATION_WORD, "application/msword");
   
   public final static FileType XLS = new FileType("xls", APPLICATION_EXCEL, "application/msexcel");
   public final static FileType XLSX = new FileType("xlsx", APPLICATION_EXCEL, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
   
   public final static FileType PPT = new FileType("ppt", APPLICATION_POWERPOINT, "application/mspowerpoint");
   public final static FileType PPTX = new FileType("pptx", APPLICATION_POWERPOINT, "application/mspowerpoint");
   
   public final static FileType JS = new FileType("js", APPLICATION_TEXT, "text/javascript");
   public final static FileType CSV = new FileType("csv", APPLICATION_TEXT, "text/comma-separated-values");
   
   public final static FileType UNKNOWN = new FileType(null, APPLICATION_FILE, null);
   
   static {
      addInstance(BMP);
      addInstance(JPG);
      addInstance(JPEG);
      addInstance(PNG);
      addInstance(GIF);
      addInstance(TIF);
      addInstance(TIFF);
      
      addInstance(PDF);
      
      addInstance(RTF);
      addInstance(TXT);
      addInstance(CSS);
      
      addInstance(DOC);
      addInstance(DOCX);
      
      addInstance(XLS);
      addInstance(XLSX);
      
      addInstance(PPT);
      addInstance(PPTX);
      
      addInstance(JS);
      
      addInstance(CSV);
   }
   
   private static void addInstance(FileType instance) {
      instances.put(instance.getSuffix(), instance);
   }
   
   public static FileType getInstance(String suffix) {
	   FileType fileType = instances.get(suffix);
	   if (fileType == null)
		   return UNKNOWN;
	   else
		   return fileType;
   }
	
	private String suffix;
	private String applicationType;
	private String mimeType;
	
	private FileType(String suffix, String applicationType, String mimeType) {
		super();
		this.suffix = suffix;
		this.applicationType = applicationType;
		this.mimeType = mimeType;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public String getMimeType() {
		return mimeType;
	}
	
	
}
