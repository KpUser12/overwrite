package test;



import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;

import java.util.ArrayList;

import java.util.List;
import java.util.Properties;

public class Overwrite {

	static String inputFilePath = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\pipeline demo\\MMC-IV.properties";
	static String artifactFilePath = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\pipeline demo\\MMC-IV.properties";

	public static Properties readFile(String filePath) throws IOException {

		Properties prop = new Properties();
		FileReader reader = new FileReader(inputFilePath);

		prop.load(reader);

		return prop;

	}

	public static void updateIVArtifact(String artifactFilePath) throws IOException {
		Properties properties = readFile(inputFilePath);
		String hostUrl = properties.getProperty("host");
		System.out.println(hostUrl);

		String constantPart = hostUrl.split("//")[0];
		String host = hostUrl.split("//")[1].split(":")[0];
		String port = hostUrl.split("//")[1].split(":")[1];

		System.out.println(constantPart);
		System.out.println(host);
		System.out.println(port);

		Properties artifactProps = readFile(artifactFilePath);

		String hostName = artifactProps.getProperty("host");
		hostName = "mongo://" + hostUrl.split("//")[1].split(":")[0];
//		System.out.println(hostName);
//		System.out.println(port);
		artifactProps.setProperty("port", port);
		artifactProps.setProperty("host", hostName.toString());
		// OutputStream out = new FileOutputStream(artifactFilePath);
		PrintWriter out = new PrintWriter(new FileOutputStream(artifactFilePath));
		List<String> list = new ArrayList<String>();
		artifactProps.list(out);
		System.out.println(artifactProps);
		out.flush();
		out.close();

		System.out.println("***File updated***");

	}

	public static void main(String args[]) throws IOException {

		readFile(inputFilePath);
		updateIVArtifact(artifactFilePath);

	}

}
