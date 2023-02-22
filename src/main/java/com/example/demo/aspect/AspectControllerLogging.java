package com.example.demo.aspect;

import com.example.demo.dtos.MovieCrateDto;
import com.example.demo.dtos.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class AspectControllerLogging {

    @Before(value = "execution(public * com.example.demo.controllers.MovieController.save*(..))")
    public void controllerSaveMethodsLogging(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        if (methodName.equals("save")) {
            log.info("From AOP. Controller saving method one movie" + joinPoint.getArgs()[0]);
        } else {
            String infoLog = Arrays.stream(((MovieCrateDto[]) joinPoint.getArgs()[0]))
                    .map(MovieCrateDto::toString)
                    .collect(Collectors.joining(";"));

            log.info("From AOP. Controller saving method list of movie: " + infoLog);
        }
    }

    @Before(value = "execution(public * com.example.demo.controllers.MovieController.find*(..))")
    public void controllerFindByBeforeLogging(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        log.info("From AOP. Controller " + methodName + " method running");
    }

    @AfterReturning(
            value = "execution(public * com.example.demo.controllers.MovieController.find*(..))",
            returning = "movieDtoList"
    )
    public void controllerFindByAfterLogging(JoinPoint joinPoint, ResponseEntity<List<MovieDto>> movieDtoList) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String fullName = methodSignature.getDeclaringTypeName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);

        String infoLog = movieDtoList.getBody().stream()
                .map(MovieDto::toString)
                .collect(Collectors.joining(";"));

        log.info("From AOP." + className + " " + methodName + " method return list of movies: " + infoLog);

    }

}
