package fr.test.taskAppJwt.security.filters;

import fr.test.taskAppJwt.security.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.addHeader("access-control-allow-origin", "http://localhost:4200"); // Accepte les requêtes de tous les domaines
//        response.addHeader("access-control-allow-headers", "Origin, Accept, X-Requested-With, " +
//                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");

        response.addHeader("access-control-expose-headers", "Authorization");
        String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if (request.getMethod().equals("OPTIONS"))
            response.setStatus(HttpServletResponse.SC_OK);
        else {
            if (jwtToken != null && jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody();

                List<GrantedAuthority> authorities = ((List<Map<String, String>>) claims.get("roles"))
                        .stream()
                        .map(map -> new SimpleGrantedAuthority(map.get("authority")))
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authenticatedSpringUser =
                        new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authenticatedSpringUser);
            }
            filterChain.doFilter(request, response); // <=> Passage au filtre suivant (possibilté de plusieurs filtres sinon retour au filtre de spring security)
        }
    }

}
