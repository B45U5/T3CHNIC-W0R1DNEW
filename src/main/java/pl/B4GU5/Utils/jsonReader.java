package pl.B4GU5.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonReader {
    public static JSONObject readJsonFromUrlPost(final String url, final String urlParameters) throws IOException, JSONException {
        byte[] postData = urlParameters.getBytes();
        HttpURLConnection con = null;
        try {
            final URL myurl = new URL(url);
            (con = (HttpURLConnection)myurl.openConnection()).setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", settings.getUserAgent());
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            Throwable t = null;
            try {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                try {
                    wr.write(postData);
                    postData = null;
                }
                finally {
                    if (wr != null) {
                        wr.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    Throwable exception = null;
                    t = exception;
                }
                else {
                    Throwable exception = null;
                    if (t != exception) {
                        t.addSuppressed(exception);
                    }
                }
            }
            Throwable t2 = null;
            StringBuilder content;
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                try {
                    content = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }
            finally {
                if (t2 == null) {
                    Throwable exception2 = null;
                    t2 = exception2;
                }
                else {
                    Throwable exception2 = null;
                    if (t2 != exception2) {
                        t2.addSuppressed(exception2);
                    }
                }
            }
            JSONObject json = new JSONObject(content.toString());
            return json;
        }
        finally {
            con.disconnect();
        }
    }
    
    public static JSONObject readJsonFromUrl(final String url) throws IOException, JSONException {
        HttpURLConnection con = null;

        try {
            final URL myurl = new URL(url);
            (con = (HttpURLConnection)myurl.openConnection()).setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", settings.getUserAgent());
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            Throwable t = null;
            StringBuilder content;
            try {
                final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                try {
                    content = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    Throwable exception = null;
                    t = exception;
                }
                else {
                    Throwable exception = null;
                    if (t != exception) {
                        t.addSuppressed(exception);
                    }
                }
            }
            JSONObject json = new JSONObject(content.toString());
            return json;
        }
        finally {
            con.disconnect();
        }
    }
}
