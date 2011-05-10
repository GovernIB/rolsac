package org.ibit.rol.sac.extractor;

import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class extracts text from pdf files by using the PdfBox library.
 * @author Ryan Ackley
 * @author Antoni Reus
 */
public final class PDFExtractor implements Extractor {

    /**
     * Constructor.
     */
    public PDFExtractor() {
    }

    /**
     * Tipos mimes soportados por el Extractor.
     * @return devuelve los tipos de PDF.
     */
    public String[] getSupportedMimes() {
        return new String[]{"application/pdf"};
    }

    /**
     * Extracts text from a pdf document.
     * @param in The InputStream representing the pdf file.
     * @return The text in the file
     * @throws IOException Si se produce algun error leyendo el documento.
     */
    public String extractText(final InputStream in) throws IOException {
        final PDFParser parser = new PDFParser(in);
        parser.parse();
        final PDDocument document = parser.getPDDocument();
        String s = null;
        try {
            final PDFTextStripper stripper = new PDFTextStripper();
            s = stripper.getText(document);
        } finally {
            document.close();
        }

        return s;
    }

}
