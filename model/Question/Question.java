package application.model.Question;

import application.TataiApp;
import application.controller.TestPageController;
import application.model.Answer;
import javafx.concurrent.Task;

import java.io.File;
public abstract class Question {

	protected final int MAX = 10;

	protected static final String TEMPWAV = TataiApp.getTempDir() + "/temp.wav";
    protected static final String RECOUT = TataiApp.getTempDir() + "/recout.mlf";

	protected static final String RECORD_CMD = "ffmpeg -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 " + TEMPWAV + " &> /dev/null";
	protected static final String HTK_CMD = "HVite -H ~/Documents/HTK/MaoriNumbers/HMMs/hmm15/macros " +
			"-H ~/Documents/HTK/MaoriNumbers/HMMs/hmm15/hmmdefs -C ~/Documents/HTK/MaoriNumbers/user/configLR  " +
			"-w ~/Documents/HTK/MaoriNumbers/user/wordNetworkNum -o SWT -l '*' " +
			"-i " + RECOUT + " -p 0.0 -s 5.0  ~/Documents/HTK/MaoriNumbers/user/dictionaryD " +
			"~/Documents/HTK/MaoriNumbers/user/tiedList " + TEMPWAV + " &> /dev/null";
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

	/**
	 * Record by bash command, create a temporary wav file in the hidden root folder. Call method from the TestPageController
	 * field to change GUI respectively. (record -> recording)
	 */
	public void test(){
		//deleteWAV();
		System.out.println("Deleted last recording");
		System.out.println("Recording");
		System.out.println("Recorded");
		compare();
	}


	/**
	 * Use HTK command to analyse the user record, read the result to determine what the user said and whether it is
	 * the correct answer.
	 */
	private void compare(){
		System.out.println("Comparing");
		System.out.println("Compared. Result set to false");
		_result = false;
		updateGUI();
		//TODO compare完了之后在done()里叫 updateGUI()
	}


	protected abstract void updateGUI();


	/**
	 * Read mlx file produced by HTK and get what were recognized.
	 */
	private String computeRead() {
		/*
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
		*/
		return "dd";
	}


    public boolean getResult(){
        return _result;
    }


    public String getQuestion(){
    	return _question;
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
