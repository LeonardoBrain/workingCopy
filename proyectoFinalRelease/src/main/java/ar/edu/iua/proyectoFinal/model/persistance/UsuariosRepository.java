package ar.edu.iua.proyectoFinal.model.persistance;


import ar.edu.iua.proyectoFinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<User, Long> {

    public List<User> findByUsernameOrEmail(String username, String email);

    @Transactional
    @Modifying
    @Query(value="UPDATE users SET password=? WHERE username=? OR email=?",nativeQuery=true)
    public int setPassword(String password, String username, String email);
}
