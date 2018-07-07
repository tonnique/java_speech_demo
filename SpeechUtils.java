import java.beans.PropertyVetoException;
import java.util.Locale;

import javax.speech.*;
import javax.speech.synthesis.*;


public class SpeechUtils {
	
	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;
	
	public SpeechUtils() {}

	public void init(String voiceName) throws EngineException, AudioException, EngineStateError, PropertyVetoException 
	{
		if (desc == null) {		      
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			
			desc = new SynthesizerModeDesc(Locale.US);
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			
			synthesizer = Central.createSynthesizer(desc);
		    synthesizer.allocate();
		    synthesizer.resume();
		    SynthesizerModeDesc smd = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
		    
		    Voice[] voices = smd.getVoices();
		    //Voice voice = null;
		    for (Voice theVoice : voices) {
		    	if(theVoice.getName().equals(voiceName)) {
		    		this.voice = theVoice;
		    		break;
		    	}
		    }		    
		    synthesizer.getSynthesizerProperties().setVoice(voice);
		}		
	}
	
	public void terminate() throws EngineException, EngineStateError {
		synthesizer.deallocate();
	}
	
	 public void doSpeak(String speakText) throws EngineException, AudioException, IllegalArgumentException, InterruptedException 
	 {
		 synthesizer.speakPlainText(speakText, null);
		 synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
	 }
}
