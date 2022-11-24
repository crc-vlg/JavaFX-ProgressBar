package crc.vlg.javafxprogressbar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MainController {

    private ProgressWorker progressWorker;
    @FXML
    private Label label1;
    @FXML
    private ProgressBar progressBar1;
    @FXML
    private Button button1;

    @FXML
    protected void onButtonClick() {

        if (progressWorker != null && progressWorker.isRunning())
            progressWorker.cancel();

        progressWorker = new ProgressWorker();
        Thread thread = new Thread(progressWorker);
        thread.setDaemon(true);
        thread.start();

        progressBar1.progressProperty().unbind();
        label1.textProperty().unbind();
        button1.disableProperty().unbind();

        progressBar1.progressProperty().bind(progressWorker.progressProperty());
        label1.textProperty().bind(progressWorker.messageProperty());
        button1.disableProperty().bind(progressWorker.runningProperty());
    }

}