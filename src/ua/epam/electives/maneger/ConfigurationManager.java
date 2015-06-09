package ua.epam.electives.maneger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class ConfigurationManager extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 2370899765469068776L;
    private static final Logger LOGGER = Logger
	    .getLogger(ConfigurationManager.class);
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String NO_SUCH_COMMAND = "NO_SUCH_COMMAND";
    public static final String START_PAGE_PATH = "START_PAGE_PATH";
    public static final String LOGIN_FAILED_PATH = "LOGIN_FAILED_PATH";
    public static final String LECTURER_MAIN_PAGE_PATH = "LECTURER_MAIN_PAGE_PATH";
    public static final String STUDENT_MAIN_PAGE_PATH = "STUDENT_MAIN_PAGE_PATH";
    public static final String LECTURER_STUDENT_PAGE_PATH = "LECTURER_STUDENT_PAGE_PATH";
    public static final String STUDENT_COURSE_PAGE_PATH = "STUDENT_COURSE_PAGE_PATH";
    public static final String STUDENT_ALL_COURSES_PAGE_PATH = "STUDENT_ALL_COURSES_PAGE_PATH";
    public static String path;
    private static ConfigurationManager instance;
    private Properties props;

    public void init() {
	String prefix = getServletContext().getRealPath("/");
	String file = getInitParameter("config-file");
	if (file != null) {
	    path = prefix + file;
	} else {
	    LOGGER.error("Configuration file directory not find" + prefix
		    + file);
	}
    }

    public static ConfigurationManager getInstance() {
	if (instance == null) {
	    instance = new ConfigurationManager();
	    instance.props = new Properties();
	    try (FileInputStream inStream = new FileInputStream(new File(path))) {
		instance.props.load(inStream);
	    } catch (FileNotFoundException e) {
		LOGGER.error("File not exist", e);
	    } catch (IOException e) {
		LOGGER.error("Prooblem with eccess to file", e);
	    }
	}
	return instance;
    }

    public String getProperty(String key) {
	return (String) props.getProperty(key);
    }
}