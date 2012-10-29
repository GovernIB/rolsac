package es.caib.rolsac.api.v2.general;

import java.util.HashSet;
import java.util.Set;

public class CSVUtil {

    private CSVUtil() {
    }
    
    public static Set<Long> csv2LongSet(String csv) throws NumberFormatException {
        Set<Long> valuesSet = new HashSet<Long>();
        if (csv.contains(",")) {
            String[] values = csv.replaceAll(",$", "").split(",");
            for (String value : values) {
                valuesSet.add(Long.parseLong(value.trim()));
            }
        }
        return valuesSet;
    }
    
}
