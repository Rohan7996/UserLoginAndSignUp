package com.example.RegisterLogin.Service.impl;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public String authenticate(String email, String password) throws AuthenticationException {
        Employee employee = employeeRepo.findByEmail(email);
        if (employee!=null) {
            if (password.equals(employee.getPassword())) {
                return generateToken(employee.getEmployeeid());
            }
        }
        throw new AuthenticationException("Invalid username or password");
    }

    private String generateToken(Integer employeeId) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + (1000 * 120));
        String token = Jwts.builder()
                .setSubject(String.valueOf(employeeId))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
        return token;
    }
}

