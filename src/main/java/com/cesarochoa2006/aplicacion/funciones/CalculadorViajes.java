package com.cesarochoa2006.aplicacion.funciones;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesarochoa2006.aplicacion.param.Archivo;
import com.cesarochoa2006.aplicacion.param.PeticionDTO;
import com.cesarochoa2006.aplicacion.tipos.ExcepcionAplicacion;
import com.cesarochoa2006.aplicacion.tipos.ICalculador;
import com.cesarochoa2006.aplicacion.tipos.IProveedor;

/**
 * Clase que contiene los métodos de aplicación necesarios para calcular los
 * viajes de Wilson.
 * 
 * Es un calculador porque realiza el cálculo de los viajes
 * 
 * Es un proveedor porque provee la información necesaria para el cálculo de los
 * viajes
 * 
 * Si la complejidad del programa crece se pueden separar las responsabilidades
 * extrayendo cada método a clases aparte
 * 
 * @author cesar
 *
 */
public class CalculadorViajes implements IProveedor, ICalculador {
	private static final Logger LOG = LoggerFactory.getLogger(CalculadorViajes.class);

	/**
	 * Método funcional de la operación del calculador de viajes de wilson
	 * @param archivo
	 * @return
	 * @throws ExcepcionAplicacion 
	 */
	public String ejecutar(Archivo archivo) throws ExcepcionAplicacion {
		try {
		String data = archivo.getObjeto();
		// Transformo, proveo y calculo
		List<String> calculosViajes = calcular(recibir(data));
		// Proveo y transformo nuevamente
		String resultado = enviar(calculosViajes);
		// Si llegué hasta acá agrego el registro
		PeticionDTO p = new PeticionDTO();
		p.setCedula(archivo.getCedula());
		p.setFecha(Calendar.getInstance());
		CrudPeticion crud = new CrudPeticion();
		crud.crearPeticion(p);
		return resultado;
		} catch(ExcepcionAplicacion e) {
			throw e;
		} catch(Exception e) {
			LOG.error("Error ejecutando funcion del servicio de cálculo", e);
			throw new ExcepcionAplicacion("Estimado usuario, ocurrió un error ejecutando el servicio, por favor intente nuevamente");
		}
	}
	
	
	
	/**
	 * Transforma los datos de un string a una lista, extrayendo todos los números
	 * uno a uno
	 */
	@Override
	public List<Integer> recibir(String datos) throws ExcepcionAplicacion {
		try {
			List<Integer> resultado = new ArrayList<>();
			String[] numDatos = datos.replaceAll("\\s", ",").split(",");
			for (int i = 0; i < numDatos.length; i++) {
				resultado.add(Integer.parseInt("" + numDatos[i]));
			}
			return resultado;
		} catch (Exception e) {
			LOG.error("Ocurrió un error extrayendo data del archivo enviado", e);
			throw new ExcepcionAplicacion(
					"Estimado usuario, ocurrió un error leyendo el archivo enviado, por favor verifique e intente nuevamente");
		}
	}

	/**
	 * Transforma una lista de objetos a un string separado por saltos de línea
	 */
	@Override
	public String enviar(List<String> datos) throws ExcepcionAplicacion {
		try {
			StringBuilder resultado = new StringBuilder();
			for (String o : datos) {
				resultado.append(o).append(System.lineSeparator());
			}
			return resultado.toString();
		} catch (Exception e) {
			LOG.error("Ocurrió un error construyendo la data del resultado", e);
			throw new ExcepcionAplicacion(
					"Estimado usuario, ocurrió un error generando la respuesta, por favor intente nuevamente o contacte con el administrador del sistema");
		}
	}

	/**
	 * Extrae todos los dias, los elementos de cada dia y calcula
	 * el máximo de viajes del perezoso wilson
	 */
	@Override
	public List<String> calcular(List<Integer> casos) throws ExcepcionAplicacion {
		List<String> resultado = new ArrayList<>();
		try {
			// Extraer T: número de dias que Wilson labora
			Integer dias = casos.remove(0);
			int posicionActual = 0;
			for (int dia = 1; dia <= dias; dia++) {
				// Extraigo datos de un día particular y calculo los viajes
				Integer cantidadElementos = casos.get(posicionActual);
				posicionActual += 1;
				List<Integer> elementosDia = new ArrayList<>();
				while(cantidadElementos > 0) {
					elementosDia.add(casos.get(posicionActual));
					posicionActual+=1;
					cantidadElementos-=1;
				}
				resultado.add(String.format("Case #%s: %s", String.valueOf(dia), String.valueOf(calcularViajes(elementosDia))));
				
			}
			return resultado;
		} catch (Exception e) {
			LOG.error("Ocurrió un error calculando los viajes", e);
			throw new ExcepcionAplicacion(
					"Estimado usuario, ocurrió un error durante el cálculo de los viajes, por favor intente nuevamente y asegurese que el archivo enviado cumple con el formato específico");
		}
	}

	/**
	 * Método que realiza el cálculo del máximo de viajes que puede realizar Wilson
	 * para un día específico
	 * 
	 * @param elementos
	 */
	private Integer calcularViajes(List<Integer> elementos) {
		// Elementos ordenados para facilitar la extracción
		Collections.sort(elementos);
		// Acumulado de viajes
		int viajes = 0;
		// auxiliar para manejar la bolsa actual
		List<Integer> bolsa = new ArrayList<>();
		while (!elementos.isEmpty()) {
			// Si la bolsa no cumple la condición agrego elementos
			while (bolsa.isEmpty() || bolsa.get(0) * bolsa.size() < 50) {
				// Si la bolsa está vacia agrego un elemento grande, si no pequeños
				if (bolsa.isEmpty())
					bolsa.add(elementos.remove(elementos.size() - 1));
				else {
					bolsa.add(elementos.remove(0));
				}
			}
			viajes += 1;
			// Valido si puedo hacer otro viaje
			if (!elementos.isEmpty() && (int) elementos.get(elementos.size() - 1) * elementos.size() > 50) {
				bolsa = new ArrayList<>();
			} else
				elementos.clear();

		}
		return viajes;
	}
	


}
