/**
 * Copyright 2012 Lyncode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lyncode.jtwig.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author "João Melo <jmelo@lyncode.com>"
 *
 */
public class ResourceManager {
	private File file;
	private File baseDir;
	
	public ResourceManager (String filename) throws IOException {
		this.file  = new File(filename);
		if (!this.file.exists()) throw new FileNotFoundException(filename);
		this.baseDir = this.file.getParentFile();
		if (this.baseDir == null) throw new IOException("No parent directory in " +filename);
	}

	public File getFile() {
		return file;
	}

	public File getBaseDir() {
		return baseDir;
	}
	
	public String getFile (String relative) {
		String path = this.baseDir.getAbsolutePath();
		if (!path.endsWith(File.separator))
			path += File.separator;
		return path + relative;
	}
}
