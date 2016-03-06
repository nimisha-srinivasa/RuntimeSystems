package com.ucsb.cs263.tunein.utils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class JsonDateSerializer extends StdSerializer<Date> {

	public JsonDateSerializer() {
	    super(Date.class, true);
	}

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	        throws IOException, JsonGenerationException {
	    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
	    String format = formatter.format(value);
	    jgen.writeString(format);
	}

}