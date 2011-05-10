package org.ibit.rol.sac.extractor;

import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

/**
 * TODO documentar
 * TODO Definir mimes.
 */
public class OpenOfficeExtractor implements Extractor {

    public String[] getSupportedMimes() {
        return new String[0];
    }

    public String extractText(InputStream in) throws IOException {
        String text = null;

        ZipInputStream zis = new ZipInputStream(in);
        ZipEntry entry = zis.getNextEntry();
        while (entry != null) {
            if (entry.getName().equals("content.xml")) {
                text = new XMLExtractor().extractText(zis);
                break;
            }
            zis.closeEntry();
            entry = zis.getNextEntry();
        }

        zis.close();
        return text;
    }
}
