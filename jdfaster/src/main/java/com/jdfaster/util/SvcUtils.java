package com.jdfaster.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.jdfaster.test.services.run.RunTestOut;
import com.jdfaster.test.services.run.TestResult;

public class SvcUtils {
	public static String makeDefaultPath() {
		StringBuilder sb = new StringBuilder(System.getProperty("user.home"));
		sb.append(File.separator).append("Documents").append(File.separator).append("Jdfaster");
		return sb.toString();
	}

	public static void makeResultXMLFile(RunTestOut testDVO) throws Exception {
		if (testDVO == null || testDVO.getResultDVOList() == null || testDVO.getResultDVOList().size() <= 0)
			return;
		File folder = new File(makeDefaultPath());
		if (!folder.exists()) {
			folder.mkdir();
		}

		List<TestResult> resultDVOList = testDVO.getResultDVOList();

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Node root = document.createElement("TestResult");
		document.appendChild(root);
		{
			for (TestResult dvo : resultDVOList) {
				Element service = document.createElement("Servicename");
				service.setAttribute("methodName", dvo.getMethodName());
				root.appendChild(service);
				{
					Element theadName = document.createElement("threadName");
					theadName.appendChild(document.createTextNode(dvo.getThreadName()));
					service.appendChild(theadName);
				}
				{
					Element runTime = document.createElement("runTime");
					runTime.appendChild(document.createTextNode(dvo.getRunTime()));
					service.appendChild(runTime);
				}
				{
					Element conTime = document.createElement("conTime");
					conTime.appendChild(document.createTextNode(dvo.getConnectionTime()));
					service.appendChild(conTime);
				}
				{
					Element startTime = document.createElement("startTime");
					startTime.appendChild(document.createTextNode(dvo.getStartTime()));
					service.appendChild(startTime);
				}
				{
					Element stauts = document.createElement("stauts");
					stauts.appendChild(document.createTextNode(dvo.getStatus()));
					service.appendChild(stauts);
				}
			}
		}
		// TODO : 시나리오 명 갖고 오는 로직 필요.
		String curDate = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		StringBuilder sb = new StringBuilder(makeDefaultPath());
		sb.append(File.separator).append("Jdfaster_").append(curDate).append(".xml");
		DOMSource xmlDOM = new DOMSource(document);
		StreamResult xmlFile = new StreamResult(new File(sb.toString()));
		TransformerFactory.newInstance().newTransformer().transform(xmlDOM, xmlFile);
	}

	public static void makeJsonFile(String result) throws Exception {
		File folder = new File(makeDefaultPath());
		if (!folder.exists()) {
			folder.mkdir();
		}
		String curDate = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		StringBuilder sb = new StringBuilder(makeDefaultPath());
		sb.append(File.separator).append("Jdfaster_").append(curDate).append(".txt");
		Files.write(Paths.get(sb.toString()), result.getBytes());
	}
	
	public static RunTestOut readResult(String fullPath) throws Exception{
		String content = new String(Files.readAllBytes(Paths.get(fullPath)));
		return new ObjectMapper().readValue(content, RunTestOut.class);
	}
	
	public static String getProperties(String key) throws Exception {
		InputStream inputStream;
		Properties prop = new Properties();
		String propFileName = "config/config.properties";
		
		inputStream = SvcUtils.class.getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		String value = prop.getProperty(key);
		value = value.replace("\"", "");
		return value;
	}
}
