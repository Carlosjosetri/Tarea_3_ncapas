package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/parametros")
	public ModelAndView resultadoAlumno(@RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String nacimiento, @RequestParam String Lnacimiento, @RequestParam String colegio,
			@RequestParam String telefono, @RequestParam String celular) throws ParseException {
		List<String> errores = new ArrayList<>();
				
		ModelAndView mav = new ModelAndView();
	
		
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nacimiento);
		Date date_not = new SimpleDateFormat("yyyy-MM-dd").parse("2003-01-01");
				
		if(date.compareTo(date_not) < 0) {errores.add("- La fecha debe ser mayor que 1/1/2003");} 
		if(nombre.length() == 0 || nombre.length() > 25) {errores.add("Nombre mínimo 1 carácter y máximo 25 caracteres");}
		if(apellido.length() == 0 || apellido.length() > 25) {errores.add("-Apellido mínimo 1 carácter y máximo 25 caracteres");}
		if(Lnacimiento.length() == 0 || Lnacimiento.length() > 25) {errores.add("- no ha escrito el lugar de nacimiento o supera el limite de 25 caracteres");}
		if(colegio.length() == 0 || colegio.length() > 100) {errores.add("- no ha escrito el instituto/colegio o supera el limite de 100 caracteres");}
		if(telefono.length() != 8 || celular.length() != 8) {errores.add("- El numero de telefono/movil debe ser de 8 caracteres");}
		
		if(errores.size() > 0) {
			mav.addObject("errores", errores);
			mav.setViewName("/errors");
			return mav;
		}
		
		mav.setViewName("/results");
		return mav;
	
		
		
	}
}
