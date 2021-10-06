
package com.suntun.itextmyanmar;

import com.suntun.itextmyanmar.render.UnicodeRender;
     
import com.itextpdf.text.pdf.BidiLine;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.languages.LanguageProcessor;

/**
 *
 * @author suntun
 */
public class MyanmarLigaturizer implements LanguageProcessor {
    public MyanmarLigaturizer (){
    }
    
    public String process(String s) {
        UnicodeRender mymrRender = new UnicodeRender();
        return BidiLine.processLTR(mymrRender.render(s), PdfWriter.RUN_DIRECTION_LTR, 0);
    }
    
    public boolean isRTL() {
		return false;
    }
    
}
