package in.co.srdt.unif.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.util.FileSystemUtils;



public class FileSystemStorageService implements StorageService {

	String reportlocation = "/EmployeeDocs/reports";
	String storageLocation = "/EmployeeDocs/reports/storage";
	
	String reportfilePath = new File("").getAbsolutePath() + File.separator + reportlocation;
	String storagefilePath = new File("").getAbsolutePath() + File.separator + storageLocation;
	
	private final Path rootLocation= Paths.get(reportfilePath);
	private final Path reportLocation=Paths.get(storagefilePath);
	
//	private final Path rootLocation=Paths.get("E:\\DevOps\\Asmita\\UNIF_ERP_ASMITA_18_02_2021_SECURE\\unif-ui\\src\\main\\resources\\reports\\");
//	private final Path reportLocation=Paths.get("E:\\DevOps\\Asmita\\UNIF_ERP_ASMITA_18_02_2021_SECURE\\unif-ui\\src\\main\\resources\\reports\\storage\\");
	
	/*E:\\UPDEVOPS\\Asmita\\UNIF_ASMITA_05_02_2021_WRK\\unif\\src\\main\\resources\\reports\\storage*/
	//private Resource storageLocation;	
	
	
	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}


	@Override
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not delete files and folders", e);
		}
	}

	@Override
	public boolean jrxmlFileExists(String file) throws IOException {
		Path reportFile = reportLocation;
		reportFile = reportFile.resolve(file + ".jrxml");
		if (Files.exists(reportFile))
			return true;
		// @formatter:on
		return false;
	}

	@Override
	public boolean jasperFileExists(String file) {
		Path reportFile = rootLocation;
		reportFile = reportFile.resolve(file + ".jasper");
		if (Files.exists(reportFile))
			return true;
		return false;
	}

	@Override
	public String loadJrxmlFile(String file) throws IOException {
		Path reportFile = reportLocation;
		reportFile = reportFile.resolve(file + ".jrxml");
		return reportFile.toString();
	}

	@Override
	public File loadJasperFile(String file) {
		Path reportFile = rootLocation;
		reportFile = reportFile.resolve(file + ".jasper");
		return reportFile.toFile();
	}

}
