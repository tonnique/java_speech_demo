
public class SpeechDemo {

	public static void main(String[] args) throws Exception{
	    SpeechUtils su = new SpeechUtils();
	    
	    su.init("kevin16"); // high quality
	    su.doSpeak("Hello world. This is Demo Speech application");
	    su.terminate();
	}
}
