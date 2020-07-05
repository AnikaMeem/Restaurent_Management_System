/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.paint.Color.BLACK;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sound.midi.Receiver;

/**
 *
 * @author Lenovo
 */
public class clientMain extends Application {

    Scene signinScene, messageScene;
    Stage currentStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        currentStage = primaryStage;
        primaryStage.setTitle("Sign In");

        Label nicknameLabel = new Label("Nickname");
        //nicknameLabel.setAlignment(Pos.CENTER);

        TextArea receivedMessage = new TextArea("");

        TextField nicknameText = new TextField();

        Button signinBtn = new Button();
        signinBtn.setText("Sign In");
        signinBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nicknameText.getText() == null) {
                    return;
                }

                if (nicknameManager.getInstance().signin(nicknameText.getText())) {
                    currentStage.setTitle("Send Message");
                    currentStage.setScene(messageScene);

                    Thread receiverThread = new Thread(new messageReceiver(receivedMessage));
                    receiverThread.start();
                    
                    Thread fileThread = new Thread(new fileReceiver());
                    fileThread.start();

                    Thread KeepAliveThread = new Thread(new KeepAlive());
                    KeepAliveThread.start();

                }
            }
        });
        
        
        //ImageView img=new ImageView(new Image(client.clientMain.class.getResourceAsStream("image/chat.png")));
        //Image img=new Image(clientMain.class.getResourceAsStream("image/chat.png"));
        //BackgroundSize bSize = new BackgroundSize(0, 0, true, true, true, true);
        //BackgroundImage backImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize);
        
        /*String musicFile = "sound.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();*/
        
        /*AudioClip messageTone;
        messageTone = new AudioClip(this.getClass().getResource("sound/sound.mp3").toExternalForm());
        messageTone.play();*/
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Send File");
        //fileChooser.showOpenDialog(currentStage);
        
        HBox hbChild = new HBox();
        hbChild.getChildren().addAll(nicknameLabel, nicknameText);
        hbChild.setSpacing(10);
        hbChild.setAlignment(Pos.CENTER);
        
        HBox hbParent = new HBox();
        hbParent.getChildren().addAll(hbChild, signinBtn);
        hbParent.setSpacing(10);
        hbParent.setAlignment(Pos.CENTER);

        StackPane signinPane = new StackPane();
        signinPane.getChildren().add(hbParent);
        //signinPane.setBackground(new Background(backImg));

        signinScene = new Scene(signinPane, 500, 500);

        TextField receiver = new TextField();
        Label receiverlbl = new Label("Receiver:");

        TextField message = new TextField();
        Label messagelbl = new Label("Message:");
        
        /*TextField sendFilePath = new TextField();
        Label filePathLabel = new Label("Enter File Path: ");*/

        Button sendBtn = new Button();
        sendBtn.setText("Send Message");
        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                receivedMessage.appendText("you:"+message.getText()+"\n");
                System.out.println( receivedMessage.getText());
                messageSender.getInstance().sendOneMessage(receiver.getText(), message.getText());
                //mediaPlayer.play();
                //primaryStage.setScene(messageScene);

            }
        });
        
        Button sendFileBtn = new Button();
        sendFileBtn.setText("Send File");
        Desktop desktop = Desktop.getDesktop();
        //FileChooser filechooser = new FileChooser();
        sendFileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //filechooser.getInitialDirectory();
                File file = fileChooser.showOpenDialog(currentStage);
                if (file != null){
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        Logger.getLogger(clientMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        fileSender.getInstance().sendOneFile(receiver.getText(), file.getAbsolutePath());
                }
                //fileSender.getInstance().sendOneFile(receiver.getText(),filePathLabel.getText());
            }
        });

        ListView<String> nicknameList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(nicknameManager.getInstance().getNicknameList());
        nicknameList.setItems(items);

        Button refreshBtn = new Button();
        refreshBtn.setText("refresh");
        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                items.clear();
                items.addAll(nicknameManager.getInstance().getNicknameList());
                nicknameList.setItems(items);
            }
        });

        HBox receiverBox = new HBox();
        receiverBox.getChildren().addAll(receiverlbl, receiver);

        HBox messageBox = new HBox();
        messageBox.getChildren().addAll(messagelbl, message);
        
        /*HBox filePathBox = new HBox();
        filePathBox.getChildren().addAll(filePathLabel, sendFilePath);*/

        HBox buttons = new HBox();
        buttons.getChildren().addAll(sendBtn, sendFileBtn);
        buttons.setSpacing(10);

        VBox sendMessageBox = new VBox();
        sendMessageBox.getChildren().addAll(receiverBox, messageBox, buttons, receivedMessage);
        sendMessageBox.setSpacing(10);

        HBox all = new HBox();
        all.getChildren().addAll(sendMessageBox, nicknameList, refreshBtn);
        all.setSpacing(10);

        StackPane messagePane = new StackPane();
        messagePane.getChildren().add(all);
        //messagePane.getChildren().add(img);

        messageScene = new Scene(messagePane, 800, 500);

        primaryStage.setScene(signinScene);
        primaryStage.show();
    }

}