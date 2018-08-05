package com.spotify;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.testng.annotations.*;
import org.testng.Assert;

public class CreateNewPlaylistTest {
	Path currentRelativePath = Paths.get("");
	String s_path = currentRelativePath.toAbsolutePath().toString();
    Runtime rt = Runtime.getRuntime();
	
  @BeforeTest
  public void setup() throws IOException{
	    Process pr = rt.exec("open -a Spotify");
  }
  
  @Test
  public void CreateNewPlaylist() throws FindFailed, InterruptedException, IOException{
	    Properties prop = new Properties();
	    InputStream input = null;
	    Screen s = new Screen();
	    input = new FileInputStream(s_path + "/src/test/java/config.properties");
	    prop.load(input);
	    String username = prop.getProperty("username");
	    String password = prop.getProperty("password");
	    		
	    Pattern login_button = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_button.png");	    
	    s.doubleClick(s.wait(login_button, 10));
	    
	    Pattern email_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/email_field.png");	    
	    s.type(username);
	    Pattern password_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/password_field.png");	    
	    s.doubleClick(s.wait(password_field, 10));
	    s.type(password);

	    Pattern login_button_green = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/login_button_green.png");	    
	    s.doubleClick(s.wait(login_button_green, 10));
	    
	    Pattern search_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/search_field.png");	    
	    s.wait(search_field, 10);
	    Assert.assertTrue(search_field !=null);

	    Pattern add_new_playlist_button = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/add_new_playlist_button.png");	    
	    s.click(s.wait(add_new_playlist_button, 10));
	    
	    Pattern description_field = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/description_field.png");	    
	    s.doubleClick(s.wait(description_field, 10));
	    s.type("This is a test");
	    
	    Pattern create_new_playlist_button_green = new Pattern(s_path + "/src/test/java/assets_spotify.sikuli/create_new_playlist_button_green.png");	    
	    s.doubleClick(s.wait(create_new_playlist_button_green, 10));
	    Thread.sleep(3000);

  }
  
  @AfterTest
  public void tearDown() throws IOException{
	    Process close = rt.exec("killall Spotify");
  }

}