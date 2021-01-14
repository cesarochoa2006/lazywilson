package com.cesarochoa2006.aplicacion.tipos;

import java.util.List;

/**
 * Interface que define lo que debe contener una clase 
 * que desempeñe como calculadora (de viajes)
 * @author cesar
 *
 */
public interface ICalculador {
	public List<String> calcular(List<Integer> elementos) throws ExcepcionAplicacion;
}
