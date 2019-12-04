package pl.B4GU5.TW.Controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.event.HyperlinkEvent;

import org.apache.commons.codec.binary.Hex;
import org.codefx.libfx.control.webview.WebViewHyperlinkListener;
import org.codefx.libfx.control.webview.WebViews;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.schlegel11.jfxanimation.JFXAnimationTemplates;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import pl.B4GU5.TW.Main;
import pl.B4GU5.TW.Animations.animations;
import pl.B4GU5.Utils.Browser;
import pl.B4GU5.Utils.LogType;
import pl.B4GU5.Utils.Logger;
import pl.B4GU5.Utils.jsonReader;
import pl.B4GU5.Utils.modsListClass;
import pl.B4GU5.Utils.settings;

public class MainController {
	
	private Boolean isPlayable = false;
	private ArrayList<modsListClass> modsList = new ArrayList<modsListClass>();
	private ArrayList<modsListClass> modsListInDir = new ArrayList<modsListClass>();
	private ArrayList<String> addonsList = new ArrayList<String>();
    public String slash = File.separator;
    private int mods = 0;
    private String modsPath = settings.getWorkingDir() + slash + settings.getPack() + slash + "Minecraft" + slash + "mods";
    private String minecraftPath = settings.getWorkingDir() + slash + settings.getPack() + slash + "Minecraft";
    private String urlModsPath = "https://api.technicworld.pl/api/modpack-" + settings.getPack();
    private String urlAddonsList = "https://api.technicworld.pl/api/addons_" + settings.getPack() + ".json";
    private String urlModsList = "https://api.technicworld.pl/api/mods-" + settings.getPack() + ".json";
    private String urlPackVersion = "0.0.0";

	
	@FXML
	private Label playText;
	@FXML
	private MaterialDesignIconView settingsICON;
	@FXML
	private Label loginButtonText;
	@FXML
	private WebView webBrowser;
	@FXML
	private JFXProgressBar browserProgress;
	@FXML
	private AnchorPane loginPane;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private VBox loginFormPane;
	@FXML
	private VBox loadingText;
	@FXML
	private AnchorPane menuLeftPane;
	@FXML
	private AnchorPane menuPackLeftPane;
	@FXML
	private AnchorPane modpackPane;
	@FXML
	private JFXTextField loginField;
	@FXML
	private JFXPasswordField passwordField;
	@FXML
	private Text loginText;
	@FXML
	private JFXProgressBar loginLoading;
	@FXML
	private Text nick;
	@FXML
	private Text rankTxt;
	@FXML
	private Button skyblockButton;
	@FXML
	private Button survivalButton;
	@FXML
	private AnchorPane downloadInfoPane;
	@FXML
	private Text downloadInfo;
	@FXML
	private JFXProgressBar downloadProgress;
	
	@FXML
	private void addons(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
	}
	@FXML
	private void news(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
		loadUrl("https://api.technicworld.pl/?site=lglowna" + "&accesstoken=" + settings.getToken());
	}
	@FXML
	private void play(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
	}
	@FXML
	private void settingsBTN(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
	}
	@FXML
	private void settings(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
	}
	@FXML
	private void modpacks(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
	}	
		
	@FXML
	private void playAnim(MouseEvent event) {
		Timeline timeline = animations.fadeInBTN().build(JFXAnimationTemplates::buildTimeline, playText);
		timeline.play();
	}
	
	@FXML
	private void playExit(MouseEvent event) {
		Timeline timeline = animations.fadeOutBTN().build(JFXAnimationTemplates::buildTimeline, playText);
		timeline.play();
	}
	@FXML
	private void loginAnim(MouseEvent event) {
		Timeline timeline = animations.fadeInBTN().build(JFXAnimationTemplates::buildTimeline, loginButtonText);
		timeline.play();

	}
	
	@FXML
	private void loginExit(MouseEvent event) {
		Timeline timeline = animations.fadeOutBTN().build(JFXAnimationTemplates::buildTimeline, loginButtonText);
		timeline.play();
	}
	
	@FXML
	private void settingsAnim(MouseEvent event) {
		Timeline timeline = animations.fadeInBTN().build(JFXAnimationTemplates::buildTimeline, settingsICON);
		timeline.play();
	}
	
	@FXML
	private void settingsExit(MouseEvent event) {
		Timeline timeline = animations.fadeOutBTN().build(JFXAnimationTemplates::buildTimeline, settingsICON);
		timeline.play();
	}
	
	@FXML
	private void initialize() throws MalformedURLException, IOException {
		menuLeftPane.setVisible(true);
		loginLoading.setVisible(true);
		loadingText.setVisible(true);
		loginFormPane.setVisible(false);
		GaussianBlur blur = new GaussianBlur(8);
		mainPane.setEffect(blur);
		Runnable thread = new Runnable() {
			public void run() {
				Platform.runLater(new Runnable() {
		            @Override public void run() {
		        		checkToken();
			        }
				});
			}
		};
		ExecutorService executor = Executors.newCachedThreadPool();
	    executor.submit(thread);
		
		
		
	}
	//PACK ----------------------------------------------------------------------------------------------------------------------->	

	private void checkModpack() {
		if(settings.getPack().equals("select")) {
			modpackPane.setTranslateX(-1 * modpackPane.getBoundsInParent().getWidth());
			menuPackLeftPane.setVisible(true);
        	Timeline packAnimate = animations.leftOpen().build(JFXAnimationTemplates::buildTimeline, modpackPane);
        	Timeline mainBlur = animations.blur().build(JFXAnimationTemplates::buildTimeline, mainPane);
        	mainBlur.play();
    		packAnimate.play();
		} else {
			modpackCheck();
		}
	}
	
	@FXML
	private void skyblockButton(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
		settings.setPack("skyblock");
		settings.saveSettings();
		Main.changeStageName("TechnicWorld | Skyblock");
    	Timeline packAnimate = animations.leftClose().build(JFXAnimationTemplates::buildTimeline, modpackPane);
    	Timeline mainBlur = animations.unBlur().build(JFXAnimationTemplates::buildTimeline, mainPane);
    	mainBlur.play();
    	packAnimate.play();
    	packAnimate.setOnFinished(a -> modpackSelected());
    	
	}
	@FXML
	private void survivalButton(ActionEvent e) {
		Logger.log(LogType.info(), e.toString());
		settings.setPack("survival");
		settings.saveSettings();
		Main.changeStageName("TechnicWorld | Survival");
    	Timeline packAnimate = animations.leftClose().build(JFXAnimationTemplates::buildTimeline, modpackPane);
    	Timeline mainBlur = animations.unBlur().build(JFXAnimationTemplates::buildTimeline, mainPane);
    	mainBlur.play();
    	packAnimate.play();
    	packAnimate.setOnFinished(a -> modpackSelected());

	}	
	
	private void modpackSelected() {
		checkModpack();
		leftPackPaneSetVisible(false);
	}
	
	private void leftPackPaneSetVisible(Boolean t) {
		menuPackLeftPane.setVisible(t);
	}
	
	private void modpackCheck() {
	    modsPath = settings.getWorkingDir() + slash + settings.getPack() + slash + "Minecraft" + slash + "mods";
	    minecraftPath = settings.getWorkingDir() + slash + settings.getPack() + slash + "Minecraft";
	    urlModsPath = "https://api.technicworld.pl/api/modpack-" + settings.getPack();
	    urlAddonsList = "https://api.technicworld.pl/api/addons_" + settings.getPack() + ".json";
	    urlModsList = "https://api.technicworld.pl/api/mods-" + settings.getPack() + ".json";
	    urlPackVersion = "0.0.0";
		downloadInfoPane.setVisible(true);
		Runnable thread = new Runnable() {
			public void run() {
				try {
					getModsJson();
					if (startCheckingAndDownloadMods(urlModsPath, modsPath)) {
						if (downloadConfigs(new URL("https://api.technicworld.pl/api/config-"+settings.getPack()+".zip"), new File(minecraftPath))) {
				            javafx.application.Platform.runLater( () -> downloadInfoPane.setVisible(false));
						}
					}
				} catch (JSONException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		ExecutorService executor = Executors.newCachedThreadPool();
	    executor.submit(thread);
		
	}
	
    private boolean startCheckingAndDownloadMods(String modsUrl, String modspath) throws MalformedURLException {
            for (modsListClass mods : modsList) {
                if (mods.isAddon()) {
                    String modStr = String.valueOf(modspath) + "/" + mods.getName().replace("|", slash);
                    File mod = new File(modStr);
                    String modStrAddon = String.valueOf(modspath) + "/" + mods.getName().replace("|", slash) + ".disable";
                    File modAddon = new File(modStrAddon);
                    if (!modAddon.exists() && !mod.exists()) {
                    	downloadMod(new URL(String.valueOf(modsUrl) + "/" + mods.getName().replace("|", "/").replaceAll(" ", "%20")), String.valueOf(modspath) + slash + mods.getName().replace("|", slash), mods.getName(), new File(String.valueOf(modspath) + slash + "1.7.10"), new File(modspath), mods.isAddon(), mods.getId(), modsList.size());                     
                    }
                    else {
                        try {
                            if (getModHash(modStrAddon).contains(mods.getHash().toLowerCase()) || getModHash(modStr).contains(mods.getHash().toLowerCase())) {
                                continue;
                            }
                        	downloadMod(new URL(String.valueOf(modsUrl) + "/" + mods.getName().replace("|", "/").replaceAll(" ", "%20")), String.valueOf(modspath) + slash + mods.getName().replace("|", slash), mods.getName(), new File(String.valueOf(modspath) + slash + "1.7.10"), new File(modspath), mods.isAddon(), mods.getId(), modsList.size());
                            
                        }
                        catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        catch (FileNotFoundException e2) {
                            e2.printStackTrace();
                        }
                        catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                else {
                    String modStr = String.valueOf(modspath) + "/" + mods.getName().replace("|", slash);
                    File mod = new File(modStr);
                    if (mod.exists()) {
                        try {
                            if (getModHash(modStr).contains(mods.getHash().toLowerCase())) {
                                continue;
                            }
                        		downloadMod(new URL(String.valueOf(modsUrl) + "/" + mods.getName().replace("|", "/").replaceAll(" ", "%20")), String.valueOf(modspath) + slash + mods.getName().replace("|", slash), mods.getName(), new File(String.valueOf(modspath) + slash + "1.7.10"), new File(modspath), mods.isAddon(), mods.getId(), modsList.size());
                        }
                        catch (NoSuchAlgorithmException e4) {
                            e4.printStackTrace();
                        }
                        catch (FileNotFoundException e5) {
                            e5.printStackTrace();
                        }
                        catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    else {
                    	downloadMod(new URL(String.valueOf(modsUrl) + "/" + mods.getName().replace("|", "/").replaceAll(" ", "%20")), String.valueOf(modspath) + slash + mods.getName().replace("|", slash), mods.getName(), new File(String.valueOf(modspath) + slash + "1.7.10"), new File(modspath), mods.isAddon(), mods.getId(), modsList.size());
      	
                    }
                }
            }
        
        return true;
    }
    
    private boolean getModsJson() throws JSONException, IOException {
        JSONObject json = jsonReader.readJsonFromUrl(urlModsList);
        int jsonSize = json.getJSONArray("mods").length();
        String urlPackVersion = json.getString("version");
        for (int i = 0; i < jsonSize; ++i) {
            JSONObject jsonObject = json.getJSONArray("mods").getJSONObject(i);
            modsListClass arrayMods = new modsListClass();
            arrayMods.setId(i);
            arrayMods.setName(jsonObject.get("name").toString());
            arrayMods.setAddon(jsonObject.get("addon").toString());
            arrayMods.setHash(jsonObject.get("md5").toString().toLowerCase());
            modsList.add(arrayMods);
            mods += 1;
        }
        return true;
    }
	
	//</PACK --------------------------------------------------------------------------------------------------------------------->	
	
	
	//[+]  \
//		   | Pozdrawiam dekompilatorów! :)
	//[-]  /

	
	//LOGIN ----------------------------------------------------------------------------------------------------------------------->	
	
	private void checkToken() {
		loginLoading.setVisible(true);
		loadingText.setVisible(true);
		loginFormPane.setVisible(false);
        try {
			JSONObject json = jsonReader.readJsonFromUrl("https://api.technicworld.pl/index.php?api=check&token=" + settings.getToken());
	        if (json.get("token").equals("succ")) {
	        	//Token ważny
	        	loginLoading.setVisible(true);
	        	browserInit(); //Wczytanie przeglądarki
	    		loadingText.setVisible(true);
	    		loginFormPane.setVisible(false);
            	Timeline loginAnimate = animations.leftClose().build(JFXAnimationTemplates::buildTimeline, loginPane);
            	Timeline mainBlur = animations.unBlur().build(JFXAnimationTemplates::buildTimeline, mainPane);
            	mainBlur.play();
        		loginAnimate.play();
        		loginAnimate.setOnFinished(e -> modpackInit());
        		settings.setUsername(json.get("username").toString());
        		nick.setText(settings.getUsername());
        		if(json.get("rank").toString().equals("Wlasciciel")) {
        			rankTxt.setText("#Właścicel");
        		} else if(json.get("rank").toString().equals("default") || json.get("rank").toString().equals("Default")) {
        			rankTxt.setText("#Gracz");
        		} else if(json.get("rank").toString().isEmpty()) {
            		rankTxt.setText("#Gracz");
        		} else if(json.get("rank").toString().equals("admin")) {
            		rankTxt.setText("#Administrator");
        		} else {
            		rankTxt.setText("#" + json.get("rank").toString());
        		}
	        } else if (settings.getToken().contentEquals("-1")) {
	        	//Nigdy nie zalogowano
	        	loginLoading.setVisible(false);
	    		loadingText.setVisible(false);
	    		loginFormPane.setVisible(true);
            	Timeline loginAnimate = animations.leftOpen().build(JFXAnimationTemplates::buildTimeline, loginPane);
            	Timeline mainBlur = animations.blur().build(JFXAnimationTemplates::buildTimeline, mainPane);
            	mainBlur.play();
            	loginAnimate.setOnFinished(e -> leftPaneSetVisible(true));
        		loginAnimate.play();
        		
	        } else {
	        	//Wylogowano
	        	loginLoading.setVisible(false);
	    		loadingText.setVisible(false);
	    		loginFormPane.setVisible(true);
	            Logger.log(LogType.warn(), "Wylogowano!");
            	Timeline loginAnimate = animations.leftOpen().build(JFXAnimationTemplates::buildTimeline, loginPane);
            	Timeline mainBlur = animations.blur().build(JFXAnimationTemplates::buildTimeline, mainPane);
            	mainBlur.play();
            	loginAnimate.setOnFinished(e -> leftPaneSetVisible(true));
        		loginAnimate.play();
        		
	        }
		} catch (JSONException | IOException e) {
			Logger.log(LogType.error(), e.toString());
		}
	}
	
	private void modpackInit() {
		checkModpack();
		leftPaneSetVisible(false);
	}
	
	private void leftPaneSetVisible(Boolean t) {
		menuLeftPane.setVisible(t);
	}
	
	@FXML
	private void loginButton(ActionEvent e) {
		loginLoading.setVisible(true);
		Logger.log(LogType.info(), e.toString());
		String login = loginField.getText();
		String pass = passwordField.getText();
		try {
			if (checklogin(login, pass)) {
				checkToken();
			}
		} catch (JSONException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	

    private boolean checklogin(String user, String pass) throws JSONException, IOException {
        resetColors();
        JSONObject json = jsonReader.readJsonFromUrlPost("https://api.technicworld.pl/index.php?api=login", "login=" + user + "&pass=" + pass);
        System.out.println(json.toString());
        if (!json.isNull("info")) {
            System.out.println("USERNAME: " + json.get("username"));
            settings.setToken(json.getString("success"));
            settings.saveSettings();
            return true;
        }
        System.out.println(json.get("error"));
        if (((String)json.get("function")).equals("login")) {
            loginText.setText("Sprawdź pole z loginem!");
            loginField.setUnFocusColor(Color.RED);
        }
        else if (((String)json.get("function")).equals("password")) {
        	loginText.setText("Sprawdź pole z hasłem!");
            passwordField.setUnFocusColor(Color.RED);
        }
        else if (json.get("msg").toString().contains("zablokowane")) {
        	loginText.setText("Konto zostało zablokowane!\nSpróbuj ponownie za jakiś czas\n lub skontaktuj się z administracją!");
        }
        else {
        	loginText.setText("Podałeś zły login lub hasło!");
            loginField.setUnFocusColor(Color.RED);
            passwordField.setUnFocusColor(Color.RED);
        }
        loginLoading.setVisible(false);
        return false; 
    }
    
    private void resetColors() {
        Color normal = new Color(0.77, 0.77, 0.77, 1.0);
        loginField.setUnFocusColor(normal);
        passwordField.setUnFocusColor(normal);
    }
    
	//</LOGIN --------------------------------------------------------------------------------------------------------------------->
		
		//[+]  \
// 			   | Pozdrawiam dekompilatorów! :)
		//[-]  /
	
	//Browser --------------------------------------------------------------------------------------------------------------------->
	private String currentURL;
	private void browserInit() {
		WebEngine engine = webBrowser.getEngine();

		Runnable thread = new Runnable() {
			public void run() {
				Platform.runLater(new Runnable() {
		            @SuppressWarnings("unchecked")
					@Override public void run() {
		                engine.setUserAgent(settings.getUserAgent());
						webBrowser.setContextMenuEnabled(false);				        
				
						engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
				            if (Worker.State.SCHEDULED.equals(newValue)) {
				            	checkBrowser();
				            	String url = engine.getLocation();
				                if(url.contains("api.technicworld.pl")) {
					                if(!url.contains("&accesstoken=" +settings.getToken())) {
					                    engine.getLoadWorker().cancel();
					    		        loadUrl(url + "&accesstoken=" + settings.getToken());
					                } else {
					                    browserProgress.progressProperty().bind(engine.getLoadWorker().progressProperty());
					                }
					                url = null;
				                } else {  
				                    engine.getLoadWorker().cancel();
				                }
				
				            } 
				            if (Worker.State.SUCCEEDED.equals(newValue)) {
				            	String url = engine.getLocation();
				            	if(!url.contains("api.technicworld.pl")) {
				                    engine.getLoadWorker().cancel();
				    		        loadUrl(currentURL + "&accesstoken=" + settings.getToken());
				            	}
				    			browserProgress.progressProperty().unbind();
				    			url = null;
				    			browserProgress.setVisible(false);
	                            webBrowser.setVisible(true);
			    		        Runtime.getRuntime().gc(); 
				            }
				            if (Worker.State.CANCELLED.equals(newValue)) {
				    			browserProgress.progressProperty().unbind();
				            }
				            if (Worker.State.FAILED.equals(newValue)) {
				    			browserProgress.progressProperty().unbind();
				            }
				        });
						WebViewHyperlinkListener activationCancelingListener  = event -> {
							if(!(event.getURL() == null)) {
						        loadUrl(event.getURL() + "&accesstoken=" + settings.getToken());
							}
							return false;
						};
						WebViews.addHyperlinkListener(webBrowser, activationCancelingListener, HyperlinkEvent.EventType.ACTIVATED);
						loadUrl("https://api.technicworld.pl/?site=lglowna" + "&accesstoken=" + settings.getToken());
		            }
		        });
	        }
	    };
		ExecutorService executor = Executors.newCachedThreadPool();
	    executor.submit(thread);
	}
	
    public void loadUrl(String URL) {
    	WebEngine engine = webBrowser.getEngine();

    	if (!URL.contains("api.technicworld.pl")) {
    		Browser.browseURL(URL);
    	} else {
	        currentURL = URL;
	        engine.setUserAgent(settings.getUserAgent());
	        engine.setJavaScriptEnabled(true);
	        engine.load(URL);
    	}
    }
    
    @SuppressWarnings("unchecked")
	public void checkBrowser() {
        browserProgress.setVisible(true);
        webBrowser.setVisible(false);
        browserProgress.setProgress(-1.0);
    }
    
    //</ Browser ------------------------------------------------------------------------------------------------------------------/>
    //DOWNLOADER ------------------------------------------------------------------------------------------------------------------->

    public Boolean downloadMod(URL url, String path, String modname, File modsPath, File otherModsPath, Boolean isAddon, int modID, int modsSize) {
		if (isAddon) {
			path +=".disable";
		}
		if (!otherModsPath.exists()) {
			Logger.log(LogType.info(), "Tworzę folder: " + otherModsPath.getName());
			boolean result = false;	
		    try{
		       	otherModsPath.mkdirs();
		        result = true;
		    } catch(SecurityException se){
		       	Logger.log(LogType.error(), "Wystąpił błąd przy tworzeniu folderu!\n"+se);
		    }        
		    if(result) {
		    	Logger.log(LogType.info(), "Stworzono folder!");
		    }
		}
		
        javafx.application.Platform.runLater( () -> downloadInfo.setText("Pobieram ("+modID+"/"+modsSize+"): " + modname));

		System.out.println("Pobieram mod: "+modname+" ...");
			 
		download(url, path);
             
		Logger.log(LogType.info(), "Pobrano!");
		
        return true;
	}
	
    public String getModHash(String mod) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        File folder = new File(mod);
        if (!folder.exists()) {
            return "false";
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = getDigest(new FileInputStream(mod), md, 2048);
        return digest.toLowerCase();
    }
    
    public String getDigest(InputStream is, MessageDigest md, int byteArraySize) throws NoSuchAlgorithmException, IOException {
        md.reset();
        final byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest)).toUpperCase();
        return result;
    }
	
	public Boolean downloadConfigs(URL url, File path) throws IOException {
        javafx.application.Platform.runLater( () -> downloadInfo.setText("Pobieram: konfiguracja"));

        if (!path.exists()) {
        	path.mkdirs();
        }
        
        archiveDownload(url, path);
		return true;
	}
	
    private File archiveDownload(URL url, File targetDir) throws IOException {
    	File zip = File.createTempFile("configs", ".zip", targetDir);
    	download(url, zip.toString());
        javafx.application.Platform.runLater( () -> downloadInfo.setText("Rozpakowywuję: konfiguracja"));
        javafx.application.Platform.runLater( () -> downloadProgress.setProgress(-1.0));
    	return pl.B4GU5.Utils.unpackArchive.unpackZip(zip, targetDir);
    }
	
	private Boolean download(URL url, String path) {
        javafx.application.Platform.runLater( () -> downloadProgress.setProgress(0));

		try  {
			 HttpURLConnection httpsConnection = (HttpURLConnection) (url.openConnection());
	         httpsConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	         long completeFileSize = httpsConnection.getContentLength();
	         BufferedInputStream in = new BufferedInputStream(httpsConnection.getInputStream());
	         FileOutputStream fos = new FileOutputStream(path);
	         BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
	         byte[] data = new byte[1024];
	         long downloadedFileSize = 0;
	         int x = 0;
	         while ((x = in.read(data, 0, 1024)) >= 0) {
	             downloadedFileSize += x;
	             double currentProgress = downloadedFileSize / (double)completeFileSize * 100.0;
	             javafx.application.Platform.runLater( () -> downloadProgress.setProgress((currentProgress / 100.0)));

	             bout.write(data, 0, x);
	         }
	         bout.close();
	         in.close();
	         data = null;
	         return true;
	    } catch (FileNotFoundException e) {
		     Logger.log(LogType.error(), "Wystąpił błąd podczas pobierania!\n"+e);
	    } catch (IOException e) {
		     Logger.log(LogType.error(), "Wystąpił błąd podczas pobierania!\n"+e);
	    }
        return true;
	}
}
