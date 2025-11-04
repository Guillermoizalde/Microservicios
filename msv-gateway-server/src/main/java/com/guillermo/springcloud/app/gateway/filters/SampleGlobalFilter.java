package com.guillermo.springcloud.app.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SampleGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // === FASE PRE ===
        long startTime = System.currentTimeMillis();
        String token = "hola";

        logger.info("[PRE] Ejecutando filtro global - ruta: {}", exchange.getRequest().getURI());
        logger.debug("[PRE] Agregando header 'token' con valor: {}", token);

        // Guarda el token en atributos del exchange
        exchange.getAttributes().put("token", token);

        // Mutar headers de la request sin romper la cadena
        exchange.getRequest().mutate().headers(h -> h.add("token", token)).build();

        // === CONTINUAR CON LA CADENA ===
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {

            // === FASE POST ===
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("[POST] Respuesta procesada en {} ms", elapsedTime);

            // Recuperar el token del atributo (seguro)
            String storedToken = (String) exchange.getAttribute("token");

            // Evitar NullPointer
            if (storedToken == null) {
                storedToken = exchange.getRequest().getHeaders().getFirst("token");
            }

            logger.debug("[POST] Token encontrado: {}", storedToken != null ? storedToken : "N/A");

            // Agregar una cookie
            exchange.getResponse().addCookie(ResponseCookie.from("color", "red").build());

            // No cambies el Content-Type si ya fue establecido por el servicio destino
            if (!exchange.getResponse().getHeaders().containsKey("Content-Type")) {
                exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
            }

            logger.info("[POST] Filtro global completado correctamente");
        }));
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
