package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

public class MyController implements Initializable {
    private boolean devMode;
    private TextArea textArea;
    private WebEngine engine;
    @FXML
    private WebView webView;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea = new TextArea();
        engine = webView.getEngine();
        engine.load("http://www.baidu.com");
        System.out.println(engine.getUserAgent());
        
         engine.setOnAlert(new EventHandler<WebEvent<String>>() {  
             @Override  
             public void handle(WebEvent<String> event) {  
                 System.out.println("this is event"+event);  
             }  
         }); 
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.F12) {
            this.devMode = !devMode;
            if (devMode) {
                this.borderPane.setBottom(textArea);
            } else {
                this.borderPane.setBottom(null);
            }
//            String js = "document.getElementById('kw').value = 'hello';document.getElementById('su').click();";
            String js = "window.alert('hello');";
            engine.executeScript(js);
        }
    }
}