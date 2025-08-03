package com.example.react_blog.filter;

import com.example.react_blog.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter { //jwt 필터

    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try{
            String token =  parseBearToken(request);

            //Bearer,Authorization 받아올때 둘중의 하나가 없을경우 동작
            if (token == null){
                filterChain.doFilter(request,response); // 다음 필터로 넘긴다
                return;
            }

            // 토큰에서 email을 꺼낸다
            String email = jwtProvider.validate(token);

            // jwtprovider에 있는 validate메서드에서 SigningKey가 안 맞거나 기간이 만료되었을경우 null을 받는다
            if (email ==null){
                filterChain.doFilter(request,response);
                return;
            }

            //여기서는 email이 사용자 아이디이다 ,비밀번호 사용x , 권한
            //AuthorityUtils.NO_AUTHORITIES 이거는 권한이 없다는 뜻이다
            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,null, AuthorityUtils.NO_AUTHORITIES);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(securityContext);
        }catch (Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request,response); //다음 필터로 넘긴다는 뜻이다


    }

    //JWT 토큰 자체를 꺼내는 용도
    //Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ6aEBleGFtcGxlLmNvbSIsImV4cCI6MTY5ODAwMDAwMH0.abc123def456
    //.substring(7)를 통해 "Bearer "를 잘라내고 👉 결과적으로 eyJhbGciOi...abc123def456 이 부분만 가져온다
    private String parseBearToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        String token = authorization.substring(7); //7번째 부터 가지고 온다
        return token;
    }
}
