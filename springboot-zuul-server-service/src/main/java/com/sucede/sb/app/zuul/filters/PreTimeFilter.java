package com.sucede.sb.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreTimeFilter.class);
			
	@Override
	public boolean shouldFilter() {
		//Indica cuando se debe ejecutar el filtro
		//Lo ponemos a true para que se ejecute siempre
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		
		Long timeIni = System.currentTimeMillis();
		request.setAttribute("timeIni", timeIni);
		
		return null;
	}

	@Override
	public String filterType() {
		// Los tipos tienen que ser palabras clave, en este caso "pre"
		return "pre";
	}

	@Override
	public int filterOrder() {
		//Orden de los filtros
		return 1;
	}

}
