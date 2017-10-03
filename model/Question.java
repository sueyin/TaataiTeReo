package application.model;

import application.MainApp;
import application.TestPageController;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Question {

	private final int MAX = 10;

	private static final String TEMPWAV = MainApp.getPath() + "/temp.wav";
    private static final String RECOUT = MainApp.getPath() + "/recout.mlf";

	private static final String RECORD_CMD = "ffmpeg -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 " + TEMPWAV + " &> /dev/null";
	private static final String HTK_CMD = "HVite -H ~/Documents/HTK/MaoriNumbers/HMMs/hmm15/macros " +
			"-H ~/Documents/HTK/MaoriNumbers/HMMs/hmm15/hmmdefs -C ~/Documents/HTK/MaoriNumbers/user/configLR  " +
			"-w ~/Documents/HTK/MaoriNumbers/user/wordNetworkNum -o SWT -l '*' " +
			"-i " + RECOUT + " -p 0.0 -s 5.0  ~/Documents/HTK/MaoriNumbers/user/dictionaryD " +
			"~/Documents/HTK/MaoriNumbers/user/tiedList " + TEMPWAV + " &> /dev/null";


	private static final String PLAY_CMD = "aplay " + TEMPWAV + " &> /dev/null";

	private boolean _result;
	private boolean _finished;
	private boolean _tested;
	private int _number;

	private String _answer;
	private String _read;

	private TestPageController _page;
	private Task _recordTask;
	private Task _compareTask;

	public Question(int n, TestPageController page){
		_number = n;
		_tested = false;
		_finished = false;
		_page = page;
	}

	/**
	 * Record by bash command, create a temporary wav file in the hidden root folder. Call method from the TestPageController
	 * field to change GUI respectively.
	 */
	public void test(){
		//Change GUI to the pre-record status.
		_page.beforeRecordGUI();

		//Create a Task to implement Record in the background thread
		_recordTask = new Task<Void>() {
			@Override public Void call() throws IOException {
				try {
					deleteWAV();	//Overwrite the wav file if there is one.
					ProcessBuilder pb = new ProcessBuilder("bash","-c", RECORD_CMD);
					pb.inheritIO();
					Process p  = pb.start();
					p.waitFor();
					} catch(InterruptedException e){
						e.printStackTrace();
					}
				return null;
			}
		};
		//When the Record process is completed, update the status of the Question and switch GUI to show progressing.
		//Invoke the next Compare step
		_recordTask.setOnSucceeded(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (_tested){
					_finished = true;
				}
				_page.afterRecordingGUI();
				compare();
			}
		});

		_page.getBar().progressProperty().bind(_recordTask.progressProperty());
		new Thread(_recordTask).start();
	}



	/**
	 * Use HTK command to analyse the user record, read the result to determine what the user said and whether it is
	 * the correct answer.
	 */
	private void compare(){
		//Create anthoer Task to implement the HTK command in background.
        _compareTask = new Task<Void>() {
			@Override public Void call() throws IOException {
				try {
					ProcessBuilder pb = new ProcessBuilder("bash", "-c", HTK_CMD);
					pb.inheritIO();
					Process p = pb.start();
					p.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Create an Answer object to generate the correct answer of the Question.
				Answer answer = new Answer(_number);
				_answer = answer.getAnswer();
				//Read the result from HTK command and get what the user said.
				_read = computeRead();
				//Determine whether the user said the correct answer.
				if (_read.equals("")){
					_result = false;
					//TODO change a label or something to indicate the user nothing has been recorded
				}else if(_read.equals(_answer)){
					_result = true;
				}else{
					_result = false;
				}
				return null;
			}
        };
		//When the Compare process finished, update Question status and GUI.
		_compareTask.setOnSucceeded(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (!_finished){
					if (_result){
						_finished = true;
					}else {
						_finished = false;
						_tested = true;
					}
				}
				_page.afterCompareGUI();
			}
		});

		_page.getBar().progressProperty().bind(_compareTask.progressProperty());
		new Thread(_compareTask).start();
	}


	/**
	 * Read mlx file produced by HTK and get what were recognized.
	 */
	private String computeRead(){
		String read = null;
		//Read the mlx file produced by HTK command.
		File recout = new File(RECOUT);
		try {
			//Read the file into a String
			Scanner sc = new Scanner(recout);
			while (sc.hasNextLine()) {
				read = read + " " + sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//If something is recognized, the String should contain "sil"
		//Find what the user said if anything is recognized. Otherwise return null.
		if (read.contains("sil")){
			return read.split("sil")[1].trim();
		}else{
			return "";
		}
	}


    public boolean getResult(){
        return _result;
    }

	/**
	 * TODO When the user said the number wrong, should use this method to show the correct answer (optional??)
	 * @return a String indicating the correct answer.
	 */
	public String getAnswer() {
		return _answer;
	}


	public String getRead(){
		return _read;
	}


	/**
	 * Return a boolean to show whether the Question is finished.
	 * To be finished, the user either said the number right in the first try or used up the second chance.
	 * If the user said the number wrong the first time, the Question is not finished.
	 * @return	a boolean value indicating whether the Question is finished.
	 */
	public boolean isFinished(){
        return _finished;
    }


	/**
	 * Delete the temp wav file if there is one.
	 */
	private void deleteWAV(){
    	File wav = new File(TEMPWAV);
    	if (wav.exists()){
    		wav.delete();
		}
    }


	/**
	 * This method cancels the running task if there is one.
	 */
	public void cancel(){
		if ((_recordTask != null)&&(_recordTask.isRunning())){
			_recordTask.cancel();
		}
		if ((_compareTask != null)&&(_compareTask.isRunning())){
			_compareTask.cancel();
		}
	}
}
