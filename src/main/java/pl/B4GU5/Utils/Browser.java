package pl.B4GU5.Utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.SystemUtils;

public class Browser {
	
	public static void browseURL(String urlString) {

	    try {
	        if (SystemUtils.IS_OS_LINUX) {
	            // Workaround for Linux because "Desktop.getDesktop().browse()" doesn't work on some Linux implementations
	            if (Runtime.getRuntime().exec(new String[] { "which", "xdg-open" }).getInputStream().read() != -1) {
	                Runtime.getRuntime().exec(new String[] { "xdg-open", urlString });
	            }
	        } else {
	            if (Desktop.isDesktopSupported())
	            {
	                Desktop.getDesktop().browse(new URI(urlString));
	            }
	        }

	    } catch (IOException | URISyntaxException e) {
	    	System.out.println(e);
	    }
	}
	
}
