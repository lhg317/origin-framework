import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writer(new HTMLCharacterEscapes());
//		om.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
//		final int[] asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
//		
//		
//		om.getFactory().setCharacterEscapes(new CharacterEscapes(){
//			@Override
//			public int[] getEscapeCodesForAscii() {
//	            asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
//	            asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
//	            asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
//	            asciiEscapes['"'] = CharacterEscapes.ESCAPE_CUSTOM;
//	            asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
//				return asciiEscapes;
//			}
//
//			@Override
//			public SerializableString getEscapeSequence(int arg0) {
//				return new SerializedString("<"+arg0 + ">");
//			}});
		Map valueMap = new HashMap();
		valueMap.put("a", "&刘海刚");
		om.writeValue(System.out, valueMap);
	}
	
	public static class HTMLCharacterEscapes extends CharacterEscapes {

		private static final long serialVersionUID = -8824255529875193034L;
		private final int[] asciiEscapes;

        public HTMLCharacterEscapes() {
            // start with set of characters known to require escaping (double-quote, backslash etc)
            asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
            // and force escaping of a few others:
            asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['"'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
        }


        @Override
        public int[] getEscapeCodesForAscii() {
            return asciiEscapes;
        }

        // and this for others; we don't need anything special here
        @Override
        public SerializableString getEscapeSequence(int ch) {
            return new SerializedString(Character.toString((char) ch));

        }
    }
}
