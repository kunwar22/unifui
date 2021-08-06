package in.co.srdt.unif.storage;

import java.io.File;
import java.io.IOException;

public interface StorageService {

	void init();

	void deleteAll();

	boolean jrxmlFileExists(String file) throws IOException;

	boolean jasperFileExists(String file);

	String loadJrxmlFile(String file) throws IOException;

	File loadJasperFile(String file);
	
}
