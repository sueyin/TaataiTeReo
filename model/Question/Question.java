package application.model.question;

import application.TataiApp;
import application.controller.TestPageController;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is an abstract model of the questions in the Taatai application. It contains methods to recording, marking
 * and calling the corresponding GUI changing methods. It is intended to be extended to questions of specified modes.
 */
public abstract class Question {
	protected final int MAX = 10;

	protected static final String TEMPWAV = TataiApp.getTempDir() + "/temp.wav";
    protected static final String RECOUT = TataiApp.getTempDir() + "/recout.mlf";
	protected static final String RECORD_CMD = "ffmpeg -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 " + TEMPWAV+ " &> /dev/null";
	protected static final String HTK_CMD = "HVite -H ./HTK/MaoriNumbers/HMMs/hmm15/macros " +
			"-H ./HTK/MaoriNumbers/HMMs/hmm15/hmmdefs -C ./HTK/MaoriNumbers/user/configLR  " +
			"-w ./HTK/MaoriNumbers/user/wordNetworkNum -o SWT -l '*' " +
			"-i " + RECOUT + " -p 0.0 -s 5.0  ./HTK/MaoriNumbers/user/dictionaryD " +
			"./HTK/MaoriNumbers/user/tiedList " + TEMPWAV+ " &> /dev/null";

	//protected static final String HTK_CMD  = "HVite -H ~/Documents/HTK/MaoriNumers/HMMs/hmm15/macros -H ~/Documents/HTK/MaoriNumers/HMMs/hmm15/hmmdefs -C user/configLR  -w ~/Documents/HTK/MaoriNumers/user/wordNetworkNum -o SWT -l '*' -i "+RECOUT+" -p 0.0 -s 5.0  ~/Documents/HTK/MaoriNumers/user/dictionaryD user/tiedList "+TEMPWAV;
	protected static final String PLAY_CMD = "aplay " + TEMPWAV + " &> /dev/null";

	protected boolean _result;

	protected String _answer;
	protected String _question;
	protected String _read;

	protected TestPageController _page;
	protected Task _recordTask;
	protected Task _compareTask;


	public Question(String question, String answer, TestPageController page){
		Answer a = new Answer(Integer.parseInt(answer));
		_answer = a.getAnswer();
		_question = question;
		_page = page;
	}

	//==================================================================================================================

	/*
		Functionality
	 */

	/**
	 * Read mlx file produced by HTK and get what were recognized.
	 */
	private String computeRead() {
	
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
		//Find what the user said if anything is recognized. Otherwise return empty.
		if (read.contains("sil")){
			return read.split("sil")[1].trim();
		}else{
			return "";
		}
		
		//return "dd";
	}


	/**
	 * Record by bash command, create a temporary wav file in the hidden root folder. Call method from the TestPageController
	 * field to change GUI respectively. (record -> recording)
	 */
	public void test(){

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

		//When the Record process is completed, update the status of the question and switch GUI to show progressing.
		//Invoke the next Compare step
		_recordTask.setOnSucceeded(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				//TODO change record btn text to "Processing"
				compare();
			}
		});

		new Thread(_recordTask).start();
		
		//compare();
	}



	/**
	 * Use HTK command to analyse the user record, read the result to determine what the user said and whether it is
	 * the correct answer.
	 */
	private void compare(){

		//NOTE: The following commented code corresponds to the voice recognition functionality, provided by the lecturer.
		//		It is currently unavailable to public. The result of each attempt is instead randomly generated.
		/*
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
				//Read the result from HTK command and get what the user said.
				_read = computeRead();
				//Determine whether the user said the correct answer.
				if(_read.equals(_answer)){
					_result = true;
				}else{
					_result = false;
				}
				return null;
			}
		};
		//When the Compare process finished, update question status and GUI.
		_compareTask.setOnSucceeded(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				updateGUI();
			}
		});
		new Thread(_compareTask).start();
		*/

		
		//Randomly set the result to be true or false.
		int i = (int)(Math.random()*10);
		if (i < 5) {
			_result = false;
		}else{
			_result = true;
		}
		updateGUI();
		
	}


	/**
	 * Called by the Template method compare(). Set the GUI according to the result and the question type, question
	 * mode correspondingly.
 	 */
	protected abstract void updateGUI();

	//==================================================================================================================

	/*
		Getters
	 */

    public boolean getResult(){
        return _result;
    }

    public String getQuestion(){
    	return _question;
	}

	public String getAnswer() {
		return _answer;
	}

	public String getRead(){
		return _read;
	}


	/*
		Management
	 */

	/**
	 * Delete the previous temp wav file if there is one.
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
