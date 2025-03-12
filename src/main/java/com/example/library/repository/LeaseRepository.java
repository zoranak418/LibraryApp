package com.example.library.repository;

import com.example.library.model.Copy;
import com.example.library.model.Lease;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    public List<Lease> findByCopy(Copy copy);
    public List<Lease> findByCopyId(Long id);
    public List<Lease> findByUserAndCopy(User user, Copy copy);
    public List<Lease> findByUserAndCopyId(User user, Long id);

    @Query("select l from Lease l where l.copy.book.id =:idBook")
    public List<Lease> findByBook(@Param("idBook")Long idBook);

    @Query("select l from Lease l where l.user.id = :idUser")
    public List<Lease> findByUser(@Param("idUser")Long idUser);
}
