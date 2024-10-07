/**
 * Copyright (C) 2012 alanhay <alanhay99@hotmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.certait.htmlexporter.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class PdfExporter {

	protected List<String> fontPaths;

	public void registerFontPath(String path) {
		getFontPaths().add(path);
	}

	public List<String> getFontPaths() {
		if(fontPaths == null) fontPaths = new ArrayList<>();
		return fontPaths;
	}

	public byte[] exportHtml(String html) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		exportHtml(html, out);

		return out.toByteArray();
	}

	public void exportHtml(String html, File file) throws Exception {
		exportHtml(html, new FileOutputStream(file));
	}

	private void exportHtml(String html, OutputStream out) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		String fixedHtml = fixCharEntities(html);
		Document doc = builder.parse(new ByteArrayInputStream(fixedHtml.getBytes(StandardCharsets.UTF_8)));

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(doc, null);

		for(String path : getFontPaths()) {
			renderer.getFontResolver().addFont(path, true);
		}

		renderer.layout();
		renderer.createPDF(out);
		out.flush();
		out.close();
	}

	private static final Map<String, String> charToNumericEntities;

	static {
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("&nbsp;", "&#160;");
		tempMap.put("&iexcl;", "&#161;");
		tempMap.put("&cent;", "&#162;");
		tempMap.put("&pound;", "&#163;");
		tempMap.put("&curren;", "&#164;");
		tempMap.put("&yen;", "&#165;");
		tempMap.put("&brvbar;", "&#166;");
		tempMap.put("&sect;", "&#167;");
		tempMap.put("&uml;", "&#168;");
		tempMap.put("&copy;", "&#169;");
		tempMap.put("&ordf;", "&#170;");
		tempMap.put("&laquo;", "&#171;");
		tempMap.put("&not;", "&#172;");
		tempMap.put("&shy;", "&#173;");
		tempMap.put("&reg;", "&#174;");
		tempMap.put("&macr;", "&#175;");
		tempMap.put("&deg;", "&#176;");
		tempMap.put("&plusmn;", "&#177;");
		tempMap.put("&sup2;", "&#178;");
		tempMap.put("&sup3;", "&#179;");
		tempMap.put("&acute;", "&#180;");
		tempMap.put("&micro;", "&#181;");
		tempMap.put("&para;", "&#182;");
		tempMap.put("&middot;", "&#183;");
		tempMap.put("&cedil;", "&#184;");
		tempMap.put("&sup1;", "&#185;");
		tempMap.put("&ordm;", "&#186;");
		tempMap.put("&raquo;", "&#187;");
		tempMap.put("&frac14;", "&#188;");
		tempMap.put("&frac12;", "&#189;");
		tempMap.put("&frac34;", "&#190;");
		tempMap.put("&iquest;", "&#191;");
		tempMap.put("&Agrave;", "&#192;");
		tempMap.put("&Aacute;", "&#193;");
		tempMap.put("&Acirc;", "&#194;");
		tempMap.put("&Atilde;", "&#195;");
		tempMap.put("&Auml;", "&#196;");
		tempMap.put("&Aring;", "&#197;");
		tempMap.put("&AElig;", "&#198;");
		tempMap.put("&Ccedil;", "&#199;");
		tempMap.put("&Egrave;", "&#200;");
		tempMap.put("&Eacute;", "&#201;");
		tempMap.put("&Ecirc;", "&#202;");
		tempMap.put("&Euml;", "&#203;");
		tempMap.put("&Igrave;", "&#204;");
		tempMap.put("&Iacute;", "&#205;");
		tempMap.put("&Icirc;", "&#206;");
		tempMap.put("&Iuml;", "&#207;");
		tempMap.put("&ETH;", "&#208;");
		tempMap.put("&Ntilde;", "&#209;");
		tempMap.put("&Ograve;", "&#210;");
		tempMap.put("&Oacute;", "&#211;");
		tempMap.put("&Ocirc;", "&#212;");
		tempMap.put("&Otilde;", "&#213;");
		tempMap.put("&Ouml;", "&#214;");
		tempMap.put("&times;", "&#215;");
		tempMap.put("&Oslash;", "&#216;");
		tempMap.put("&Ugrave;", "&#217;");
		tempMap.put("&Uacute;", "&#218;");
		tempMap.put("&Ucirc;", "&#219;");
		tempMap.put("&Uuml;", "&#220;");
		tempMap.put("&Yacute;", "&#221;");
		tempMap.put("&THORN;", "&#222;");
		tempMap.put("&szlig;", "&#223;");
		tempMap.put("&agrave;", "&#224;");
		tempMap.put("&aacute;", "&#225;");
		tempMap.put("&acirc;", "&#226;");
		tempMap.put("&atilde;", "&#227;");
		tempMap.put("&auml;", "&#228;");
		tempMap.put("&aring;", "&#229;");
		tempMap.put("&aelig;", "&#230;");
		tempMap.put("&ccedil;", "&#231;");
		tempMap.put("&egrave;", "&#232;");
		tempMap.put("&eacute;", "&#233;");
		tempMap.put("&ecirc;", "&#234;");
		tempMap.put("&euml;", "&#235;");
		tempMap.put("&igrave;", "&#236;");
		tempMap.put("&iacute;", "&#237;");
		tempMap.put("&icirc;", "&#238;");
		tempMap.put("&iuml;", "&#239;");
		tempMap.put("&eth;", "&#240;");
		tempMap.put("&ntilde;", "&#241;");
		tempMap.put("&ograve;", "&#242;");
		tempMap.put("&oacute;", "&#243;");
		tempMap.put("&ocirc;", "&#244;");
		tempMap.put("&otilde;", "&#245;");
		tempMap.put("&ouml;", "&#246;");
		tempMap.put("&divide;", "&#247;");
		tempMap.put("&oslash;", "&#248;");
		tempMap.put("&ugrave;", "&#249;");
		tempMap.put("&uacute;", "&#250;");
		tempMap.put("&ucirc;", "&#251;");
		tempMap.put("&uuml;", "&#252;");
		tempMap.put("&yacute;", "&#253;");
		tempMap.put("&thorn;", "&#254;");
		tempMap.put("&yuml;", "&#255;");
		charToNumericEntities = Collections.unmodifiableMap(tempMap);
	}

	/**
	 * Converts character entities to numeric entities.
	 *
	 * @param html
	 * @return
	 */
	private String fixCharEntities(String html) {
		for(String key:charToNumericEntities.keySet()) {
			html = html.replace(key, charToNumericEntities.get(key));
		}
		return html;
	}

}