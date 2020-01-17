/**
 * @author Dilshad Mustafa
 * Copyright (c) Dilshad Mustafa
 * All Rights Reserved.
 * Created 20-Oct-2016
 * File Name : DMStdStorageHandler.java
 */

/**
Copyright (c) Dilshad Mustafa 2016. All Rights Reserved.

User and Developer License

1. You can use this Software for both Personal use as well as Commercial use, 
with or without paying fee to Dilshad Mustafa. Please read fully below for the 
terms and conditions. You may use this Software only if you comply fully with 
all the terms and conditions of this License. 

2. If you want to redistribute this Software, you should redistribute only the 
original source code from Dilshad Mustafa's project and/or the compiled object 
binary form of the original source code and you should ensure to bundle and 
display this original license text and Dilshad Mustafa's copyright notice along 
with it as well as in each source code file of this Software. 

3. If you want to embed this Software within your work, you should embed only the 
original source code from Dilshad Mustafa's project and/or the compiled object 
binary form of the original source code and you should ensure to bundle and display 
this original license text and Dilshad Mustafa's copyright notice along with it as 
well as in each source code file of this Software. 

4. You should not modify this Software source code and/or its compiled object binary 
form in any way.

5. You should not redistribute any modified source code of this Software and/or 
its compiled object binary form with any changes, additions, enhancements, 
updates or modifications. You should not redistribute any modified works of this 
Software. You should not create and/or redistribute any straight forward 
translation and/or implementation of this Software source code to same and/or 
another programming language, either partially or fully. You should not redistribute 
embedded modified versions of this Software source code and/or its compiled object 
binary in any form, both within as well as outside your organization, company, 
legal entity and/or individual. 

6. You should not embed any modification of this Software source code and/or its compiled 
object binary form in any way, either partially or fully.

7. Under differently named or renamed software, you should not redistribute this 
Software and/or any modified works of this Software, including its source code 
and/or its compiled object binary form. Under your name or your company name or 
your product name, you should not publish this Software, including its source code 
and/or its compiled object binary form, modified or original. 

8. You agree to use the original source code from Dilshad Mustafa's project only
and/or the compiled object binary form of the original source code.

9. You accept and agree fully to the terms and conditions of this License of this 
software product, under same software name and/or if it is renamed in future.

10. This software is created and programmed by Dilshad Mustafa and Dilshad holds the 
copyright for this Software and all its source code. You agree that you will not infringe 
or do any activity that will violate Dilshad's copyright of this software and all its 
source code.

11. The Copyright holder of this Software reserves the right to change the terms 
and conditions of this license without giving prior notice.

12. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHOR OR COPYRIGHT HOLDER
   BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
   ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.

*/

package com.dilmus.dilshad.scabiai;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dilmus.dilshad.storage.IStorageHandler;

/**
 * @author Dilshad Mustafa
 *
 */
public class DMStdStorageHandler implements IStorageHandler {

	private final Logger log = LoggerFactory.getLogger(DMStdStorageHandler.class);
	
	public DMStdStorageHandler() {
		
	}
	
	public int copyFromLocal(String storageFilePath, String localFilePath) throws Exception {
		Path localPath = Paths.get(localFilePath);

		Path storagePath = Paths.get(storageFilePath);
		// if storage file of index exists then delete storage file
		Files.deleteIfExists(storagePath);
		// copy _local file to storage file
		Files.copy(localPath, storagePath, StandardCopyOption.REPLACE_EXISTING); // StandardCopyOption.ATOMIC_MOVE throws exception

		return 0;
	}
	
	public int copyIfExistsToLocal(String storageFilePath, String localFilePath) throws Exception {
		Path storagePath = Paths.get(storageFilePath);
		
		if (Files.exists(storagePath, LinkOption.NOFOLLOW_LINKS)) {							
			// To file
			Path localPath = Paths.get(localFilePath);
			if (Files.exists(localPath, LinkOption.NOFOLLOW_LINKS)) {
				// logger.warn("copyIfExistsToLocal() File : " + localFilePath + " already exists. Overwriting");
				System.out.println("copyIfExistsToLocal() File : " + localFilePath + " already exists. Overwriting");
			}
			
			Files.copy(storagePath, localPath, StandardCopyOption.REPLACE_EXISTING); // StandardCopyOption.ATOMIC_MOVE throws Exception in thread "main" java.lang.UnsupportedOperationException: Unsupported copy option
		}

		return 0;
	}
	
	public int deleteIfExists(String storageFilePath) throws Exception {
		Path path = Paths.get(storageFilePath);
		Files.deleteIfExists(path);
		return 0;
	}
	
	public int mkdirIfAbsent(String dir) throws Exception {
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			boolean status = dirFile.mkdir();
			if (false == status)
				throw new IOException("Failed to create directory : " + dir + " Check if parent directories exist");
		}
		
		return 0;
	}
	
	public int deleteArrayDirIfExists(String dir) throws Exception {
		
		// throws directory is not empty exception
		// Path path = Paths.get(dir);
		// Files.deleteIfExists(path);
		
		// Delete dir, contained files and all sub-dirs instead of complaining dir is not empty
		File f = new File(dir);
		if (f.exists())
			deleteFileDir(f);
	
		return 0;
	}
	
	/* 
	 * Method : deleteDirIfExists(String dirPath)
	 * 
	 * Assumptions for Storage system that support creation of directory given directory name
	 * Parameter Name : dirPath
	 * Parameter Value :
	 * 		Any standard file system path for directory, example /home/<user>/testdata/storage
	 * 
	 * Delete entire directory contents of dirPath, all files and sub directories contained within dirPath
	 * Do not complain saying "Directory is not empty" 
	 * If the directory dirPath doesn't exist in the Storage System, don't throw exception
	 * -----------------------------------------------------------------------------------------------------------
	 * Assumptions for Storage system that does not support creation of directory given directory name
	 * Parameter Name : dirPath
	 * Parameter Value :
	 * 		<AnyDummyStringWithoutSlash>/<SomeDirectoryName>
	 * 		
	 * 		where <AnyDummyStringWithoutSlash> = <AppId> or "" (empty string) or any dummy string without File.separator ("/" or "\")
	 * 
	 * Delete all the files associated with <SomeDirectoryName> for example file names starting with <SomeDirectoryName> [<AnyDummyStringWithoutSlash> can also be appended if needed]
	 * 
	 * If the files don't exist in the Storage System, don't throw exception
	 * 
	 * If this functionality is not possible in the storage system (for example listing all the files with file names starting with <SomeDirectoryName>)
	 * then throw new DScabiException("Not Supported Exception", "Error Code");
	 */
	public int deleteDirIfExists(String dir) throws Exception {
		// Delete dir, contained files and all sub-dirs instead of complaining dir is not empty
		File f = new File(dir);
		if (f.exists())
			deleteFileDir(f);
	
		return 0;
	}
	
	public boolean isFileExists(String storageFilePath) throws Exception {
		Path storagePath = Paths.get(storageFilePath);
		
		if (Files.exists(storagePath, LinkOption.NOFOLLOW_LINKS)) {							
			return true;
		} else
			return false;

	}
	
	public void close() throws Exception {
		// do nothing because nothing to close
	}	
	
	private int deleteFileDir(File f) {
		if (f.exists() == false)
			return 0;
		if (f.isDirectory()) {
			File[] fa = f.listFiles();
			for (File e : fa) {
				if (e.isDirectory())
					deleteFileDir(e);
				else if (e.isFile()) {
					// System.out.println("DMStdStorageHandler deleteFileDir(f) Deleting file : " + e);
					log.debug("DMStdStorageHandler deleteFileDir(f) Deleting file : {}", e);
					e.delete();
				}
			}
			// System.out.println("DMStdStorageHandler deleteFileDir(f) Deleting dir : " + f);
			log.debug("DMStdStorageHandler deleteFileDir(f) Deleting dir : {}", f);
			f.delete();
		}	
		else if (f.isFile()){
			// System.out.println("DMStdStorageHandler deleteFileDir(f) Deleting2 file : " + f);
			log.debug("DMStdStorageHandler deleteFileDir(f) Deleting2 file : {}", f);
			f.delete();
		}
		
		return 0;
	}	
	
}
