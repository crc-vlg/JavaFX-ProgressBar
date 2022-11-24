package crc.vlg.javafxprogressbar;

import javafx.concurrent.Task;

public class ProgressWorker extends Task<Void> {

    @Override
    public Void call() {
        updateMessage("Начало");
        updateProgress(0, 100);
        for (int i = 0; i <= 100; i++) {
            if (this.isCancelled())
                updateMessage("Отменено");

            updateMessage(Integer.toString(i));

            try {
                Thread.sleep(200);
                updateProgress(i, 100);
            } catch (Exception e) {
                if (isCancelled()) {
                    updateProgress(100, 100);
                    updateMessage("Отменено");
                    return null;
                }
            }
        }
        updateMessage("Готово");
        updateProgress(100, 100);
        return null;
    }
}
