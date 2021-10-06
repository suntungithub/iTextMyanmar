
package com.suntun.itextmyanmar.table;

/**
 *
 * @author suntun
 */
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;

/** This class created is to display myanmar unicode in table
 * it is extended from PdfPTable
 * @author suntun
 */
public class PdfPTableMyanmar extends PdfPTable {

    public PdfPTableMyanmar() {}
    public PdfPTableMyanmar(float[] relativeWidths) { super(relativeWidths);}
    public PdfPTableMyanmar(int numColumns) { super(numColumns);}
    public PdfPTableMyanmar(PdfPTable table) { super(table);}
    
    public void addCell(String text, Font font) {
        addCell(new Phrase(text, font));
    }
}
