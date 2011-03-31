/*
  Función que permite rellenar las posibles opciones de un campo de tipo select.
  select -> campo de tipo select al que queremos asociar las posibles opciones.
  valores-> conjunto de valores de tipo (etiqueta, valor) que serán las opciones del campo select.
*/
function refillSelect(select, valores) {
    select.options.length = 0;
    for (var i = 0; i < valores.length; i++) {
        //var opcio = new Option(texto, valor, opcion_por_defecto,  opcion_seleccionada);
        //var opcio = new Option(valores[i].etiqueta, valores[i].valor, false,  valores[i].defecto);
        var opcio = new Option(valores[i].etiqueta, valores[i].valor, false,  false);

        try {
            select.add(opcio, null);
        } catch(E) {
            select.add(opcio);
        }
    }
}

