package com.sucede.sb.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PostTimeFilter.class);
			
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
		
		log.info("Entramos al post filter");
		
		Long timeIni = (Long) request.getAttribute("timeIni");
		Long timeFin = System.currentTimeMillis();
		Long timeTot = timeFin - timeIni;

		log.info(String.format("Tiempo transcurrido en milisegundos %s.",timeTot.doubleValue()));
		log.info(String.format("Tiempo transcurrido en segundos %s.",timeTot.doubleValue()/1000.00));
		
		return null;
	}

	@Override
	public String filterType() {
		// Los tipos tienen que ser palabras clave, en este caso "post"
		return "post";
	}

	@Override
	public int filterOrder() {
		//Orden de los filtros
		return 2;
	}

}
