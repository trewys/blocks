/*
 * Copyright 2012 trewys GmbH
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
 * 
 */

package de.trewys.blocks.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtil {

	private static FileUtil instance;
	
	private String  tempDirPath = "";
	
	public static FileUtil getInstance() {
		if (instance == null)
			instance = new FileUtil();
		
		return instance;
	}
	
	private FileUtil() {}

	public void deleteTempDir() {
		File fi = new File(getTempDir());
		if (!fi.canWrite())
			return;

		deleteDir(fi);
	}

	public void deleteDir(File dir) {
		// delete contents
		deleteDirContents(dir);
		// finally delete empty directory
		if (!dir.delete())
			throw new RuntimeException("deleteDir(): Error while delete dir ["
					+ dir.getPath() + "]");
	}

	public void deleteDirContents(File dir) {
		File[] fiList = dir.listFiles();
		if (fiList == null)
			return;
		for (int i = 0; i < fiList.length; i++)
			if (fiList[i].isDirectory()) {
				// delete content
				deleteDirContents(fiList[i]);
				// and then empty directory
				if (!fiList[i].delete())
					throw new RuntimeException(
							"deleteDirContents(): Error while delete dir ["
									+ fiList[i].getPath() + "]");
			} else if (!fiList[i].delete())
				throw new RuntimeException(
						"deleteDirContents(): Error while delete file ["
								+ fiList[i].getPath() + "]");
	}

	public String getTempDir() {
		String tempDirName = System.getProperty("java.io.tmpdir") + tempDirPath;

		createDir(tempDirName);
		
		return tempDirName;
	}
	
	public static void createDir(String path) {
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
	}

	/**
	 * Erstellt einen Dateienamenfilter fuer Dateiendungen
	 * 
	 * @param endsWith
	 *            z.B. ".xml"
	 * @return FilenameFilter
	 */
	public static FilenameFilter getFileNameFilter(final String endsWith) {
		return new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				if (endsWith == null)
					return true;
				return fileName.toLowerCase().endsWith(endsWith.toLowerCase());
			}
		};
	}

	public static File[] getFileList(String path, String fileNameEndsWith) {
		if (path == null)
			return null;
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory())
			return dir.listFiles(FileUtil.getFileNameFilter(fileNameEndsWith));
		return null;
	}

	public String getTempDirPath() {
		return tempDirPath;
	}

	public void setTempDirPath(String tempDirPath) {
		this.tempDirPath = tempDirPath;
	}
	
}
