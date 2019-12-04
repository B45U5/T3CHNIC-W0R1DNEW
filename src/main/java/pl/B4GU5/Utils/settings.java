
package pl.B4GU5.Utils;

import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import org.json.JSONObject;
import java.util.Scanner;
import java.io.File;

public class settings
{

	private static String packVer;
	private static String pack;
	private static String ram;
	private static String launcherVer;
	private static String premium;
	private static String token;
	private static String slash = File.separator;
	private static String workingDir = String.valueOf(System.getProperty("user.home")) + settings.slash + "TechnicWorld";
	private static String javaPath;
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";
	private static Boolean isLoaded = false;
	private static String username;
    
    public static void loadSettings() throws FileNotFoundException {
         String filename = String.valueOf(settings.workingDir) + settings.slash + "launcher_settings.json";
         File file = new File(filename);
        if (file.exists()) {
            Scanner inputFile = new Scanner(file);
            String jsonTxt = "";
            while (inputFile.hasNext()) {
                jsonTxt = String.valueOf(jsonTxt) + inputFile.nextLine();
            }
            inputFile.close();
            JSONObject json = new JSONObject(jsonTxt);
            settings.pack = json.get("selectedPack").toString();
            settings.packVer = json.get("packVersion").toString();
            settings.ram = json.get("ram").toString();
            settings.launcherVer = json.get("launcherVersion").toString();
            settings.token = json.get("token").toString();
            settings.premium = json.get("premium").toString();
        }
        else {
            settings.pack = "select";
            settings.packVer = "-1";
            settings.ram = "1";
            settings.launcherVer = "-1";
            settings.token = "-1";
            settings.premium = "false";
        }
        isLoaded = true;
    }
    
    public static String getString() {
        return "Selected pack: " + settings.pack + ", Version: " + settings.packVer + ", Ram: " + settings.ram + ", Launcher version: " + settings.launcherVer + ", token: " + settings.token + ", premium: " + settings.premium;
    }
    
    public static boolean saveSettings() {
    	Map<String, String> jsonCreator = new HashMap<String, String>();
        jsonCreator.put("selectedPack", settings.pack);
        jsonCreator.put("packVersion", settings.packVer);
        jsonCreator.put("ram", settings.ram);
        jsonCreator.put("launcherVersion", settings.launcherVer);
        jsonCreator.put("token", settings.token);
        jsonCreator.put("premium", settings.premium);
        JSONObject j = new JSONObject(jsonCreator);
        try {
             FileWriter fileWriter = new FileWriter(String.valueOf(settings.workingDir) + settings.slash + "launcher_settings.json");
            fileWriter.write(j.toString());
            fileWriter.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static void setPack( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        pack = txt;
    }
    
    public static void setUsername( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        username = txt;
    }
    
    public static void setPackVersion( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        packVer = txt;
    }
    
    public static void setRam( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ram = txt;
    }
    
    public static void setLauncherVersion( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        launcherVer = txt;
    }
    
    public static void setToken( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        token = txt;
    }
    
    public static void setPremium( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        premium = txt;
    }
    
    public static void setJavaPath( String txt) {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        javaPath = txt;
    }
    
    public static String getPack() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return pack;
    }
    
    public static String getPackVersion() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return packVer;
    }
    
    public static String getRam() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return ram;
    }
    
    public static String getLauncherVersion() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return launcherVer;
    }
    
    public static String getToken() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return token;
    }
    
    public static String getPremium() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return premium;
    }
    
    public static String getJavaPath() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return javaPath;
    }
    public static String getUserAgent() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return userAgent;
    }
    public static String getUsername() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return username;
    }
    public static String getWorkingDir() {
        if (!isLoaded) {
        	try {
				loadSettings();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return workingDir;
    }
}
